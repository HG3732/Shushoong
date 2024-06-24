package kh.mclass.shushoong.mypage.admin.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BusinessManageDto {
	private String userId;
	private String userName;
	private String userEmail;
	private String userGrade;
	private String userStatus;
	private String joinDate;
	private String latestLogin;
	private Integer businessNum;
	private String hotelName;
}
