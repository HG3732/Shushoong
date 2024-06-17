package kh.mclass.shushoong.hotel.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.hotel.model.domain.HotelPic;
import kh.mclass.shushoong.hotel.model.domain.HotelReviewOverallDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelReviewDto;
import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelFacilityDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelRoomDto;
import kh.mclass.shushoong.hotel.model.domain.HotelSearchDtoRes;

@Mapper
public interface HotelRepository {
	
	//Hot 호텔 리스트
	public List<HotelDtoRes> selectHotHotelList();
	//호텔 리스트
	public List<HotelDtoRes> selectAllHotelList(String code, String people, String keyword, String maxPrice, String sortBy, String sortTo);
	//호텔 리스트중 최고가(슬라이드 바 표시용)
	public Integer selectMaxRoomlPrice(String code, String people, String keyword);
	
//	public List<HotelPic> selectHotelPicList(String hotelCode);
//	
//	public List<HotelRoomDto> selectRoomList(String hotelCode);
	
	public List<HotelSearchDtoRes> selectHotelSearchList(String hotelCode);
	
	public List<HotelFacilityDtoRes> selectHotelFacility(String hotelCode);
	
	public List<HotelReviewDto> selectReviewDetailList(String hotelCode);
	
	public List<HotelReviewOverallDtoRes> selectReviewOverall(String hotelCode);
	
}
