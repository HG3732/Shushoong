package kh.mclass.shushoong.mypage.customer.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.mypage.customer.model.repository.MypageCustomerRepository;

@Service
public class MypageCustomerService {

	@Autowired
	private MypageCustomerRepository mypageRepository;

	private BCryptPasswordEncoder bcrypt;

	public MemberDto selectOne(String userId) {
		return mypageRepository.selectOne(userId);
	}

	// 비밀번호 체크
	public String pwdChecking(String userId) {
		return mypageRepository.pwdChecking(userId);
	}
	
	// 비밀번호 재설정
	public int resetInfo(Map<String, Object> paramMap) {
		return mypageRepository.resetInfo(paramMap);
	}
	
	//호텔 예약 리스트
	public List<Map<String, String>> selectReservedHotelList(String userId) {
		//호텔 예약 정보에 나오는 여러개의 정보를 map으로 묶는데 한 사람이 호텔을 여러개 결제하면 이 map이 여러개 있을 수 있기 때문에 list로 감싸기
		return mypageRepository.selectReservedHotelList(userId);
	}
	
	//호텔 취소 리스트
	public List<Map<String, String>> selectCancelHotelList(String userId) {
		//호텔 예약 정보에 나오는 여러개의 정보를 map으로 묶는데 한 사람이 호텔을 여러개 결제하면 이 map이 여러개 있을 수 있기 때문에 list로 감싸기
		return mypageRepository.selectCancelHotelList(userId);
	}
	
	//호텔 예약 상세정보
	public Map<String, Object> selectOneReservedHotelList(String userId, String hotelReserveCode) {
		//예매내역 상세는 예매 한개에 대한 내용이므로 map 하나만 있어도 뭐..
		return mypageRepository.selectOneReservedHotelList(userId, hotelReserveCode);
	}
	
	
	
	
	//호첼 예약 취소
	public int cancelHotelReserve(String paymentId) {
		return mypageRepository.cancelHotelReserve(paymentId);
	}
	
	//좋아요
	public List<HotelDtoRes> selectListInterestedHotel(String userId){
		return mypageRepository.selectListInterestedHotel(userId);
	}
	
	public int deleteHotelLiked(String hotelCode) {
		return mypageRepository.deleteHotelLiked(hotelCode);
	}
	
}
