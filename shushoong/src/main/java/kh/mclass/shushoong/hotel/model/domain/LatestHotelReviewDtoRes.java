package kh.mclass.shushoong.hotel.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class LatestHotelReviewDtoRes {
	private String hotelCode;
	private String hotelPicture;
	private String reviewTitle;
	private String reviewComment;
}
