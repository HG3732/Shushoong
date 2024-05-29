package kh.mclass.shushoong.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.mclass.shushoong.member.model.service.MemberSecurityService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberSecurityService memberSecurityService;
	
	
	// 로그인 페이지로 이동
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	// 회원가입 페이지로 이동
	@GetMapping("join")
	public String join() {
		return "member/join";
	}
	
	
}
