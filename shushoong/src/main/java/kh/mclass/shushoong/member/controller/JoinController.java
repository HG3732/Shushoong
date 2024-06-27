package kh.mclass.shushoong.member.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.member.model.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@AllArgsConstructor
@Slf4j
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
	@PostMapping(value = "join/customer")
	public String singupCustomer(MemberDto memberDto, BindingResult bindingResult,
								ModelAndView mav) {
		if (bindingResult.hasErrors()) {
            return "member/userJoin";
        }
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mi:ss");
		
		Date now = new Date();
		String joinDate = sdf1.format(now);
		String loginDate = sdf2.format(now);
		
		memberDto.setUserId(memberDto.getUserId());
		memberDto.setUserName(memberDto.getUserName());
		memberDto.setUserPwd(bcrypt.encode(memberDto.getUserPwd()));
		memberDto.setUserEmail(memberDto.getUserEmail());
		memberDto.setUserGrade("customer");
		memberDto.setUserStatus(1);
		memberDto.setJoinDate(joinDate);
		memberDto.setMsgReceive(memberDto.getMsgReceive());
		memberDto.setEmailReceive(memberDto.getEmailReceive());
		memberDto.setLatestLogin(loginDate);
		
		log.info("member Information = " + memberDto);
		
		memberservice.join(memberDto);
		
		return "redirect:/login";
	}

	// 사업자회원 가입 페이지로 이동
	@GetMapping("join/business")
	public String joinBusiness() {
		return "member/businessJoin";
	}
	
	@PostMapping(value = "join/business")
	public String singupBusiness(MemberDto memberDto, BindingResult bindingResult,
			ModelAndView mav) {
		if (bindingResult.hasErrors()) {
            return "member/userJoin";
        }
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mi:ss");
		
		Date now = new Date();
		String joinDate = sdf1.format(now);
		String loginDate = sdf2.format(now);
		
		memberDto.setUserId(memberDto.getUserId());
		memberDto.setUserName(memberDto.getUserName());
		memberDto.setUserPwd(bcrypt.encode(memberDto.getUserPwd()));
		memberDto.setUserEmail(memberDto.getUserEmail());
		memberDto.setUserGrade("business");
		memberDto.setUserStatus(1);
		memberDto.setJoinDate(joinDate);
		memberDto.setMsgReceive(0);
		memberDto.setEmailReceive(0);
		memberDto.setLatestLogin(loginDate);
		
		memberservice.join(memberDto);
		
		log.info("member Information = " + memberDto);
		
		return "redirect:/login";
	}
}
