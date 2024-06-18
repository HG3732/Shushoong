package kh.mclass.shushoong.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelFacilityDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelReviewDto;
import kh.mclass.shushoong.hotel.model.domain.HotelReviewOverallDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelRoomDto;
import kh.mclass.shushoong.hotel.model.domain.HotelViewDtoRes;
import kh.mclass.shushoong.hotel.model.service.HotelService;

@Controller
public class HotelController {

	@Autowired
	private HotelService service;
	
	@GetMapping("/hotel/main")
	public String hotelMain(Model model, HttpSession session) {
		List<HotelDtoRes> hotHotelList = service.selectHotHotelList();
		model.addAttribute("hotHotelList", hotHotelList);
		session.setAttribute("userId", "ex1");
		return "hotel/hotel_main";
	}
	
	//main에서 지역, 인원 검색 시 호텔 리스트 표시
	@GetMapping("/hotel/list")
	public String hotelList(Model model, HttpSession session, String loc, String room, String adult, String child) {
		Integer child1 = Integer.parseInt(child)/2;
		Integer adult1 = Integer.parseInt(adult);
		String people = String.valueOf(child1+adult1);
		List<HotelDtoRes> result = service.selectAllHotelList(loc, people, null, null, null, null);
		Integer maxPrice = service.selectMaxRoomlPrice(loc, people, null);
		
		//좋아요 여부 검색
		String userId = (String)session.getAttribute("userId");
		List<String> likeList = service.selectLikeHotelList(loc, userId);
//		hotelDtoRes.setHotelPic(service.selectAllHotelList(loc));
//		hotelDtoRes.setHotelPic(service.selectAllHotelList(loc));
//		hotelDtoRes.setHotelPic(service.selectAllHotelList(loc));
		model.addAttribute("hotelList", result);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("likeList", likeList);
		return "hotel/hotel_list";
	}

	@GetMapping("/hotel/list/sort.ajax")
	public String hotelListSort(
			Model model,
			HttpSession session,
			String loccode,
			String people,
			String keyword,
			String maxPrice,
			String sortBy,
			String sortTo
			) {
		List<HotelDtoRes> result = service.selectAllHotelList(loccode, people, keyword, maxPrice, sortBy, sortTo);
		Integer maxPrice2 = service.selectMaxRoomlPrice(loccode, people, keyword);
		//session의 이름이 43행에 있는 List와 같아야 덮어쓰기됨, 다를 경우 기존 List + 새 List 출력
		String userId = (String)session.getAttribute("userId");
		List<String> likeList = service.selectLikeHotelList(loccode, userId);
		model.addAttribute("hotelList", result);
		model.addAttribute("maxPrice", maxPrice2);
		model.addAttribute("likeList", likeList);
		return "hotel/hotel_list_section";
	}
	
	@GetMapping("/hotel/list/price.ajax")
	public String hotelPriceSort(
			Model model,
			HttpSession session,
			String loccode,
			String people,
			String keyword,
			String maxPrice,
			String sortBy,
			String sortTo
			) {
		List<HotelDtoRes> result = service.selectAllHotelList(loccode, people, keyword, maxPrice, sortBy, sortTo);
		Integer maxPrice2 = service.selectMaxRoomlPrice(loccode, people, keyword);
		//session의 이름이 43행에 있는 List와 같아야 덮어쓰기됨, 다를 경우 기존 List + 새 List 출력
		String userId = (String)session.getAttribute("userId");
		List<String> likeList = service.selectLikeHotelList(loccode, userId);
		model.addAttribute("hotelList", result);
		model.addAttribute("maxPrice", maxPrice2);
		model.addAttribute("likeList", likeList);
		return "hotel/hotel_slide_bar";
	}
	
	//좋아요 테이블에 추가
	@GetMapping("/hotel/like/insert.ajax")
	public String insertHotelLike(Model model, HttpSession session, String hotelCode) {
		String userId = (String)session.getAttribute("userId");
		Integer result = service.insertHotelLike(userId, hotelCode);
		model.addAttribute("result", result);
		return "hotel/hotel_list_section";
	}
	
	//좋아요 테이블에서 삭제
		@GetMapping("/hotel/like/delete.ajax")
		public String deleteHotelLike(Model model, HttpSession session, String hotelCode) {
			String userId = (String)session.getAttribute("userId");
			Integer result = service.deleteHotelLike(userId, hotelCode);
			model.addAttribute("result", result);
			return "hotel/hotel_list_section";
		}
	
	@GetMapping("/hotel/view")
	public String hotelview(Model model) {
		// @PathVariable String hotelCode
		
		//호텔 상세정보들 출력
		HotelViewDtoRes result = service.selectOneHotel("2OS001");
		model.addAttribute("hotelViewList", result); 

		
		//편의시설
		List<HotelFacilityDtoRes> facilitylist = service.selectHotelFacility("2OS001");
		for(int i = 0; i<facilitylist.size(); i++) {
			switch(facilitylist.get(i).getHotelFacCat()){
				case "0":
					facilitylist.get(i).setHotelFacCat("무선인터넷");
					break;
				case "1":
					facilitylist.get(i).setHotelFacCat("주차");
					break;
				case "2":
					facilitylist.get(i).setHotelFacCat("레스토랑");
					break;
				case "3":
					facilitylist.get(i).setHotelFacCat("수영장");
					break;
				case "4":
					facilitylist.get(i).setHotelFacCat("피트니스센터");
					break;
				case "5":
					facilitylist.get(i).setHotelFacCat("에어컨");
					break;
				case "6":
					facilitylist.get(i).setHotelFacCat("바");
					break;
				default:
					facilitylist.get(i).setHotelFacCat("카지노");
					break;		
			}
			
		}
		model.addAttribute("facilitylist", facilitylist);
		
		//작성된 리뷰 불러오기
		List<HotelReviewDto> reviewDetailDto = service.selectReviewDetailList("2OS001");
			//여행객 종류
			for(int i = 0; i<reviewDetailDto.size(); i++) {
				switch(reviewDetailDto.get(i).getTripperCat()){
					case "0":
						reviewDetailDto.get(i).setTripperCat("혼자");
						break;
					case "1":
						reviewDetailDto.get(i).setTripperCat("커플/부부");
						break;
					case "2":
						reviewDetailDto.get(i).setTripperCat("가족");
						break;
					case "3":
						reviewDetailDto.get(i).setTripperCat("단체");
						break;
					default:
						reviewDetailDto.get(i).setTripperCat("출장");
						break;		
				}
			}
		model.addAttribute("reviewDetailDto", reviewDetailDto);		
		
		List<HotelReviewOverallDtoRes> reviewOverallDto = service.selectReviewOverall("2OS001");
		model.addAttribute("reviewOverallDto", reviewOverallDto);	
		
		
		return "hotel/hotel_view";
	}
	
	//페이징 처리
//	@GetMapping("/hotel/view/paging.ajax")
//	public String hotelPaging() {
//		
//		return "hotel/hotel_view_paging";
//		
//	}
//	
	@GetMapping("/hotel/customer/reserve/pay")
	public String hotelPay() {
		return "hotel/hotel_pay";
	}

	//지역, 인원수 선택 안한채로 hotel_list를 url에 직접 입력하여 진입할 경우 예외처리  
//	@ExceptionHandler(Exception.class)
//	public String ExceptionHandler() {
//		return "hotel/hotel_main";
//	}
}
