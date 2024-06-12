package kh.mclass.shushoong.member.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	// 로그인 페이지로 이동
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}

}
