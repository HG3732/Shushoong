package kh.mclass.shushoong.mypage.admin.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProductDtoRes {
	private String hotelCode;
	private String hotelName;
	private String hotelEng;
	private String hotelAddress;
	private String hotelCall;
	private String hotelCheckIn;
	private String hotelCheckOut;
	private String hotelPolicy;
	private String hotelIntro;
	private int hotelPcount;
	private String hotelLocCat;
	private int businessNum;
	private String hotelSafety;
	private String userId;
}
