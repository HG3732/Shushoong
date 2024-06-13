package kh.mclass.shushoong.member.email.dto;

import lombok.Getter;

public class EmailRequestDto {
	@Getter
	public static class EmailForVerificationRequest {
		private String userEmail;
	}
	
	@Getter
	public static class VerificationCodeRequest {
		private String code;
	}
}
