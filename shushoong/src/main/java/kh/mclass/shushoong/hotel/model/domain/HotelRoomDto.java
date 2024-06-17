package kh.mclass.shushoong.hotel.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class HotelRoomDto {
	private String hotelCode;
	private String roomCat;
	private String hotelPrice;
	private String hotelDiscount;
	private String roomCount;
	private String roomCap;
	private String roomAtt;
}
