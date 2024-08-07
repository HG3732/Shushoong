package kh.mclass.shushoong.config;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kh.mclass.shushoong.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String error;
		if(exception instanceof BadCredentialsException) {
			error = "BadCredentialsException";
		}else if(exception instanceof InternalAuthenticationServiceException) {
			error = "InternalAuthenticationServiceException";
		}else if(exception instanceof UsernameNotFoundException) {
			error = "UsernameNotFoundException";
		}else if(exception instanceof AuthenticationException) {
			error = "AuthenticationException";
		}else {
			error = "others";
		}
		error = URLEncoder.encode(error, "UTF-8");
		setDefaultFailureUrl("/login/admin?error=true&exception="+error);
		
		super.onAuthenticationFailure(request, response, exception);
	}
}
