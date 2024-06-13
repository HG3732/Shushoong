package kh.mclass.shushoong.mypage.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/business")
@RequiredArgsConstructor
public class MypageBusinessController {
	
		// 마이페이지 메인 페이지로 이동
		@GetMapping("/mypage/home")
		public String mypageHome() {
			return "mypage/business/mypageCustomerHome";
		}

		// 개인정보 수정 페이지로 이동
		@GetMapping("/my/information")
		public String correctInfoBusiness() {
			return "mypage/business/mypageCorrectInfoBusiness";
		}
}
