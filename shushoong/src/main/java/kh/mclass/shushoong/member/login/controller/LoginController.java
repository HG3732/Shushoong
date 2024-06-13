package kh.mclass.shushoong.member.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
public class LoginController {

	// 로그인 페이지로 이동
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false)String error, 
			@RequestParam(value = "exception", required = false)String exception, 
			Model model) {
		
		String message="";
		
		if(exception != null) {
			switch(exception) {
			case "BadCredentialsException":
				message = "이메일 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
				break;
			case "InternalAuthenticationServiceException":
				message = "시스템 문제로 인해 요청을 처리할 수 없습니다. 관리자에게 문의해주세요.";
				break;
			case "UsernameNotFoundException":
				message = "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요.";
				break;
			case "AuthenticationException":
				message = "탈퇴 처리된 회원입니다. 관리자에게 문의해주세요.";
				break;
			default:
				message = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의해주세요.";
				break;
			}
		}
		
        model.addAttribute("message", message);
		return "member/login";
	}
}
