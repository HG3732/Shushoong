package kh.mclass.shushoong.home.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kh.mclass.shushoong.home.model.repository.HomeRepository;
import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.hotel.model.domain.LatestHotelReviewDtoRes;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeService {

	private final HomeRepository homeRepository;
	
	public List<HotelDtoRes> selectDiscountHotelList() {
		return homeRepository.selectDiscountHotelList();
	}
	
	public LatestHotelReviewDtoRes selectLatestReview() {
		return homeRepository.selectLatestReview();
	}
}
