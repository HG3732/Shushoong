 package kh.mclass.shushoong.hotel.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kh.mclass.shushoong.hotel.model.domain.HotelReviewDto;
import kh.mclass.shushoong.hotel.model.domain.HotelReviewOverallDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelFacilityDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelViewDtoRes;
import kh.mclass.shushoong.hotel.model.repository.HotelRepository;
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
	
//	public List<HotelPic> selectPicList(String hotelCode) {
//		return hotelRepository.selectHotelPicList(hotelCode);
//	}
//	
//	public List<HotelRoomDto> selectRoomList(String hotelCode) {
//		return hotelRepository.selectRoomList(hotelCode);
//	}
	
	//호텔 상세정보
	public HotelViewDtoRes selectOneHotel(String hotelCode) {
		return hotelRepository.selectOneHotel(hotelCode);
	}
	
	//해당 호텔 편의시설 정보 불러오기
	public List<HotelFacilityDtoRes> selectHotelFacility(String hotelCode) {
		return hotelRepository.selectHotelFacility(hotelCode);
	}

	//해당 호텔 리뷰 작성된거 불러오기
	public List<HotelReviewDto> selectReviewDetailList(String hotelCode) {
		return hotelRepository.selectReviewDetailList(hotelCode);
	}
	
	//위에꺼랑 합치기!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public List<HotelReviewDto> selectReviewPage(String hotelCode, String currentPage, String userId ) {
//		String currentPage
		
		//현재페이지: currentPage
		//리뷰 하단에 표시할 페이지 수: reviewPageNum
		//화면에 한번에 표시되는 리뷰부분 당 글 수 : reviewNum
		
		Map<String, Object> result = null;
		
		//총 게시글 개수
		//DB가서 그때그때 알아와야함 - 호텔 한개 당 리뷰글이 몇개냐에 따라 달라질 수 있음
		int totalReviewCount = hotelRepository.selectTotalPageCount(hotelCode, dto);
		
		int start = reviewNum * (currentPage - 1) + 1;
		int end = reviewNum * currentPage;
		
//		전체페이지수(총 게시글 개수/한 페이지 당 글 수) => (총 게시글 개수%한 페이지 당 글 수== 0)?(총 게시글 개수/한 페이지 당 글 수):(총 게시글 개수/한 페이지 당 글 수+1)
		int totalPageCount = (totalReviewCount % reviewNum == 0?) ? (totalReviewCount / reviewNum) : (totalReviewCount / reviewNum) + 1;
		// 조건문 - 앞에가 0이 맞으면 : 앞에꺼, 0이 아니면 : 뒤에꺼
		
		//시작페이지
		int startPageNum = (currentPage % reviewPageNum == 0) ? ((currentPage / reviewPageNum) - 1) * reviewPageNum + 1
				: (currentPage / reviewPageNum) * reviewPageNum + 1;
		
		//끝페이지
		int endPageNum = (startPageNum + reviewPageNum > totalPageCount) ? totalPageCount
				: startPageNum + reviewPageNum - 1;
		
		return hotelRepository.selectReviewPage(hotelCode, currentPage, userId );

	}
	
	//해당 호텔 총리뷰 기록 불러오기
	public List<HotelReviewOverallDtoRes> selectReviewOverall(String hotelCode) {	
		return hotelRepository.selectReviewOverall(hotelCode);
	}
	
	

}
