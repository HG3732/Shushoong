package kh.mclass.shushoong.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.siot.IamportRestClient.IamportClient;

@Configuration
@PropertySource("classpath:/keyfiles/apikey.properties")
public class AppConfig {
	
	//PortOne
	@Value("${portone.rest.api.key}")
	private String portoneRestApiKey;
	
	@Value("${portone.rest.api.secret.key}")
	private String portoneRestApiSecretKey;
	
	@Bean //이거 다른 클래스에서 끌어다 사용하고 싶으면 @RequiredArgsConstructor 쓰고 변수 선언하면 그 클래스 내에서 사용 가능
	public IamportClient iamportClient() {
		System.out.println("=======결제API======");
		System.out.println(portoneRestApiKey);
		System.out.println(portoneRestApiSecretKey);
		return new IamportClient(portoneRestApiKey, portoneRestApiSecretKey);
	}
	
}
