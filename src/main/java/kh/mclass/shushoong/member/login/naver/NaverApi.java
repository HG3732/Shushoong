package kh.mclass.shushoong.member.login.naver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.google.gson.Gson;

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
		
		
}
