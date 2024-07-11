package kh.mclass.shushoong.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity.RequestMatcherConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import kh.mclass.shushoong.config.UserAuthenticationSuccessHandler;
import kh.mclass.shushoong.config.AdminAuthenticationFailureHandler;
import kh.mclass.shushoong.config.AdminAuthenticationSuccessHandler;
import kh.mclass.shushoong.config.BusinessAuthenticationSuccessHandler;
import kh.mclass.shushoong.config.UserAuthenticationFailureHandler;
import kh.mclass.shushoong.member.model.service.CustomAuthenticationFilter;
import kh.mclass.shushoong.member.model.service.MemberSecurityService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	UserAuthenticationSuccessHandler authSuccessHandler;
	
	@Autowired
	BusinessAuthenticationSuccessHandler businessAuthSuccessHandler;
	
	@Autowired
	UserAuthenticationFailureHandler authFailureHandler;
	
	@Autowired
	AdminAuthenticationSuccessHandler adminAuthSuccessHandler;
	
	@Autowired
	AdminAuthenticationFailureHandler adminAuthFailureHandler;
	
	@Autowired
	MemberSecurityService securityService;
		
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
		.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAuthority("admin")
				.requestMatchers(new AntPathRequestMatcher("/customer/**")).hasAuthority("customer")
				.requestMatchers(new AntPathRequestMatcher("/business/**")).hasAuthority("business")
				.requestMatchers(new AntPathRequestMatcher("/support/notice/write")).hasAuthority("admin")
				.requestMatchers(new AntPathRequestMatcher("/support/qna/write")).hasAuthority("customer")
				.requestMatchers(new AntPathRequestMatcher("/support/qna/write")).hasAuthority("business")
				.requestMatchers(new AntPathRequestMatcher("/login/**")).anonymous()
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
				)
		.csrf((csrf) -> csrf
				.disable())
		.headers((headers) -> headers
				.addHeaderWriter(new XFrameOptionsHeaderWriter(
						XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
		.formLogin((formLogin) -> formLogin
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.successHandler(authSuccessHandler)
				.failureHandler(authFailureHandler)
				.usernameParameter("userId")
				.passwordParameter("userPwd"))
		.rememberMe((rememberMe -> rememberMe
//				.key("uniqueAndSecret")
				.rememberMeParameter("remember")
				.tokenValiditySeconds(3600)
				.alwaysRemember(false)
				.userDetailsService(securityService)))
		.logout((logout) -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/home")
				.invalidateHttpSession(true)
//				.deleteCookies("remember")
				)
		.exceptionHandling((exceptionHandling -> exceptionHandling
				.accessDeniedPage("/error/redirect"))
		);
		
		return http.build();
	}
	
//	@Bean
//	@Order(1)
//	SecurityFilterChain businessFilterChain(HttpSecurity http) throws Exception {
//		
//		http
//		.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
//				.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAuthority("admin")
//				.requestMatchers(new AntPathRequestMatcher("/customer/**")).hasAuthority("customer")
//				.requestMatchers(new AntPathRequestMatcher("/business/**")).hasAuthority("business")
//				.requestMatchers(new AntPathRequestMatcher("/login/**")).anonymous()
//				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
//				)
//		.csrf((csrf) -> csrf
//				.disable())
//		.headers((headers) -> headers
//				.addHeaderWriter(new XFrameOptionsHeaderWriter(
//						XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
//		.formLogin((formLogin) -> formLogin
//				.loginPage("/login")
//				.loginProcessingUrl("/login/business")
//				.successHandler(businessAuthSuccessHandler)
//				.failureHandler(authFailureHandler)
//				.usernameParameter("userId")
//				.passwordParameter("userPwd"))
//		.rememberMe((rememberMe -> rememberMe
////				.key("uniqueAndSecret")
//				.rememberMeParameter("remember")
//				.tokenValiditySeconds(3600)
//				.alwaysRemember(false)
//				.userDetailsService(securityService)))
//		.sessionManagement(
//				(auth) -> auth
//					.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).sessionFixation((sessionFixation)->sessionFixation.newSession())
//					.maximumSessions(1)
//					.maxSessionsPreventsLogin(true))
//		.logout((logout) -> logout
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//				.logoutSuccessUrl("/home")
//				.invalidateHttpSession(true))
//		.exceptionHandling((exceptionHandling -> exceptionHandling
//				.accessDeniedPage("/error/redirect")));
//		
//		return http.build();
//	}
////	
////	
////	@Bean
////	@Order(2)
////	SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
////		
////		http
////		.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
////				.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAuthority("admin")
////				.requestMatchers(new AntPathRequestMatcher("/customer/**")).hasAuthority("customer")
////				.requestMatchers(new AntPathRequestMatcher("/business/**")).hasAuthority("business")
////				.requestMatchers(new AntPathRequestMatcher("/login/**")).anonymous()
////				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
////				)
////		.csrf((csrf) -> csrf
////				.disable())
////		.headers((headers) -> headers
////				.addHeaderWriter(new XFrameOptionsHeaderWriter(
////						XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
////		.formLogin((formLogin) -> formLogin
////				.loginPage("/login/admin")
////				.loginProcessingUrl("/login/admin")
////				.successHandler(adminAuthSuccessHandler)
////				.failureHandler(adminAuthFailureHandler)
////				.usernameParameter("userId")
////				.passwordParameter("userPwd"))
////		.logout((logout) -> logout
////				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////				.logoutSuccessUrl("/home")
////				.invalidateHttpSession(true))
////		.exceptionHandling((exceptionHandling -> exceptionHandling
////				.accessDeniedPage("/error/redirect")));
////		
////		return http.build();
////	}
	
	// 비밀번호 암호화
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
