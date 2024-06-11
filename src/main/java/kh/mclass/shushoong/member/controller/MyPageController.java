package kh.mclass.shushoong.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPageController {
	
	// 회원가입 메인 페이지로 이동
	@GetMapping("mypage/customer")
	public String join() {
		return "mypage/customer/mypageCustomerHome";
	}
}
