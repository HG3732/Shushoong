package kh.mclass.shushoong.home.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.hotel.model.domain.LatestHotelReviewDtoRes;

@Mapper
public interface HomeRepository {

	//할인 호텔 리스트
	public List<HotelDtoRes> selectDiscountHotelList();
	
	//최신 댓글 1개
	public LatestHotelReviewDtoRes selectLatestReview();
}
