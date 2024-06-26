package kh.mclass.shushoong.member.login.kakao;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:/keyfiles/apikey.properties")
public class KakaoApi {

	// KakaoApi
	@Value("${kakao.api_key}")
	private String kakaoApiKey;
	@Value("${kakao.redirect_uri}")
	private String kakaoRedirectUri;
	
	// 토큰 발급
	public String getAccessToken(String code) {
		String accessToken = "";
		String refreshToken = "";
		String reqUrl = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// 필수 헤더 세팅
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setDoOutput(true); // OutputStream으로 POST 데이터를 넘기는 옵션

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();

			// 필수 쿼리 파라미터 세팅
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=").append(kakaoApiKey);
			sb.append("&redirect_uri=").append(kakaoRedirectUri);
			sb.append("&code=").append(code);
			
			bw.write(sb.toString());
	        bw.flush();

	        int responseCode = conn.getResponseCode();
//	        System.out.println("[KakaoApi.getAccessToken] responseCode = "+responseCode);

	        BufferedReader br;
	        if (responseCode >= 200 && responseCode <= 300) {
	            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        
	        String line = "";
			StringBuilder responseSb = new StringBuilder();
			while((line = br.readLine())!=null) {
				responseSb.append(line);
			}
			String result = responseSb.toString();
			System.out.println("responseBody = "+result);
			
			Map<String, String> resultMap = new Gson().fromJson(result, Map.class);
			accessToken = resultMap.get("access_token");
			refreshToken = resultMap.get("refresh_token");
			
			br.close();
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return accessToken;
	}
	
	// 사용자 정보 반환 메소드
	public HashMap<String, Object> getUserInfo(String accessToken) {
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqUrl = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
	        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	        
	        int responseConde = conn.getResponseCode();
//	        System.out.println("[KakaoApi.getAccessToken] responseCode = "+ responseConde);
	        
	        BufferedReader br;
	        if(responseConde >= 200 && responseConde <= 300) {
	        	br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	        	br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        
	        String line = "";
	        StringBuilder responseSb = new StringBuilder();
	        while((line = br.readLine()) != null) {
	        	responseSb.append(line);
	        }
	        String result = responseSb.toString();
	        System.out.println("responseSb = "+ responseSb);
	        
	        Gson gson = new Gson();
	        
	        HashMap<String, Object> resultMap = gson.fromJson(result, HashMap.class);
	        
	        Object userId = resultMap.get("id");
	        String propertiesStr = gson.toJson(resultMap.get("properties"));
//			System.out.println(propertiesStr);
			String kakaoAccountStr = gson.toJson(resultMap.get("kakao_account"));
			
			HashMap<String, Object> properties = gson.fromJson(propertiesStr, HashMap.class);
			HashMap<String, Object> kakaoAccount = gson.fromJson(kakaoAccountStr, HashMap.class);
			
			String nickname = (String)properties.get("nickname");
			String email = (String)kakaoAccount.get("email");
			
			userInfo.put("userId", userId);
			userInfo.put("nickname", nickname);
			userInfo.put("email", email);
	        
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userInfo;
	}
	
	// 로그아웃 시키기
	public void kakaoLogout(String accessToken) {
		String reqUrl = "https://kapi.kakao.com/v1/user/logout";
		
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);
			
			int responseCode = conn.getResponseCode();
			System.out.println("[KakaoApi.kakaoLogout] responseCode = "+ responseCode);
			BufferedReader br;
			if (responseCode >= 200 && responseCode <= 300) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
			String line = "";
			StringBuilder responseSb = new StringBuilder();
			while((line = br.readLine()) != null) {
				responseSb.append(line);
			}
			String result = responseSb.toString();
			System.out.println("kakaoLogout] -responseBody = {} "+ result);
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//카카오 연동 해제
		public void unlink(String accessToken) {
			String reqUrl = "https://kapi.kakao.com/v1/user/unlink";
			try {
				URL url = new URL(reqUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				//파라미터 입력
				conn.setRequestProperty("Authorization", "Bearer "+accessToken);
				conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;utf-8");
				
				int responseCode = conn.getResponseCode();
				System.out.println("[KakaoApi.unlink] responseCode : "+responseCode);
				
				BufferedReader br;
				if (responseCode >= 200 && responseCode <= 300) {
		            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        } else {
		            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		        }
				
				String line = "";
				StringBuilder responseSb = new StringBuilder();
				while((line = br.readLine())!=null) {
					responseSb.append(line);
				}
				
				String result = responseSb.toString();
				System.out.println("responseBody = "+result);
				
				br.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//카카오 나한테 링크 보내기
		public void sendLink(String accessToken) {
			String reqUrl = "https://kapi.kakao.com/v2/api/talk/memo/scrap/send";
			try {
				URL url = new URL(reqUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				//파라미터 입력
				conn.setRequestProperty("Authorization", "Bearer "+accessToken);
				conn.setRequestProperty("request_url", "http://shushoong/login");
				conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;utf-8");
				
				int responseCode = conn.getResponseCode();
//				System.out.println("[KakaoApi.getUserInfo] responseCode : "+responseCode);
				
				BufferedReader br;
				if (responseCode >= 200 && responseCode <= 300) {
		            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        } else {
		            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		        }
				
				String line = "";
				StringBuilder responseSb = new StringBuilder();
				while((line = br.readLine())!=null) {
					responseSb.append(line);
				}
				String result = responseSb.toString();
				System.out.println("responseBody = "+result);
				
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
