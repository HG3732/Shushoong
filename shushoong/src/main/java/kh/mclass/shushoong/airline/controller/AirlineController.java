package kh.mclass.shushoong.airline.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.mclass.shushoong.airline.model.domain.AirlineInfoDto;
import kh.mclass.shushoong.airline.model.service.AirlineService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AirlineController {

	@Autowired
	private AirlineService service;
	
	// 항공 목록 
	@GetMapping("/airline/list")
	public String airlineInfo(
			String departLoc,
			String arrivalLoc,
			String departDate,
			String arrivalDate,
			Model md) {
		System.out.println("=========");
		log.info("!!!Received departLoc: " + departLoc + ", arrivalLoc: " + arrivalLoc + ", departDate: " + departDate +", arrivalDate: " + arrivalDate);

		if (departLoc != null && arrivalLoc != null) {
			List<AirlineInfoDto> airlineData = service.getAirlineInfo(departLoc, arrivalLoc, departDate, arrivalDate);
			System.out.println("컨트롤러 airline data: " + airlineData);
			Integer maxPrice = service.getMaxPrice(departLoc, arrivalLoc);
			md.addAttribute("airlineData", airlineData);
			md.addAttribute("maxPrice", maxPrice);
		}else {
		}
		return "airline/airline_list";
	}
	
	// 왕복 오는 항공편
	@GetMapping("airline/list_return")
	public String airlineInfoReturn(
			String airlineCode,
			String departLoc,
			String arrivalLoc,
			String departDate,
			String arrivalDate,
			Model md) {
		
		System.out.println("에어 오는편 컨트롤러");
		log.info("선택된 항공 코드 : ", airlineCode);
		log.info("항공 오는 편 departLoc: {}, arrivalLoc: {}", departLoc, arrivalLoc);
		
		List<AirlineInfoDto> airlineReturnData = service.getAirlineInfo(departLoc, arrivalLoc, departDate, arrivalDate);
		
		// 선택한 항공
		List<AirlineInfoDto> selectOneAirline = service.getSelectOne(airlineCode);
		
		Integer maxPrice = service.getMaxPrice(departLoc, arrivalLoc);
		System.out.println("출발지 : " + departLoc);
		System.out.println("도착지 : " + arrivalLoc);
		
		md.addAttribute("selectOneAirline", selectOneAirline);
		md.addAttribute("airlineReturnData", airlineReturnData);
		md.addAttribute("maxPrice", maxPrice);
		log.debug("Return controller 왕복편 data : {}", airlineReturnData);
		
		return "airline/airline_list_return";
	}
	
	// 항공 목록 정렬 옵션
	@GetMapping("airline/list_select_options/ajax")
	@ResponseBody
	public List<AirlineInfoDto> airlineSelectOptions(
			String departLoc,
			String arrivalLoc,
			String departTimeLeft,
			String deaprtTimeRight,
			String arrivalTimeLeft,
			String arrivalTimeRight,
			String selectType,
			String viaType,
			String maxPrice,
			Model md
			){
		System.out.println("컨트롤러 목록 정렬");
		System.out.println("출발지 : " + departLoc);
		System.out.println("도착지 : " + arrivalLoc);
		System.out.println("정렬 타입 : " + selectType);
		System.out.println("경유 타입 : " + viaType);
		System.out.println("가격 최댓 값 : " + maxPrice);
		
		List<AirlineInfoDto> SortData = service.getAirlineSideTime(
				departLoc, arrivalLoc, departTimeLeft, deaprtTimeRight, arrivalTimeLeft, arrivalTimeRight, selectType, viaType, maxPrice
				);
		Integer maxPrice2 = service.getMaxPrice(departLoc, arrivalLoc);
		md.addAttribute("maxPrice2", maxPrice2);
		log.debug("컨트롤러 디버깅 : " + SortData);
		
		return SortData;
	}


	// 항공 메인 페이지
	@GetMapping("/airline/main")
	public String airlineMain() {
		return "airline/airline_main";
	}

	// 항공 메인 페이지
	@GetMapping("/airline/customer/reserve/pay")
	public String airlinePay() {
		return "airline/airline_pay";
	}

}
