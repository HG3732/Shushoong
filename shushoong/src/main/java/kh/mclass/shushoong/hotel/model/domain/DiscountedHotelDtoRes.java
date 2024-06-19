package kh.mclass.shushoong.hotel.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class DiscountedHotelDtoRes {
	private String hotelCode;
	private String hotelPic;
	private String hotelName;
	private String hotelEng;
	private String hotelAddress;
	private String roomCat;
	private String hotelPrice;
	private String discountedPrice;
	private String discountRatio;
	private String roomCount;
}
