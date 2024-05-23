package kh.mclass.shushoong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestKakaoService {
	
	// 토큰 발급 구현 
	public String getKakaoAccessToken (String code) {
		String accessToken = "";
		String refreshToken = "";
		String requestURL = "https://kauth.kakao.com/oauth/token";
		
		try {
			URL url = new URL(requestURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			String sb = "grant_type=authorization_code" +
	                "&client_id=REST_API_KEY 입력" + "199633a1a6c5c720315f72f34bf77975"
	                + "&redirect_uri=http://localhost:8080/app/login/kakao" + "http://localhost:8080/shushoong/app/login/kakao"
	                + "&code=" + code;

			bufferedWriter.write(sb);
			bufferedWriter.flush();
			
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			StringBuilder result = new StringBuilder();
			
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}
			
			System.out.println("response body : " + result);
						
			JsonElement element = JsonParser.parseString(result.toString());
			
			accessToken = element.getAsJsonObject().get("access_token").getAsString();
			refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
			
			System.out.println("accessToken : " + accessToken);
			System.out.println("refreshToken : " + refreshToken);

			bufferedReader.close();
			bufferedWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return accessToken;
		
	}
	
	// 사용자 로그인 처리 구현
	public HashMap<String, Object> getUserInfo(String accessToken) {
		HashMap<String, Object> userInfo = new HashMap<>();
		String postURL = "https://kapi.kakao.com/v2/user/me";
		
		try {
			URL url = new URL(postURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);
			
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			StringBuilder result = new StringBuilder();
			
			while((line = br.readLine()) != null) {
				result.append(line);
			}
			System.out.println("response body : " + result);
			
	        JsonElement element = JsonParser.parseString(result.toString());
	        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
	        JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

	        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	        String email = kakaoAccount.getAsJsonObject().get("email").getAsString();

	        userInfo.put("nickname", nickname);
	        userInfo.put("email", email);

			
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		
		return userInfo;
		
	}
}
