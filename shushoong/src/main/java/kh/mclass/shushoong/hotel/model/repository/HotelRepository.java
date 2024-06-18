package kh.mclass.shushoong.hotel.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.hotel.model.domain.HotelReviewOverallDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelReviewDto;
import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelFacilityDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelViewDtoRes;

@Mapper
public interface HotelRepository {
	
	//Hot 호텔 리스트
	public List<HotelDtoRes> selectHotHotelList();
	//호텔 리스트
	public List<HotelDtoRes> selectAllHotelList(String code, String people, String keyword, String maxPrice, String sortBy, String sortTo);
	//호텔 리스트중 최고가(슬라이드 바 표시용)
	public Integer selectMaxRoomlPrice(String code, String people, String keyword);
	//호텔 리스트에 좋아요 표시용 리스트 검색
	public List<String> selectLikeHotelList(String code, String userId);
	//좋아요 추가
	public Integer insertHotelLike(String userId, String hotelCode);
	//좋아요 삭제
	public Integer deleteHotelLike(String userId, String hotelCode);
//	public List<HotelPic> selectHotelPicList(String hotelCode);
//	
//	public List<HotelRoomDto> selectRoomList(String hotelCode);
	
	//호텔 상세페이지
	public HotelViewDtoRes selectOneHotel(String hotelCode);
	
	//호텔 상세페이지 시설정보
	public List<HotelFacilityDtoRes> selectHotelFacility(String hotelCode);
	
	//호텔 상세페이지 리뷰
	// 리뷰 list 전체 수 구하기 - 페이지 수 계산하려함
	//ex. 리뷰글이 총 10개면 2개로 나눈다 했을때 총 5페이지 나옴
	//총 페이지 수가 결정됨 - 숫자만 뽑는거
	public List<HotelReviewDto> selectReviewDetailList(String hotelCode);
//	String currentPage									(int start, int end);
	
	
	// 페이지 당 나오는 게시글 수 뽑기
	//위에꺼 토대로 얘가 배치됨 - 게시글 관련 데이터까지 뽑는거
//	public List<HotelReviewDto> selectReviewPage(int start, int end);	
	
	
	//호텔 상세페이지 전체평균리뷰
	public List<HotelReviewOverallDtoRes> selectReviewOverall(String hotelCode);
	
}
