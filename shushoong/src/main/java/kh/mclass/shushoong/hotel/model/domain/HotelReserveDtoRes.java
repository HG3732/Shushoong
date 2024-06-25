package kh.mclass.shushoong.hotel.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class HotelReserveDtoRes {

	private String hotelReserveCode;
	private String reserveName;
	private String reserveEmail;
	private String residenceNameKo;
	private String residenceNameEng;
	private String residencePhone;
	private String request;
	private String reserveCheckIn;
	private String reserveCheckOut;
	private String userId;
	private String roomCap;
	private String hotelCode;
	private String roomCat;
	private String roomAtt;
	
}
