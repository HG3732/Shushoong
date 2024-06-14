package kh.mclass.shushoong.hotel.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class HotelReviewOverallDtoRes {

	private String reviewCount;
	private String avgHotelFacility;
	private String avgHotelClean;
	private String avgHotelConven;
	private String avgHotelKind;
	private String avgAllRate;
}
