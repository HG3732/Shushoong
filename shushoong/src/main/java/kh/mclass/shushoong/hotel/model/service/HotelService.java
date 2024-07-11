 package kh.mclass.shushoong.hotel.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kh.mclass.shushoong.hotel.model.domain.HotelReviewDto;
import kh.mclass.shushoong.hotel.model.domain.HotelReviewOverallDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelFacilityDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelReserveDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelViewDtoRes;
import kh.mclass.shushoong.hotel.model.repository.HotelRepository;
import kh.mclass.shushoong.payment.PayDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {

	private final HotelRepository hotelRepository;
	
	public List<HotelDtoRes> selectHotHotelList() {
		return hotelRepository.selectHotHotelList();
	}
	
	public List<HotelDtoRes> selectAllHotelList(String loc, String people, String keyword, String maxPrice, String sortBy, String sortTo) {
		return hotelRepository.selectAllHotelList(loc, people, keyword, maxPrice, sortBy, sortTo);
	}
	
	public Integer selectMaxRoomlPrice(String loc, String people, String keyword) {
		return hotelRepository.selectMaxRoomlPrice(loc, people, keyword);
	}
	
	public List<String> selectLikeHotelList(String code, String userId){
		return hotelRepository.selectLikeHotelList(code, userId);
	}
	
	public Integer insertHotelLike(String userId, String hotelCode) {
		return hotelRepository.insertHotelLike(userId, hotelCode);
	}
	
	public Integer deleteHotelLike(String userId, String hotelCode) {
		return hotelRepository.deleteHotelLike(userId, hotelCode);
	}
	
	//호텔 상세정보
	public HotelViewDtoRes selectOneHotel(String hotelCode, String people) {
		return hotelRepository.selectOneHotel(hotelCode, people);
	}

	
	//해당 호텔 편의시설 정보 불러오기
	public List<HotelFacilityDtoRes> selectHotelFacility(String hotelCode) {
		return hotelRepository.selectHotelFacility(hotelCode);
	}
	
	//해당 호텔 리뷰 작성된거 불러오기
	public Map<String, Object> selectReviewDetailList(String hotelCode, int reviewNum, int reviewPageNum, int currentPageNum) {					
		
		//현재페이지: currentPage
		//리뷰 하단에 표시할 페이지 수: reviewPageNum
		//화면에 한번에 표시되는 리뷰부분 당 글 수 : reviewNum	
		Map<String, Object> result = null;
		
		//총 리뷰 개수
		//DB가서 그때그때 알아와야함 - 호텔 한개 당 리뷰글이 몇개냐에 따라 달라질 수 있음
		int totalReviewCount = hotelRepository.selectTotalPageCount(hotelCode);
		
		int startRounum = reviewNum * (currentPageNum - 1) + 1;
		int endRonum = reviewNum * currentPageNum;
		
//		전체페이지수(총 게시글 개수/한 페이지 당 글 수) => (총 게시글 개수%한 페이지 당 글 수== 0)?(총 게시글 개수/한 페이지 당 글 수):(총 게시글 개수/한 페이지 당 글 수+1)
		int totalPageCount = (totalReviewCount % reviewNum == 0) ? (totalReviewCount / reviewNum) : (totalReviewCount / reviewNum) + 1;
		// 조건문 - 앞에가 0이 맞으면 : 앞에꺼, 0이 아니면 : 뒤에꺼
		
		//시작페이지
		int startPageNum = (currentPageNum % reviewPageNum == 0) ? ((currentPageNum / reviewPageNum) - 1) * reviewPageNum + 1
				: (currentPageNum / reviewPageNum) * reviewPageNum + 1;
		
		//끝페이지
		int endPageNum = (startPageNum + reviewPageNum > totalPageCount) ? totalPageCount : startPageNum + reviewPageNum - 1;
		
		List<HotelReviewDto> reviewDtoList = hotelRepository.selectReviewDetailList(hotelCode, startRounum, endRonum);
		result = new HashMap<String, Object>();
		result.put("reviewDtoList", reviewDtoList);
		result.put("totalReviewCount", totalReviewCount);
		result.put("startPageNum", startPageNum);
		result.put("endPageNum", endPageNum);
		result.put("currentPage", currentPageNum);
		
		return result;
	}
	
	//해당 호텔 총리뷰 기록 불러오기
	public List<HotelReviewOverallDtoRes> selectReviewOverall(String hotelCode) {	
		return hotelRepository.selectReviewOverall(hotelCode);
	}
	
	//호텔 요청사항 조회
	public List<Map<String, String>> hotelRequestAll(){
		return hotelRepository.hotelRequestAll();
	}
	
	//호텔 예매 내역 먼저 저장
	public int insertReserveInfo(HotelReserveDtoRes reserverInfo){
		return hotelRepository.insertReserveInfo(reserverInfo);
	}
	
//	결제 정보
	public int insertPayInfo(PayDto paydto) {
		return hotelRepository.insertPayInfo(paydto);
	}

	//결제 정보 수정
	public int updatePayInfo(String hotelReserveCode, String approveNo){
		return hotelRepository.updatePayInfo(hotelReserveCode, approveNo);
	}
	
}
