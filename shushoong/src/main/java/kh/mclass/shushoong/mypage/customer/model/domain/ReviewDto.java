package kh.mclass.shushoong.mypage.customer.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ReviewDto {
	private String approveNo;
	private String hotelReserveCode;
	private String reviewTitle;
	private String reviewComment;
	private String userId;
	private String hotelFacility;
	private String hotelClean;
	private String hotelConven;
	private String hotelKind;
	private String tripperCat;
}
