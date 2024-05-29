package kh.mclass.shushoong.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import kh.mclass.shushoong.member.model.domain.MemberEntity;
import kh.mclass.shushoong.member.model.service.MemberSecurityService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberSecurityService memberService;
	
	
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
