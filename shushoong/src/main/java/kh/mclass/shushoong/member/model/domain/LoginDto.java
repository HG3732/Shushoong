package kh.mclass.shushoong.member.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class LoginDto {
	private String userId;
	private String userPwd;
	private String userGrade;
	private String userStatus;
}
