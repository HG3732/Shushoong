package kh.mclass.shushoong.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kh.mclass.shushoong.member.model.service.MemberSecurityService;

@Controller
public class MemberController {

	@Autowired
	private MemberSecurityService memberSecurityService;

	// 로그인 페이지로 이동
	@GetMapping("login")
	public String login() {
		return "member/login";
	}

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

	// 회원가입 메인 페이지로 이동
	@GetMapping("account/find")
	public String findInfo() {
		return "member/findhome";
	}

	// 일반 회원 아이디/비밀번호 찾기 페이지로 이동
	@GetMapping("find/customer")
	public String findInfoCustomer() {
		return "member/findCustomerInfo";
	}

	// 사업자 회원 아이디/비밀번호 찾기 페이지로 이동
	@GetMapping("find/business")
	public String findInfoBusiness() {
		return "member/findbusinessInfo";
	}
}
