package kh.mclass.shushoong;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/app/login")
public class TestKaKaoController {
	private TestKakaoService testKakaoService = new TestKakaoService();
	
	@GetMapping("/open")
	public String home() {
		return "kakaotest";
	}
	
	@ResponseBody
	@GetMapping("/kakao")
	public String kakaoLogin(@RequestParam(required = false) String code) {
		System.out.println("kakao login code !!!!");
		System.out.println(code);
		
		//URL애 포함된 code를 이용하여 액세스 토큰 발급
		String accessToken = testKakaoService.getKakaoAccessToken(code);
        System.out.println(accessToken);
		
		// 액세스 토큰을 이용하여 카카오 서버에서 유저 정보 받아오기
		HashMap<String, Object> userInfo = testKakaoService.getUserInfo(accessToken);
        System.out.println("login Controller : " + userInfo);

		return null;
	}
}
