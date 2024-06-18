package kh.mclass.shushoong.mypage.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CustomerManageDto {
	private String userId;
	private String userName;
	private String userEmail;
	private String userGrade;
	private String userStatus;
	private String joinDate;
	private Integer emailReceive;
	private Integer msgReceive;
	private String latestLogin;
}
