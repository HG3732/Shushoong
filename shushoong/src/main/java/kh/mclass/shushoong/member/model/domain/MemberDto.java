package kh.mclass.shushoong.member.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MemberDto {
	private String userId;
	private String userName;
	private String userPwd;
	private String userEmail;
	private String userGrade;
	private String userStatus;
	private String joinDate;
	private Integer emailReceive;
	private Integer msgReceive;
	private String latestLogin;
}
