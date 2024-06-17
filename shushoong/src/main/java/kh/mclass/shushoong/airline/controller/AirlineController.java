package kh.mclass.shushoong.airline.controller;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kh.mclass.shushoong.airline.model.domain.AirlineInfoDto;
import kh.mclass.shushoong.airline.model.service.AirlineService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AirlineController {

	@Autowired
	private AirlineService service;
	private AirlineInfoDto dto;
	// 항공 목록 
	@GetMapping("/airline/list")
	public String airlineInfo(
//			String departLoc,
//			String arrivalLoc,
//			String departDate,
//			String arrivalDate,
//			String adult,
//			String child,
//			String baby,
//			String seatGrade,
//			String ticketType,
//			String seatPrice,
			HttpSession session,
			Model md) {
		String departLoc = (String) session.getAttribute("departLoc");
		String arrivalLoc = (String) session.getAttribute("arrivalLoc");
		String departDate = (String) session.getAttribute("departDate");
		String arrivalDate = (String) session.getAttribute("arrivalDate");
		String adult = (String) session.getAttribute("adult");
		String child = (String) session.getAttribute("child");
		String baby = (String) session.getAttribute("baby");
		String ticketType = (String) session.getAttribute("ticketType");
		
		System.out.println("=========");
		log.info("!!!Received departLoc: " + departLoc + ", arrivalLoc: " + arrivalLoc + ", departDate: " + departDate +", arrivalDate: " + arrivalDate);

		if (departLoc != null && arrivalLoc != null) {
			List<AirlineInfoDto> airlineData = service.getAirlineInfo(departLoc, arrivalLoc, departDate, arrivalDate);
			
			System.out.println("컨트롤러 airline data: " + airlineData);
			Integer maxPrice = service.getMaxPrice(departLoc, arrivalLoc);
			
			
			if(ticketType.equals("1")) {
				maxPrice = maxPrice/2;
				maxPrice = (int) Math.floor(maxPrice);
			}
			
			md.addAttribute("adult", adult);
			md.addAttribute("child", child);
			md.addAttribute("baby", baby);
			md.addAttribute("ticketType", ticketType);
			md.addAttribute("airlineData", airlineData);
//			md.addAttribute("maxPrice", maxPriceStr);
			md.addAttribute("maxPrice", maxPrice);
		}else {
		}
		return "airline/airline_list";
	}
	
	// 왕복 오는 항공편
	@GetMapping("airline/list_return")
	public String airlineInfoReturn(
			String airlineCode,
//			String departLoc,
//			String arrivalLoc,
//			String departDate,
//			String arrivalDate,
			HttpSession session,
			Model md) {
		
		String departLoc = (String) session.getAttribute("arrivalLoc");
		String arrivalLoc = (String) session.getAttribute("departLoc");
		String departDate = (String) session.getAttribute("departDate");
		String arrivalDate = (String) session.getAttribute("arrivalDate");
		
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
	//@ResponseBody
	public String airlineSelectOptions(
//			String departLoc,
//			String arrivalLoc,
			String departTimeLeft,
			String deaprtTimeRight,
			String arrivalTimeLeft,
			String arrivalTimeRight,
			String selectType,
			String viaType,
			String maxPrice,
			HttpSession session,
			Model md
			){
		String departLoc = (String) session.getAttribute("departLoc");
		String arrivalLoc = (String) session.getAttribute("arrivalLoc");
		
		System.out.println("컨트롤러 목록 정렬");
		System.out.println("출발지 : " + departLoc);
		System.out.println("도착지 : " + arrivalLoc);
		System.out.println("정렬 타입 : " + selectType);
		System.out.println("경유 타입 : " + viaType);
		System.out.println("가격 최댓 값 : " + maxPrice);
		
		List<AirlineInfoDto> SortData = service.getAirlineSideTime(
				departLoc, arrivalLoc, departTimeLeft, deaprtTimeRight, arrivalTimeLeft, arrivalTimeRight, selectType, viaType, maxPrice
				);
		md.addAttribute("airlineData", SortData);
		Integer maxPrice2 = service.getMaxPrice(departLoc, arrivalLoc);
		md.addAttribute("maxPrice", maxPrice2);
		log.debug("컨트롤러 디버깅 : " + SortData);
		
		return "airline/airline_list_section";
	}


	// 항공 메인 페이지
    @GetMapping("/airline/main")
    public String airlineMain() {
        return "airline/airline_main"; // 폼이 있는 페이지로 이동
    }
	
	@PostMapping("/airline/main")
	public String airlineMainPost(
			String departLoc,
			String arrivalLoc,
			String departDate,
			String arrivalDate,
			String adult,
			String child,
			String baby,
			String seatGrade,
			String ticketType,
			HttpSession session) {
		session.setAttribute("departLoc", departLoc);
		session.setAttribute("arrivalLoc", arrivalLoc);
		session.setAttribute("departDate", departDate);
		session.setAttribute("arrivalDate", arrivalDate);
		session.setAttribute("adult", adult);
		session.setAttribute("child", child);
		session.setAttribute("seatGrade", seatGrade);
		session.setAttribute("ticketType", ticketType);
		return "redirect:/airline/list";
	}

	// 항공 메인 페이지
	@GetMapping("/airline/customer/reserve/pay")
	public String airlinePay() {
		return "airline/airline_pay";
	}

}
