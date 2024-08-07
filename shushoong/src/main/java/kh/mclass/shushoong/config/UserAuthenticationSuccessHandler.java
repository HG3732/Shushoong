package kh.mclass.shushoong.config;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kh.mclass.shushoong.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j

public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private final MemberService memberService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date now = new Date();
		String loginDate = sdf2.format(now);

		Object principal = authentication.getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String userId = userDetails.getUsername();

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String userGrade = auth.getAuthority();
		
		if (userGrade == "admin") {
			setDefaultTargetUrl("/admin/manager/home");
		} else {
			setDefaultTargetUrl("/home");
		}

		// 로그인 기록
		
		
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("latestLogin", loginDate);

		memberService.loginLog(map);

//		
		
		
		// 계정 권환 확인
//		if (userGrade == "admin" || userGrade == "business") {
//			SecurityContextHolder.clearContext();
//			request.getSession().invalidate();
//			throw new BadCredentialsException("잘못된 로그인 위치입니다.");
//		}
//
//		// 계정잠금 확인
//		String userStatus = memberService.lockedCheck(userId);
//		if (userStatus == "0") {
//			SecurityContextHolder.clearContext();
//			request.getSession().invalidate();
//			throw new LockedException("정지된 계정입니다.");
//		}
		
//		setDefaultTargetUrl("/home");
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		// 사용자가 권한이 필요한 자원에 접근해 인증 예외가 발생해 인증을 처리하는 것이 아닌 경우
		// SavedRequest 객체가 생성되지 않는다.
		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			log.info("targetUrl = {}", targetUrl);
			redirectStrategy.sendRedirect(request, response, targetUrl);
		} else {
			redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
		}
	}
}
