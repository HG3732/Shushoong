package kh.mclass.shushoong.member.email;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmailController {
	private final EmailService emailService;
	
	
}
