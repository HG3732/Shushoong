package kh.mclass.shushoong.member.login.naver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Configuration
@PropertySource("classpath:/keyfiles/apikey.properties")
public class NaverApi {

	// naverApi
		@Value("${naver.client_id}")
		private String naverClientId;
		@Value("${naver.redirect_uri}")
		private String naverRedirectUri;
		@Value("${naver.client_secret}")
		private String naverClientSecret;
		
		public String getAccessTokenString(String code, String state) {
			String reqUrl = "https://nid.naver.com/oauth2.0/token";
			RestTemplate restTemplate = new RestTemplate();
			
			// HttpHeader Object 
			HttpHeaders headers = new HttpHeaders();
			
			// HttpBody Object
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("grant_type", "authorization_code");
			params.add("client_id", naverClientId);
		    params.add("client_secret", naverClientSecret);
		    params.add("code", code);
		    params.add("state", state);
		    
		    // http body params 와 http headers 를 가진 엔티티
		    HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, headers);
		    
		    // reqUrl로 http요청, Post 방식
		    ResponseEntity<String> response = restTemplate.exchange(reqUrl, 
		    												HttpMethod.POST,
		    												naverTokenRequest,
		    												String.class);
		    
		    String responseBody = response.getBody();
		    JsonObject asJsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
		    return asJsonObject.get("access_token").getAsString();
		}
		
	
}
