package kh.mclass.shushoong.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelController {

	@GetMapping("/hotel/main")
	public String hotelMain() {
		return "hotel/hotel_main";
	}
	
	@GetMapping("/hotel/list")
	public String hotelList() {
		return "hotel/hotel_list";
	}
	
	@GetMapping("/hotel/view")
	public String hotelex() {
		return "hotel/hotel_view";
	}
}
