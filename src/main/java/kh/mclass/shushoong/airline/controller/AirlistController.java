package kh.mclass.shushoong.airline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AirlistController {
	@GetMapping("/airline/list")
	public String getAirList() {
		return "airline/airlist";
	}
	
	
	
	//항공 메인 페이지
	@GetMapping("/airline/main")
	public String airlineHome() {
		return "airline/airline_main";
	}
	

}
