package kh.mclass.shushoong.member.model.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MemberDto {
	private String userId;
	private String userName;
	private String userPwd;
	private String userEmail;
	private MemberRole userGrade;
	private String userStatus;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private String joinDate;
	private Integer emailReceive;
	private Integer msgReceive;
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	private String latestLogin;
}
