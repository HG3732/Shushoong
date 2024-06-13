package kh.mclass.shushoong.member.login.naver;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NaverController {
	private final NaverApi naverApi;
	
	@ResponseBody
	@GetMapping("/login/oauth2/code/naver")
	public Map<String, Object> naverLogin(@RequestParam(name = "code") String code,
										@RequestParam(name = "state") String state) {
		Map<String, Object> map = new HashMap<>();
		// 1. 인가 코드 받기
		
		// 2. 접근 토큰 발급 요청
		String accessToken = naverApi.getAccessTokenString(code, state);
		
		// 3. 사용자 정보 받기
		NaverProfile userInfo = naverApi.getUserInfo(accessToken);
		map.put("id", userInfo.getId());
		map.put("nickName", userInfo.getNickname());
		map.put("email", userInfo.getEmail());
		map.put("mobile", userInfo.getMobile());
		
		return map;
	}
}
