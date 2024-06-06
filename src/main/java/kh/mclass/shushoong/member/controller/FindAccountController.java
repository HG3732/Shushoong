package kh.mclass.shushoong.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FindAccountController {
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
