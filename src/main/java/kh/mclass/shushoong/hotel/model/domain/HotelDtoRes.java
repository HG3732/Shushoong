package kh.mclass.shushoong.hotel.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class HotelDtoRes {
	private String hotelName;
	private String hotelEng;
	private String hotelAddress;
	private String hotelPic;
	private String hotelScore;
	private String hotelPrice;
}
