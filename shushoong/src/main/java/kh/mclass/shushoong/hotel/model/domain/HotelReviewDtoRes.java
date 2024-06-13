package kh.mclass.shushoong.hotel.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class HotelReviewDtoRes {
	private String roomCat;
	private String hotelComment;
	private String hotelFacility;
	private String hotelClean;
	private String hotelConven;
	private String hotelKind;
	private String tripperCat;
}
