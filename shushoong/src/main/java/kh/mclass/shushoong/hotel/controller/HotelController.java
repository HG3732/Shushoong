package kh.mclass.shushoong.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelFacilityDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelReviewDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelReviewOverallDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelRoomDto;
import kh.mclass.shushoong.hotel.model.service.HotelService;

@Controller
public class HotelController {

	@Autowired
	private HotelService service;
	
	@GetMapping("/hotel/main")
	public String hotelMain() {
		return "hotel/hotel_main";
	}
	
	//main에서 지역, 인원 검색 시 호텔 리스트 표시
	@GetMapping("/hotel/list")
	public String hotelList(Model model, String loc, String room, String adult, String child) {
		Integer child1 = Integer.parseInt(child)/2;
		Integer adult1 = Integer.parseInt(adult);
		String people = String.valueOf(child1+adult1);
		List<HotelDtoRes> result = service.selectAllHotelList(loc, people, null, null, null, null);
		Integer maxPrice = service.selectMaxRoomlPrice(loc, people, null);
//		hotelDtoRes.setHotelPic(service.selectAllHotelList(loc));
//		hotelDtoRes.setHotelPic(service.selectAllHotelList(loc));
//		hotelDtoRes.setHotelPic(service.selectAllHotelList(loc));
		model.addAttribute("hotelList", result);
		model.addAttribute("maxPrice", maxPrice);
		return "hotel/hotel_list";
	}

	@GetMapping("/hotel/list/sort.ajax")
	public String hotelListSort(
			Model model,
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
		model.addAttribute("hotelList", result);
		model.addAttribute("maxPrice", maxPrice2);
		return "hotel/hotel_list_section";
	}
	
	@GetMapping("/hotel/list/price.ajax")
	public String hotelPriceSort(
			Model model,
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
		model.addAttribute("hotelList", result);
		model.addAttribute("maxPrice", maxPrice2);
		return "hotel/hotel_slide_bar";
	}
	
	@GetMapping("/hotel/view")
	public String hotelview(Model model) {
		
		List<HotelRoomDto> result = service.selectRoomList("2OS001");
		
		//방 종류 경우의 수 나누기
		for(int i = 0; i < result.size(); i++) {
			switch (result.get(i).getRoomCat()) {
			case "0":
				result.get(i).setRoomCat("스탠다드 룸");
				break;
			case "1":
				result.get(i).setRoomCat("디럭스 룸");
				break;
			case "2":
				result.get(i).setRoomCat("슈페리어 룸");
				break;
			case "3":
				result.get(i).setRoomCat("스위트 룸");
				break;
			default:
				result.get(i).setRoomCat("기타");
				break;
			}
		}

		
		//방 속성 경우의 수 나누기
		for(int i = 0; i < result.size(); i++) {
			switch(result.get(i).getRoomAtt()) {
			case "0":
				result.get(i).setRoomAtt("뷰 없음");
				break;
			case "1":
				result.get(i).setRoomAtt("오션뷰");
				break;
			case "2":
				result.get(i).setRoomAtt("마운틴뷰");
				break;
			default:
				result.get(i).setRoomAtt("시티뷰");
				break;			
			}
		}
		model.addAttribute("roomlist", result);

		//호텔 사진들 다 출력
		model.addAttribute("piclist", service.selectPicList("2OS001"));
		//model.addAttribute("dtolist", service.selectAllHotelList("2OS001")); 
		
		//호텔 상세정보들 출력
		model.addAttribute("hotelSearchlist", service.selectHotelSearchList("2OS001")); 
		
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
					result.get(i).setRoomAtt("카지노");
					break;		
			}
			
		}
		model.addAttribute("facilitylist", facilitylist);
		
		//작성된 리뷰 불러오기
		List<HotelReviewDtoRes> reviewDetailDto = service.selectReviewDetailList("2OS001");
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
