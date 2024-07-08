package kh.mclass.shushoong.member.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.mclass.shushoong.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	@Autowired
	private final MemberService memberservice;
	
	// 관리자 로그인
	@GetMapping("/login/admin")
	public String managerLogin() {
		return "mypage/admin/adminLogin";
	}
	
	// 로그인 페이지로 이동
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
}
