package kh.mclass.shushoong.mypage.business.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.hotel.model.domain.HotelReqDto;
import kh.mclass.shushoong.hotel.model.domain.HotelRoomDto;
import kh.mclass.shushoong.member.model.domain.MemberDto;

@Mapper
public interface MypageBusinessRepository {

	public MemberDto selectOne(String userId);

	// 비밀번호 체크
	public String pwdChecking(String userId);

	// 비밀번호 재설정
	public int resetPwd(Map<String, Object> paraMap);
	
	//사업자 등록
	public int insertCerti(String businessCerti, String businessRegit, String userId);
	
	//호텔 등록
	public int insertHotel(HotelReqDto dto);
	
	//호텔 사진 등록
	public int insertHotelPic(String hotelCode, List<String> urls);
	
	//호텔 편의 시설 등록
	public int insertHotelFac(String hotelCode, List<String> hotelFacs);
	
	//호텔 방 등록
	public int insertHotelRoom(String hotelCode, List<HotelRoomDto> rooms);
}
