package kh.mclass.shushoong.mypage.business.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.mypage.business.model.repository.MypageBusinessRepository;
import kh.mclass.shushoong.mypage.business.model.service.MypageBusinessService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/business")
@RequiredArgsConstructor
public class MypageBusinessController {

	@Autowired
	private MypageBusinessRepository repository;
	
	private final MypageBusinessService service;

	// 개인정보 수정 페이지로 이동
	@GetMapping("/my/information")
	public String correctInfoBusiness(Principal principal, ModelMap modelMap) {
		String userId = principal.getName();
		MemberDto dto = repository.selectOne(userId);
		modelMap.addAttribute("dto",dto);
		return "mypage/business/mypageBusinessCorrectInfo";
	}

	// 비밀번호 변경(암호화)
	@PostMapping("/changePwd.ajax")
	public String changePwd(@RequestParam("userId") String userID, 
							@RequestParam("userPwd") String userPwd,
							MemberDto dto) {
		
		dto.setUserId(userID);
		service.resetPwd(dto);
		
		return "redirect:/my/information";
	}

	// 사업장 관리 페이지 이동
	@GetMapping("/my/hotel")
	public String myBusinessHotel() {
		return "mypage/business/mypageBusinessHotel";
	}
	
	// 사업장 등록 페이지 이동
	@GetMapping("/my/hotel/register")
	public String myHotelRegister() {
		return "mypage/business/mypageBusinessHotelRegister";
	}
}
