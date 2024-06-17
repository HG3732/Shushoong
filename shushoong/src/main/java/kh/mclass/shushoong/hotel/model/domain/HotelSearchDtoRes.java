package kh.mclass.shushoong.hotel.model.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class HotelSearchDtoRes {
	private String hotelCode;
	private List<HotelPic> hotelPic;
	private String hotelName;
	private String hotelEng;
	private String hotelAddress;
	private List<HotelRoomDto> hotelRooms;
	private String hotelPrice;
	private String hotelCall;
	private String hotelCheckIn;
	private String hotelCheckOut;
	private String hotelIntro;
	private String hotelPolicy;
	private String hotelSafety;
}
