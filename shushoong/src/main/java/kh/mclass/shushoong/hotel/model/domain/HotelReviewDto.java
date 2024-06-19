package kh.mclass.shushoong.hotel.model.domain;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Component
public class HotelReviewDto {
	private String userId;
	private String tripperCat;
	private String reviewTitle;
	private String reviewComment;
	private String reviewDate;
	private String hotelFacility;
	private String hotelClean;
	private String hotelConven;
	private String hotelKind;
	private String rateAvg;

}
