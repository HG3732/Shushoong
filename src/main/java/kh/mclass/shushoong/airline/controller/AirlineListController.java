package kh.mclass.shushoong.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kh.mclass.shushoong.airline.model.domain.AirlineInfoDto;
import kh.mclass.shushoong.airline.model.service.AirlineService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AirlineListController {

	@Autowired
	private AirlineService service;

	@GetMapping("/airline/list")
	public String getAirlineInfo(
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

//	@GetMapping("/airline/list")
//	public String getAirList() {
//		return "airline/airline_list";
//	}

	// 항공 메인 페이지
	@GetMapping("/airline/main")
	public String airlineHome() {
		return "airline/airline_main";
	}

	// 항공 메인 페이지
	@GetMapping("/airline/customer/reserve/pay")
	public String customerPay() {
		return "airline/airline_pay";
	}

}
