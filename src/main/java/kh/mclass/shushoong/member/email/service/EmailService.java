package kh.mclass.shushoong.member.email.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kh.mclass.shushoong.api_payload.status_code.ErrorStatus;
import kh.mclass.shushoong.exception.GeneralException;
import kh.mclass.shushoong.member.email.dto.VerificationCode;
import kh.mclass.shushoong.member.email.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@PropertySource("classpath:/keyfiles/apikey.properties")
public class EmailService {
	
	@Value("${spring.mail.username}")
    private String serviceEmail;
	@Value("${spring.mail.templates.logo-path}")
    private Resource logoFile;
	
	// 인증번호 유효시간 5분 
	private final Integer EXPIRATION_TIME_IN_MINUTES = 5;
	private final String VERIFICATION_CODE_MAIL_SUBJECT="Email Verification For %s";
	
	private final JavaMailSender mailSender;
	private final VerificationCodeRepository verificationCodeRepository;
	private final SpringTemplateEngine templateEngine;
	
	
	// 인증고유시간 설정
	public void sendSimpleVerificationMail(String to, LocalDateTime sentAt) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(serviceEmail);
        mailMessage.setTo(to);
        mailMessage.setSubject(String.format("%s 로 발송된 인증번호 안내 메일입니다.", to));

        VerificationCode verificationCode = generateVerificationCode(sentAt);
        verificationCodeRepository.save(verificationCode);

        String text = verificationCode.generateCodeMessage();
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }
	
	public void verifyCode(String code, LocalDateTime verifiedAt) {
        VerificationCode verificationCode = verificationCodeRepository.findByCode(code)
                .orElseThrow(() -> new GeneralException(ErrorStatus._VERIFICATION_CODE_NOT_FOUND));

        if (verificationCode.isExpired(verifiedAt)) {
            throw new GeneralException(ErrorStatus._VERIFICATION_CODE_EXPIRED);
        }

        verificationCodeRepository.remove(verificationCode);
    }
	
	// 이메일 인증 코드 생성
	// 변환한 uuid를 해시 함수를 이용하여 바이트로 변환
	// 변환한 바이트에 대해 앞 4글자의 각 바이트를 2자리의 16진수 문자열로 저장
	private VerificationCode generateVerificationCode(LocalDateTime sendAt) {
//		String code = UUID.randomUUID().toString();
		String uuidString = UUID.randomUUID().toString();
		byte[] uuidStringBytes = uuidString.getBytes(StandardCharsets.UTF_8);
		byte[] hashBytes;
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			hashBytes = messageDigest.digest(uuidStringBytes);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		
		StringBuilder codeBuilder = new StringBuilder();
		for (int i=0; i<4; i++) {
			codeBuilder.append(String.format("%02x", hashBytes[i]));
		}
		
		String code = codeBuilder.toString();
		return VerificationCode.builder()
				.code(code)
				.createAt(sendAt)
				.expirationTimeInMinutes(EXPIRATION_TIME_IN_MINUTES)
				.build();
	}
	
	// HTML 템플릿 발송
	public void sendVerificationMailWithTemplate(String to, LocalDateTime sendAt) 
			throws MessagingException {
		
		VerificationCode verificationCode = generateVerificationCode(sendAt);
		verificationCodeRepository.save(verificationCode);
		
		HashMap<String, Object> templateModel = new HashMap<>();
		templateModel.put("verificationCode", verificationCode.generateCodeMessage());
		
		String subject = String.format(VERIFICATION_CODE_MAIL_SUBJECT, to);
		Context thymeleafContext = new Context();
		thymeleafContext.setVariables(templateModel);
		String htmlBody = templateEngine.process("/member/mail/mailTemplate.html", thymeleafContext);
		
		sendHtmlMessage(to, subject, htmlBody);
	}
	
	// HTML 메일 발송
	private void sendHtmlMessage(String to, String subject, String htmlBody)
            throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(serviceEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        helper.addInline("logo.png", logoFile);

        mailSender.send(message);
    }

}
