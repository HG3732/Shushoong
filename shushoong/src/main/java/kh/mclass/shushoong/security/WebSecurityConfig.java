package kh.mclass.shushoong.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kh.mclass.shushoong.config.AuthSuccessHandler;
import kh.mclass.shushoong.config.AuthenticationFailureHandler;
import kh.mclass.shushoong.member.model.service.MemberSecurityService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	AuthSuccessHandler authSuccessHandler;
	
	@Autowired
	AuthenticationFailureHandler authFailureHandler;
	
	@Autowired
	MemberSecurityService securityService;
		
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
		.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
//				.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAuthority("admin")
//				.requestMatchers(new AntPathRequestMatcher("/customer/**")).hasAuthority("customer")
//				.requestMatchers(new AntPathRequestMatcher("/business/**")).hasAuthority("business")
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
				)
		.csrf((csrf) -> csrf
				.disable())
		.headers((headers) -> headers
				.addHeaderWriter(new XFrameOptionsHeaderWriter(
						XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
		.formLogin((formLogin) -> formLogin
				.loginPage("/login")
				.loginPage("/admin/manager/login")
				.defaultSuccessUrl("/")
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
				.invalidateHttpSession(true))
		.exceptionHandling((exceptionHandling -> exceptionHandling
				.accessDeniedPage("/error/redirect")));
		
		return http.build();
	}
	
	// 비밀번호 암호화
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
