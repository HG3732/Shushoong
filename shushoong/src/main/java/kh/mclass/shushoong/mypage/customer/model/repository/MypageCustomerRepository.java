package kh.mclass.shushoong.mypage.customer.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.member.model.domain.MemberDto;

@Mapper
public interface MypageCustomerRepository {

	// 로그인 정보 불러오기 
	public MemberDto selectOne(String userId);
	
	// 비밀번호 체크
	public String pwdChecking(String userId);
	
	// 비밀번호 재설정
	public int resetInfo(Map<String, Object> paramMap);
	
	//호텔 예약 리스트
	public List<Map<String, String>> selectReservedHotelList(String userId);
	
	//호텔 예약 상세정보
	public Map<String, Object> selectOneReservedHotelList(String userId, String hotelReserveCode);
	
	public List<HotelDtoRes> selectListInterestedHotel(String userId);

}
