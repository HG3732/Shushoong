package kh.mclass.shushoong.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kh.mclass.shushoong.airline.model.domain.AirlineInfoDto;
import kh.mclass.shushoong.airline.model.service.AirlineService;



@Controller
public class AirlineListController {
	
	@Autowired
	private AirlineService service;
	
//	@GetMapping("/airline/list")
//	public String getAirlineInfo(
//			@RequestParam String departLoc, @RequestParam String arrivalLoc, Model md
//			){
//		 if (departLoc != null && arrivalLoc != null) {
//		List<AirlineInfoDto> airlineData = service.getAirlineInfo(departLoc, arrivalLoc);
//		md.addAttribute("airline", airlineData);
//		 }
//		 System.out.println("없음");
//		 return "airline/airline_list";
//	}
	
	@GetMapping("/airline/list")
	public String getAirList() {
		return "airline/airline_list";
	}
	
	
	
	//항공 메인 페이지
	@GetMapping("/airline/main")
	public String airlineHome() {
		return "airline/airline_main";
	}
	
	//항공 메인 페이지
	@GetMapping("/airline/customer/reserve/pay")
	public String customerPay() {
		return "airline/airline_pay";
	}
	

}
