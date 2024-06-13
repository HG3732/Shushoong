package kh.mclass.shushoong.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class MyPageController {

	// 마이페이지 메인 페이지로 이동
	@GetMapping("/mypage/home")
	public String mypageHome() {
		return "mypage/customer/mypageCustomerHome";
	}

	// 개인정보 수정 페이지로 이동
	@GetMapping("/my/information")
	public String correctInfoCustomer() {
		return "mypage/customer/mypageCorrectInfoCustomer";
	}
}
