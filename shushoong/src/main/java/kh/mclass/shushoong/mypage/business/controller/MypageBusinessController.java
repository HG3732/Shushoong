package kh.mclass.shushoong.mypage.business.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.mypage.business.model.repository.MypageBusinessRepository;
import kh.mclass.shushoong.mypage.business.model.service.MypageBusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/business")
@RequiredArgsConstructor
@Slf4j
public class MypageBusinessController {

	@Autowired
	private MypageBusinessRepository repository;

	private final MypageBusinessService service;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();	
	
	// 비밀번호 확인 페이지로 이동
	@GetMapping("/check/pwd")
	public String checkPwd() {
		return "mypage/business/mypageCheckPwd";
	}

	@PostMapping(value = "/check/password")
	public String PwdChecking(@RequestParam("userPwd") String userPwd, Authentication auth, RedirectAttributes rttr) {

		User user = (User) auth.getPrincipal();
		String member = repository.pwdChecking(user.getUsername());
		if (encoder.matches(userPwd, member)) {
			log.info("password 확인 완료");
			return "redirect:/customer/my/information";
		} else {
			rttr.addFlashAttribute("message", "오류");
			return "redirect:/customer/check/password";
		}
	}

	// 개인정보 수정 페이지로 이동
	@GetMapping("/my/information")
	public String correctInfoBusiness(Principal principal, ModelMap modelMap) {
		String userId = principal.getName();
		MemberDto dto = repository.selectOne(userId);
		modelMap.addAttribute("dto", dto);
		return "mypage/business/mypageBusinessCorrectInfo";
	}

	// 비밀번호 변경(암호화)
	@PostMapping("/changePwd.ajax")
	public String changePwd(@RequestParam("userPwd") String userPwd, 
							Principal principal, RedirectAttributes rttr,
							@RequestParam Map<String, Object> paramMap) {

		String userId = principal.getName();
		paramMap.put("userPwd", encoder.encode(userPwd));
		paramMap.put("userId", userId);
		int result = service.resetPwd(paramMap);
		
		String message = null;
		
		if(result > 0) {
			message = "비밀번호가 변경되었습니다.";
			
		} else {
			message = "비밀번호 변경에 실패했습니다.";
		}
		
		rttr.addFlashAttribute("message", message);
		return "redirect:/business/my/information";
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
