package kh.mclass.shushoong.member.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kh.mclass.shushoong.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FindAccountController {

	@Autowired
	private final MemberService memberservice;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

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

	// 일반회원 아이디 찾기
	@PostMapping("find/customer/id.ajax")
	@ResponseBody
	public String findIdCustomer(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail,
			@RequestParam("userGrade") String userGrade) {

		String result = "";

		try {
			result = memberservice.findId(userName, userEmail, userGrade);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@PostMapping("find/customer/pwd.ajax")
	@ResponseBody
	public int resetPasswordCustomer(@RequestParam("userId") String userId, @RequestParam("userEmail") String userEmail,
			@RequestParam("userGrade") String userGrade) {
		int result = 0;

		try {
			result = memberservice.findPwd(userId, userEmail, userGrade);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 사업자 회원 아이디/비밀번호 찾기 페이지로 이동
	@GetMapping("find/business")
	public String findInfoBusiness() {
		return "member/findbusinessInfo";
	}

	// 사업자회원 아이디 찾기
	@PostMapping("find/business/id.ajax")
	@ResponseBody
	public String findIdBusiness(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail,
			@RequestParam("userGrade") String userGrade) {

		String result = "";

		try {
			result = memberservice.findId(userName, userEmail, userGrade);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@PostMapping("find/business/pwd.ajax")
	@ResponseBody
	public int resetPasswordBusiness(@RequestParam("userId") String userId, @RequestParam("userEmail") String userEmail,
			@RequestParam("userGrade") String userGrade) {
		int cnt = 0;

		try {
			cnt = memberservice.findPwd(userId, userEmail, userGrade);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@GetMapping("reset/password")
	public String resetPassword(ModelAndView modelAndView, HttpSession httpSession, HttpServletRequest requese) {
		return "member/passwordReset";
	}

	// 비밀번호 변경(암호화)
	@PostMapping("/changePwd.ajax")
	public String changePwd(@RequestParam("userPwd") String userPwd, RedirectAttributes rttr,
			@RequestParam Map<String, Object> paramMap) {
		
		String userId;
		paramMap.put("userPwd", encoder.encode(userPwd));
//		paramMap.put("userId", userId);
		int result = memberservice.resetPwd(paramMap);

		String message = null;

		if (result > 0) {
			message = "비밀번호가 변경되었습니다.";

		} else {
			message = "비밀번호 변경에 실패했습니다.";
		}

		rttr.addFlashAttribute("message", message);
		return "redirect:/business/my/information";
	}
}
