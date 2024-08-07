package kh.mclass.shushoong.mypage.customer.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelReviewDto;
import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.mypage.customer.model.domain.ReviewDto;
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
	
	// 탈퇴
	public int updateLockAccount(String usedId) {
		return mypageRepository.secessionAccount(usedId);
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
	public Map<String, Object> selectOneReservedHotel(String userId, String hotelReserveCode) {
		//예매내역 상세는 예매 한개에 대한 내용이므로 map 하나만 있어도 뭐..
		return mypageRepository.selectOneReservedHotel(userId, hotelReserveCode);
	}
	
	//리뷰 등록
	public int insertReview(ReviewDto dto) {
		return mypageRepository.insertReview(dto);
	}
	
	//리뷰 가능 상태 업데이트
	public int updateReviewAvailable(String hotelReserveCode) {
		return mypageRepository.updateReviewAvailable(hotelReserveCode);
	}
	
	//호첼 예약 취소하기
	public int cancelHotelReserve(String paymentId) {
		return mypageRepository.cancelHotelReserve(paymentId);
	}
	
	public int increaseRoom(String hotelCode, String room, String roomCat, String roomCap, String roomAtt) {
		return mypageRepository.increaseRoom(hotelCode, room, roomCat, roomCap, roomAtt);
	};
	
	//호텔 취소 상세정보
	public Map<String, Object> selectOneCancelHotel(String userId, String hotelReserveCode) {
		//예매내역 상세는 예매 한개에 대한 내용이므로 map 하나만 있어도 뭐..
		return mypageRepository.selectOneCancelHotel(userId, hotelReserveCode);
	}
	
	//항공 예약 리스트
	public List<Map<String, String>> selectReservedAirlineList(String userId) {
		//호텔 예약 정보에 나오는 여러개의 정보를 map으로 묶는데 한 사람이 호텔을 여러개 결제하면 이 map이 여러개 있을 수 있기 때문에 list로 감싸기
		return mypageRepository.selectReservedAirlineList(userId);
	}

	//항공 취소 리스트
	public List<Map<String, String>> selectCancelAirlineList(String userId) {
		//호텔 예약 정보에 나오는 여러개의 정보를 map으로 묶는데 한 사람이 호텔을 여러개 결제하면 이 map이 여러개 있을 수 있기 때문에 list로 감싸기
		return mypageRepository.selectCancelAirlineList(userId);
	}
	
	//항공 예약 상세정보 selectOneReservedAirline
	public List<Map<String, Object>> selectOneReservedAirline(String userId, String airlineCode, String airlineReserveCode) {
		//예매내역 상세는 예매 한개에 대한 내용이므로 map 하나만 있어도 뭐..
		return mypageRepository.selectOneReservedAirline(userId, airlineCode, airlineReserveCode);
	}
	
	//항공 예약 취소하기
	public int cancelAirlineReserve(String paymentId) {
		return mypageRepository.cancelAirlineReserve(paymentId);
	}
	
	//항공 취소 상세정보
	public List<Map<String, Object>> selectOneCancelAirline(String userId, String airlineCode, String airlineReserveCode) {
		//호텔 예약 정보에 나오는 여러개의 정보를 map으로 묶는데 한 사람이 호텔을 여러개 결제하면 이 map이 여러개 있을 수 있기 때문에 list로 감싸기
		return mypageRepository.selectOneCancelAirline(userId, airlineCode, airlineReserveCode);
	}
	
	//좋아요
	public List<HotelDtoRes> selectListInterestedHotel(String userId){
		return mypageRepository.selectListInterestedHotel(userId);
	}
	
	public int deleteHotelLiked(String hotelCode) {
		return mypageRepository.deleteHotelLiked(hotelCode);
	}
	
	public List<Map<String, String>> selectListHotelReview(String userId) {
		return mypageRepository.selectListHotelReview(userId);
	}
	
	public int deleteOneHotelReview(String userId,String hotelResCode) {
		return mypageRepository.deleteOneHotelReview(userId,hotelResCode);
	}
}
