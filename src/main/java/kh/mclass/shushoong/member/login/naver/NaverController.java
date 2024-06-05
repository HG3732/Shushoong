package kh.mclass.shushoong.member.login.naver;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class NaverController {
	
	private final NaverApi naverApi;
	
	@GetMapping("/login/oauth2/code/naver")
	public String naverLogin(String code, HttpSession session) {
		// 1. 인가코드 받기
		
		//2. 토큰 받기
		String accessToken = naverApi.getAccessToken(code);
		session.setAttribute("naverToken", accessToken);
				
		//3. 사용자 정보 받기
		Map<String, Object> userInfo = naverApi.getUserInfo(accessToken);
				
		Object userId = userInfo.get("userId");
		String email = (String) userInfo.get("email");
		String nickname = (String) userInfo.get("nickname");
				
		System.out.println("userId = "+userId.toString());
		System.out.println("email = "+email);
		System.out.println("nickname = "+nickname);
		System.out.println("accessToken = "+accessToken);
				
		return "redirect:/login";
	}
	
	// 카카오 로그아웃 
	@GetMapping("/logout/kako") 
	public String logout(HttpSession session) {
		String naverToken = (String) session.getAttribute("naverToken");
		System.out.println("[naverToken] = "+naverToken);
		if(naverToken != null) {
			naverApi.unlink(naverToken);
			session.invalidate();
			return "redirect:/main";
		}else {
			return "redirect:/login";
		}
	}
	
//	//카카오 나한테 링크 보내기
//		@GetMapping("/sendmsg/me")
//		public String getMethodName(HttpSession session) {
//			String naverToken = (String) session.getAttribute("naverToken");
//			sendLinkPost("http://shushoong/login", "Bearer "+naverToken);
//			return naverToken;
//		}
//		@PostMapping("https://kapi.kakao.com/v2/api/talk/memo/scrap/send")
//		public String sendLinkPost(@RequestBody String request_url, @RequestHeader String Authorization) {
//			return "redirect:/login";
//		}
}
