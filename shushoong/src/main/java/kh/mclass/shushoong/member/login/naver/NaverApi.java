package kh.mclass.shushoong.member.login.naver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@PropertySource("classpath:/keyfiles/apikey.properties")
public class NaverApi {
	// NaverApi
	@Value("${naver.client_id}")
	private String naverApiId;
	@Value("${naver.redirect_uri}")
	private String naverRedirectUri;
	@Value("${naver.client_secret}")
	private String naverApiKey;
}
