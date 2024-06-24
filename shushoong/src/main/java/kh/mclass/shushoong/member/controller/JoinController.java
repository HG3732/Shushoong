package kh.mclass.shushoong.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.member.model.domain.MemberRole;
import kh.mclass.shushoong.member.model.service.MemberService;
import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
//@PropertySource("classpath:/keyfiles/apikey.properties")
public class JoinController {
	
	@Autowired
	private final MemberService memberservice;
	
	private final BCryptPasswordEncoder bcrypt;	
	
	// 회원가입 메인 페이지로 이동
	@GetMapping("join")
	public String join() {
		return "member/joinHome";
	}
	
	// 아이디 체크
	@PostMapping("/idcheck.ajax")
	@ResponseBody
	public int idCheck(@RequestParam("userId") String userId) {
		int cnt = memberservice.idCheck(userId);
		return cnt;
	}

	// 일반유저 가입 페이지로 이동
	@GetMapping("join/customer")
	public String joinCustomer(Model model) {
		model.addAttribute("memberAdd");
		return "member/userJoin";
	}
	
	// 일반유저 회원가입
	@PostMapping("signup/customer")
	@ResponseBody
	public String singupCustomer(MemberDto memberDto) {
				
		memberDto.setUserId(memberDto.getUserId());
		memberDto.setUserName(memberDto.getUserName());
		memberDto.setUserPwd(bcrypt.encode(memberDto.getUserPwd()));
		memberDto.setUserEmail(memberDto.getUserEmail());
		memberDto.setUserGrade("customer");
		memberDto.setUserStatus("1");
		
		return "redirect:/member/login";
	}

	// 사업자회원 가입 페이지로 이동
	@GetMapping("join/business")
	public String joinBusiness() {
		return "member/businessJoin";
	}
	
	@PostMapping("signup/business")
	@ResponseBody
	public String singupBusiness(MemberDto memberDto) {
				
		memberDto.setUserId(memberDto.getUserId());
		memberDto.setUserName(memberDto.getUserName());
		memberDto.setUserPwd(bcrypt.encode(memberDto.getUserPwd()));
		memberDto.setUserEmail(memberDto.getUserEmail());
		memberDto.setUserGrade("business");
		memberDto.setUserStatus("1");
		
		
		return "redirect:/member/login";
	}
}
