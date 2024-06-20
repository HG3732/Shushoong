package kh.mclass.shushoong.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.mclass.shushoong.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
//@PropertySource("classpath:/keyfiles/apikey.properties")
public class JoinController {
	
	@Autowired
	private final MemberService memberservice;
	
	
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
		log.info("userId : " + userId);
		return cnt;
	}

	// 일반유저 가입 페이지로 이동
	@GetMapping("join/customer")
	public String joinCustomer() {
		return "member/userJoin";
	}
	

	// 사업자회원 가입 페이지로 이동
	@GetMapping("join/business")
	public String joinBusiness() {
		return "member/businessJoin";
	}

}
