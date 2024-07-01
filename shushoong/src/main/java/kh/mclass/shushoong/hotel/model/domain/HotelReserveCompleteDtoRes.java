package kh.mclass.shushoong.hotel.model.domain;


import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class HotelReserveCompleteDtoRes {
	private String hotelReserveCode;
	private String reserveCheckIn;
	private String reserveCheckOut;
	private String roomCatDesc;
	private String roomAttDesc;
	private List<String> request;
	private String residenceNameKo;
	private String hotelPrice;
	
}
