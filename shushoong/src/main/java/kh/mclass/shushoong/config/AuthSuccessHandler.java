package kh.mclass.shushoong.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kh.mclass.shushoong.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		super.clearAuthenticationAttributes(request);
		
		
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		HttpSessionSecurityContextRepository repository = new HttpSessionSecurityContextRepository();
		repository.saveContext(context, request, response);
		
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest != null) {
			String url = savedRequest.getRedirectUrl();
			if (url == null || url.equals("")) {
				url = "/";
			}
			if (url.contains("/register")) {
				url = "/home";
			}
			if (url.contains("/login")) {
				url = "/home";
			}
			requestCache.removeRequest(request, response);
			getRedirectStrategy().sendRedirect(request, response, url);
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
