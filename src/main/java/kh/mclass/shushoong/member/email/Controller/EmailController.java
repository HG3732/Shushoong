package kh.mclass.shushoong.member.email.Controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import kh.mclass.shushoong.api_payload.CommonResponseDto;
import kh.mclass.shushoong.api_payload.status_code.SuccessStatus;
import kh.mclass.shushoong.member.email.dto.EmailRequestDto.EmailForVerificationRequest;
import kh.mclass.shushoong.member.email.dto.EmailRequestDto.VerificationCodeRequest;
import kh.mclass.shushoong.member.email.service.EmailService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmailController {
	private final EmailService emailService;
	
	@PostMapping("/verify-email")
    public CommonResponseDto<Void>
    getEmailForVerification(@RequestBody EmailForVerificationRequest request) {
        LocalDateTime requestedAt = LocalDateTime.now();
        emailService.sendSimpleVerificationMail(request.getUserEmail(), requestedAt);
        return CommonResponseDto.of(SuccessStatus._ACCEPTED, null);
    }
	
	@PostMapping("/v2/verify-email")
    public CommonResponseDto<Void>
    getEmailForVerificationV2(@RequestBody EmailForVerificationRequest request)
            throws MessagingException {
        LocalDateTime sentAt = LocalDateTime.now();
        emailService.sendVerificationMailWithTemplate(request.getUserEmail(), sentAt);
        return CommonResponseDto.of(SuccessStatus._ACCEPTED, null);
    }
	
	@PostMapping("/verification-code")
    public CommonResponseDto<String>
    verificationByCode(@RequestBody VerificationCodeRequest request) {
        LocalDateTime requestedAt = LocalDateTime.now();
        emailService.verifyCode(request.getCode(), requestedAt);
        return CommonResponseDto.ok("정상 인증 완료");
    }
}
