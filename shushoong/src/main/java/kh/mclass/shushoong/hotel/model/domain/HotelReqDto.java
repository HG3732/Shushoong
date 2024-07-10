package kh.mclass.shushoong.hotel.model.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class HotelReqDto {
	
	private String hotelName;
	private String hotelEng;
	private String hotelCall;
	private String hotelNation;
	private String hotelLoc;
	private String hotelAddress;
	private String intro;
	private String checkIn;
	private String checkOut;
	
	private List<HotelRoomDto> roomList;
	

	private List<String> facilityList;
	private String policy;
	private String safety;
	
	
}
