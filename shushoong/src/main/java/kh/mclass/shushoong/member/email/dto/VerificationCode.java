package kh.mclass.shushoong.member.email.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class VerificationCode {
	private String code;
	private LocalDateTime createAt; 
	private Integer expirationTimeInMinutes;
	
	// 만료시간 유무 
	public boolean isExpired(LocalDateTime verifiedAt) {
		LocalDateTime expiredAt = createAt.plusMinutes(expirationTimeInMinutes);
		return verifiedAt.isAfter(expiredAt);
	}
	
	
	// 식별 ID, 인증코드, 만료시간 필드 
	public String generateCodeMessage() {
		String formattedExpiredAt = createAt
				.plusMinutes(expirationTimeInMinutes)
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		return String.format(
                """
                        %s
                                """,
                code
        );
	}
}
