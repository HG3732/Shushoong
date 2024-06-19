package kh.mclass.shushoong.home.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;

@Mapper
public interface HomeRepository {

	//할인 호텔 리스트
	public List<HotelDtoRes> selectDiscountHotelList();
}
