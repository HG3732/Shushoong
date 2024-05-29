package kh.mclass.shushoong.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.siot.IamportRestClient.IamportClient;

@Configuration
@PropertySource("classpath:/keyproperties/apikey.properties")
public class AppConfig {
	//PortOne
	@Value("${portone.rest.api.key}")
	private String portoneRestApiKey;
	@Value("${portone.rest.api.secret.key}")
	private String portoneRestApiSecretKey;
	
	@Bean
	public IamportClient iamportClient() {
		System.out.println("=====1");
		System.out.println(portoneRestApiKey);
		System.out.println(portoneRestApiSecretKey);
		return new IamportClient(portoneRestApiKey, portoneRestApiSecretKey);
	}
	
}
