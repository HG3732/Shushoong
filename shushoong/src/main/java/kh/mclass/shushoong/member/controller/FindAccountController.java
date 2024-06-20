package kh.mclass.shushoong.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FindAccountController {
	
	@Autowired
	private final MemberService memberservice;
	
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
	
	// 사업자회원 아이디 찾기
	@PostMapping("find/business/id.ajax")
	@ResponseBody
	public String findIdBusiness(HttpServletRequest request, Model model, MemberDto memberDto,
								@RequestParam("userName") String userName,
								@RequestParam("userEmail") String userEmail,
								@RequestParam("userGrade") String userGrade) {
		try {
			memberDto.setUserName(userName);
			memberDto.setUserEmail(userEmail);
			memberDto.setUserGrade(userGrade);
			
			MemberDto findId = memberservice.findId(memberDto);
			model.addAttribute("userId", findId);
			
		} catch (Exception e) {
			model.addAttribute("msg", "오류가 발생되었습니다.");
			e.printStackTrace();
		}
		
		return "member/findbusinessInfo";
	}
}
