package kh.mclass.shushoong.hotel.model.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class HotelSearchDtoRes {
	private List<String> hotelPic;
	private String hotelName;
	private String hotelEng;
	private String hotelAddress;
	private String hotelPrice;
	private String hotelCall;
	private String hotelCheckIn;
	private String hotelCheckOut;
	private String hotelPolicy;
	private String hotelIntro;
	private List<HotelRoomDto> hotelRooms;
}
