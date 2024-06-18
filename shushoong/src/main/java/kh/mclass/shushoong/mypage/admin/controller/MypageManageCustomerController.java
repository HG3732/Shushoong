package kh.mclass.shushoong.mypage.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class MypageManageCustomerController {

	// 일반 회원 관리 페이지로 이동
	@GetMapping("/manager/customer")
	public String manageCustomer() {
		return "mypage/admin/manageCustomer";
	}

}
