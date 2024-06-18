package kh.mclass.shushoong.mypage.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class MypageAdminController {
	
	// 마이페이지 메인 페이지로 이동
	@GetMapping("/manager/home")
	public String managerHome() {
		return "mypage/admin/mypageAdminHome";
	}

}
