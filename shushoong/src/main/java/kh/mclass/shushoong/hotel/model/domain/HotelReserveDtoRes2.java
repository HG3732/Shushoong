package kh.mclass.shushoong.hotel.model.domain;


import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class HotelReserveDtoRes2 {
	private String hotelReserveCode;
	private String residenceNameKo;
	private String hotelCode;

	private String roomCatDesc;
	private String hotelName;
}
