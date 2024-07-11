package kh.mclass.shushoong.hotel.model.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class HotelReqDto {
	
	private String hotelCode;
	private String hotelName;
	private String hotelEng;
	private String hotelAddress;
	private String hotelCall;
	private String checkIn;
	private String checkOut;
	private String hotelNation;
	private String hotelLocCat;
	private String hotelIntro;
	
	private List<HotelRoomDto> roomList;

	private List<String> facilityList;
	private String hotelPolicy;
	private String hotelSafety;
	private String hotelPcount;
	private String businessCerti;
}
