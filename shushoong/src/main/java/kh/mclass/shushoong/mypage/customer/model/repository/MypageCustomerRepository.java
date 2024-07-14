package kh.mclass.shushoong.mypage.customer.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelReviewDto;
import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.mypage.customer.model.domain.ReviewDto;

@Mapper
public interface MypageCustomerRepository {

	// 로그인 정보 불러오기
	public MemberDto selectOne(String userId);

	// 비밀번호 체크
	public String pwdChecking(String userId);

	// 비밀번호 재설정
	public int resetInfo(Map<String, Object> paramMap);

	// 회원 탈퇴
	public int secessionAccount(String usedId);

	// 호텔 예약 리스트
	public List<Map<String, String>> selectReservedHotelList(String userId);

	// 호텔 취소 리스트
	public List<Map<String, String>> selectCancelHotelList(String userId);

	// 호텔 예약 상세정보
	public Map<String, Object> selectOneReservedHotel(String userId, String hotelReserveCode);

	//리뷰 등록
	public int insertReview(ReviewDto dto);
	
	//리뷰 등록 상태 업데이트
	public int updateReviewAvailable(String hotelReserveCode);
	
	// 호텔 예약 취소
	public int cancelHotelReserve(String paymentId);

	//취소 시 방 갯수 증가
	public int increaseRoom(String hotelCode, String room, String roomCat, String roomCap, String roomAtt);
	
	// 호텔 취소 상세정보
	public Map<String, Object> selectOneCancelHotel(String userId, String hotelReserveCode);

	// 항공 예약리스트
	public List<Map<String, String>> selectReservedAirlineList(String userId);

	// 항공 취소 예약리스트
	public List<Map<String, String>> selectCancelAirlineList(String userId);

	// 항공 상세정보
	public List<Map<String, Object>> selectOneReservedAirline(String userId, String airlineCode, String airlineReserveCode);
	
	// 항공 예약 취소
	public int cancelAirlineReserve(String paymentId);
	
	// 항공 취소 상세정보
	public List<Map<String, Object>> selectOneCancelAirline(String userId, String airlineCode, String airlineReserveCode);
	
	// 숙소 좋아요
	public List<HotelDtoRes> selectListInterestedHotel(String userId);

	public int deleteHotelLiked(String hotelCode);

	public List<Map<String, String>> selectListHotelReview(String userId);

	public int deleteOneHotelReview(String userId, String hotelResCode);
	


}
