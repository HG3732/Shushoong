package kh.mclass.shushoong.member.login.kakao;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class KakaoController {
	
	private final KakaoApi kakaoApi;
	
	
	@GetMapping("/login/oauth2/code/kakao")
	public String kakaoLogin(String code, HttpSession session) {
		// 1. 인가코드 받기
		
		//2. 토큰 받기
		String accessToken = kakaoApi.getAccessToken(code);
		session.setAttribute("kakaoToken", accessToken);
				
		//3. 사용자 정보 받기
		Map<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
				
		Object userId = userInfo.get("userId");
		String email = (String) userInfo.get("email");
		String nickname = (String) userInfo.get("nickname");
				
		System.out.println("userId = "+userId.toString());
		System.out.println("email = "+email);
		System.out.println("nickname = "+nickname);
		System.out.println("accessToken = "+accessToken);
				
		return "redirect:/login";
	}
}
