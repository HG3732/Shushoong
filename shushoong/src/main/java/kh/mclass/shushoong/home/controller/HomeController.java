package kh.mclass.shushoong.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import kh.mclass.shushoong.home.model.service.HomeService;

@Controller
public class HomeController {

	@Autowired
	private HomeService service;
	
	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		model.addAttribute("discounthotelList", service.selectDiscountHotelList());
		model.addAttribute("latestReview", service.selectLatestReview());
		return "home";
	}
	
}
