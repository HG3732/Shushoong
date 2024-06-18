package kh.mclass.shushoong.mypage.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class MypageManageBusinessController {

	// 사업자 회원 관리 페이지로 이동
	@GetMapping("/manager/business")
	public String manageBusiness() {
		return "mypage/admin/manageBusiness";
	}

	// 등록 상품 관리 페이지로 이동
	@GetMapping("/manager/product")
	public String manageProduct() {
		return "mypage/admin/manageProduct";
	}
}
