package kh.mclass.shushoong.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelFacilityDtoRes;
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
		List<HotelDtoRes> result = service.selectAllHotelList(loc, people, null, null, "price", "asc");
//		hotelDtoRes.setHotelPic(service.selectAllHotelList(loc));
//		hotelDtoRes.setHotelPic(service.selectAllHotelList(loc));
//		hotelDtoRes.setHotelPic(service.selectAllHotelList(loc));
		model.addAttribute("hotelList", result);
		return "hotel/hotel_list";
	}
	
	//list에서 세부 조건, 정렬 조건 선택 시 조건에 맞게 리스트 정렬
	@GetMapping("/hotel/list/sort.ajax")
	@ResponseBody
	public List<HotelDtoRes> hotelListSort(
			String loccode,
			String people,
			String keyword,
			String maxPrice,
			String sortBy,
			String sortTo
			) {
		
		List<HotelDtoRes> result = service.selectAllHotelList(loccode, people, keyword, maxPrice, sortBy, sortTo);
		return null;
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
		
		model.addAttribute("piclist", service.selectPicList("2OS001"));
		//model.addAttribute("dtolist", service.selectAllHotelList("2OS001")); 
		model.addAttribute("hotelSearchlist", service.selectHotelSearchList("2OS001")); 
		
		//편의시설
		List<HotelFacilityDtoRes> fcresult = service.selectHotelFacility("2OS001");
		model.addAttribute("facilitylist", fcresult);
		
		return "hotel/hotel_view";
	}
	
	@GetMapping("/hotel/customer/reserve/pay")
	public String hotelPay() {
		return "hotel/hotel_pay";
	}
	
}
