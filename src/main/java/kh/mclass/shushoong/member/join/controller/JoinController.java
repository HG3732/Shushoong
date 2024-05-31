package kh.mclass.shushoong.member.join.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JoinController {
	// 회원가입 메인 페이지로 이동
	@GetMapping("join")
	public String join() {
		return "member/joinHome";
	}

	// 일반유저 가입 페이지로 이동
	@GetMapping("join/customer")
	public String joinCustomer() {
		return "member/userJoin";
	}

	// 사업자회원 가입 페이지로 이동
	@GetMapping("join/business")
	public String joinBusiness() {
		return "member/businessJoin";
	}
}
