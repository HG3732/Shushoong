package kh.mclass.shushoong.airline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AirlineListController {
	@GetMapping("/airline/list")
	public String getAirList() {
		return "airline/airline_list";
	}
	
	
	
	//항공 메인 페이지
	@GetMapping("/airline/default")
	public String airlineHome() {
		return "airline/airline_main";
	}
	

}
