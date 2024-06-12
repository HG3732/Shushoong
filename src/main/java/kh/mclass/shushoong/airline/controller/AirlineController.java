package kh.mclass.shushoong.airline.controller;

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
			Model md) {
		System.out.println("=========");
		log.info("!!!Received departLoc: " + departLoc + ", arrivalLoc: " + arrivalLoc);

		if (departLoc != null && arrivalLoc != null) {
			List<AirlineInfoDto> airlineData = service.getAirlineInfo(departLoc, arrivalLoc);
			System.out.println("컨트롤러 airline data: " + airlineData);
			md.addAttribute("airlineData", airlineData);
		}else {
			System.out.println("출발지 도착지 일치하는 데이터 없음");
		}
		return "airline/airline_list";
	}
	
	// 왕복 오는 항공편
	@GetMapping("airline/list_return")
	public String airlineInfoReturn(
			String departLoc,
			String arrivalLoc,
			String airlineCode,
			Model md) {
		
		System.out.println("에어 오는편 컨트롤러");
		log.info("선택된 항공 코드 : ", airlineCode);
		log.info("항공 오는 편 departLoc: {}, arrivalLoc: {}", departLoc, arrivalLoc);
		
		List<AirlineInfoDto> airlineReturnData = service.getAirlineInfo(departLoc, arrivalLoc);
		
		// 선택한 항공
		List<AirlineInfoDto> selectOneAirline = service.getSelectOne(airlineCode);
		
		md.addAttribute("selectOneAirline", selectOneAirline);
		md.addAttribute("airlineReturnData", airlineReturnData);
		log.debug("Return controller 왕복편 data : {}", airlineReturnData);
		
		return "airline/airline_list_return";
	}
	
//	@GetMapping("airline/list_return/ajax")
//    @ResponseBody
//    public List<AirlineInfoDto> airlineInfoReturn(
//            @RequestParam String departLoc,
//            @RequestParam String arrivalLoc) {
//        log.info("Received departLoc: {}, arrivalLoc: {}", departLoc, arrivalLoc);
//        List<AirlineInfoDto> airlineReturnData = service.getAirlineInfo(departLoc, arrivalLoc);
//        System.out.println("컨트롤렁ㅔ이이작스");
//        log.debug("Return controller 왕복편 data : {}", airlineReturnData);
//        return airlineReturnData;
//    }
//	
	
	// 항공 목록 사이드 바
	@GetMapping("airline/list_side_time/ajax")
	@ResponseBody
	public List<AirlineInfoDto> airlineSideDepartTime(
			String departLoc,
			String arrivalLoc,
			String departTimeLeft,
			String deaprtTimeRight,
			String arrivalTimeLeft,
			String arrivalTimeRight
			){
		System.out.println("컨트롤러 사이드 시간대");
		
		List<AirlineInfoDto> SideDepartTimeData = service.getAirlineSideTime(departLoc, arrivalLoc, departTimeLeft, deaprtTimeRight, arrivalTimeLeft, arrivalTimeRight);
		log.debug("컨트롤러 디버깅 : " + SideDepartTimeData);
		return SideDepartTimeData;
		
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
