package kh.mclass.shushoong.member.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kh.mclass.shushoong.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	// 관리자 로그인
	@GetMapping("/login/admin")
	public String managerLogin(@RequestParam(value = "error", required = false) String error, Model model,
			@RequestParam(value = "exception", required = false) String exception) {

		String message = "";

		if (exception != null) {
			switch (exception) {
			case "BadCredentialsException":
				message = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
				break;
			case "InternalAuthenticationServiceException":
				message = "시스템 문제로 인해 요청을 처리할 수 없습니다. 운영자에게 문의해주세요.";
				break;
			case "UsernameNotFoundException":
				message = "존재하지 않는 계정입니다.";
				break;
			case "AuthenticationException":
				message = "정지된 회원입니다. 자세한 사항은 운영자에게 문의해 주세요.";
				break;
			default:
				message = "알 수 없는 이유로 로그인에 실패하였습니다.";
				break;
			}

			model.addAttribute("exception", exception);
			model.addAttribute("message", message);
		}

		return "mypage/admin/adminLogin";
	}

	// 로그인 페이지로 이동
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model,
			@RequestParam(value = "exception", required = false) String exception) {
		String message = "";

		if (exception != null) {
			switch (exception) {
			case "BadCredentialsException":
				message = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
				break;
			case "InternalAuthenticationServiceException":
				message = "시스템 문제로 인해 요청을 처리할 수 없습니다. 운영자에게 문의해주세요.";
				break;
			case "UsernameNotFoundException":
				message = "존재하지 않는 계정입니다.";
				break;
			case "AuthenticationException":
				message = "정지된 회원입니다. 자세한 사항은 운영자에게 문의해 주세요.";
				break;
			case "LockedException":
				message = "정지된 회원입니다. 자세한 사항은 운영자에게 문의해 주세요.";
				break;
			default:
				message = "로그인 위치를 확인해 주세요.";
				break;
			}

			model.addAttribute("exception", exception);
			model.addAttribute("message", message);
		}

		return "member/login";
	}
}

//$2a$10$xLB5O9SpSSGpXdTywkgRjuN0VBqv4lj1VYQz4GLJUeEPL0pxtujbK
//$2a$10$LrRWiThN4q/hDrvKSoVkMORbxLpuLA.ELQRNmBjvcTbH7yLY2D.6S,r,userName=customer,userPwd=$2a$10$LrRWiThN4q/hDrvKSoVkMORbxLpuLA.ELQRNmBjvcTbH7yLY2D.6S
