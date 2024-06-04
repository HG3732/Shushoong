package kh.mclass.shushoong.member.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kh.mclass.shushoong.member.model.domain.MemberEntity;
import kh.mclass.shushoong.member.model.service.MemberSecurityService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	private final MemberSecurityService memberService;

	@Autowired
	private MemberEntity memberEntity;

	// 로그인 페이지로 이동
	@GetMapping("login")
	public String login() {
		return "member/login";
	}

	// 아이디/비밀번호 찾기 메인 페이지로 이동
	@GetMapping("find/account")
	public String findAccount() {
		return "member/findHome";
	}

	// 일반사용자 아이디/ 비밀번호 찾기 이동
	@GetMapping("find/account/customer")
	public String findAccountCustomer() {
		return "member/findCustomerInfo";
	}

	// 일반사용자 아이디/ 비밀번호 찾기 이동
	@GetMapping("find/account/business")
	public String findAccountBusiness() {
		return "member/findBusinessInfo";
	}

}
