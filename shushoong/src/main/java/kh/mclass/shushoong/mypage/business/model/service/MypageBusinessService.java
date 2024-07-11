package kh.mclass.shushoong.mypage.business.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.hotel.model.domain.HotelReqDto;
import kh.mclass.shushoong.hotel.model.domain.HotelRoomDto;
import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.mypage.business.model.repository.MypageBusinessRepository;

@Service
public class MypageBusinessService {

	@Autowired
	private MypageBusinessRepository mypageRepository;

	public MemberDto selectOne(String userId) {
		return mypageRepository.selectOne(userId);
	}

	// 비밀번호 체크
	public String pwdChecking(String userId) {
		return mypageRepository.pwdChecking(userId);
	}

	// 비밀번호 재설정
	public int resetPwd(Map<String, Object> paraMap) {
		return mypageRepository.resetPwd(paraMap);
	}
	
	//사업자 등록
	public int insertCerti(String businessCerti, String businessRegit, String userId) {
		return mypageRepository.insertCerti(businessCerti, businessRegit, userId);
	}
	
	//호텔 등록
	public int insertHotel(HotelReqDto dto) {
		return mypageRepository.insertHotel(dto);
	}
	
	//호텔 사진 등록
	public int insertHotelPic(String hotelCode, List<String> urls) {
		return mypageRepository.insertHotelPic(hotelCode, urls);
	}
	
	//호텔 등록
	public int insertHotelFac(String hotelCode, List<String> hotelFacs) {
		return mypageRepository.insertHotelFac(hotelCode, hotelFacs);
	}
	
	//호텔 등록
	public int insertHotelRoom(String hotelCode, List<HotelRoomDto> rooms) {
		return mypageRepository.insertHotelRoom(hotelCode, rooms);
	}
}
