package kh.mclass.shushoong.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import kh.mclass.shushoong.member.model.service.MemberSecurityService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberSecurityService memberService;
	
	// 로그인 페이지 이동
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	// 로그아웃
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
