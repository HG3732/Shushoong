package kh.mclass.shushoong.home.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	
	@GetMapping({"/", "/home"})
	public String home(Model model, HttpSession session) {
		LocalDate now = LocalDate.now();
		LocalDate checkInDate = now.plusDays(1);
		LocalDate checkOutDate = now.plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
        String checkIn = checkInDate.format(formatter);
        String checkOut = checkOutDate.format(formatter);

        model.addAttribute("checkIn", checkIn);
        model.addAttribute("checkOut", checkOut);
		
		model.addAttribute("discounthotelList", service.selectDiscountHotelList());
		model.addAttribute("latestReview", service.selectLatestReview());
		return "home";
	}
	
}
