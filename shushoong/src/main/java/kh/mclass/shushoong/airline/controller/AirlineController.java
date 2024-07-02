package kh.mclass.shushoong.airline.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.ibatis.annotations.Arg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.mail.Session;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kh.mclass.shushoong.airline.model.domain.AirlineInfoDto;
import kh.mclass.shushoong.airline.model.domain.AirlinePassengerInfoDto;
import kh.mclass.shushoong.airline.model.service.AirlineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@Slf4j
public class AirlineController {

	@Autowired
	private AirlineService service;
	
	// 항공 목록 
	@GetMapping("/airline/list")
	public String airlineInfo(
//			String departLoc2,
//			String arrivalLoc2,
//			String departDate2,
//			String arrivalDate2,
//			String adult2,
//			String child2,
//			String baby2,
//			String seatGrade2,
//			String ticketType2,
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
		
//		session.setAttribute("departLoc", departLoc2);
//		session.setAttribute("arrivalLoc", arrivalLoc2);
//		session.setAttribute("departDate", departDate2);
//		session.setAttribute("arrivalDate", arrivalDate2);
//		session.setAttribute("adult", adult2);
//		session.setAttribute("child", child2);
//		session.setAttribute("seatGrade", seatGrade2);
//		session.setAttribute("ticketType", ticketType2);
		System.out.println(" ==== 컨트롤러 세션 값 ====");
		System.out.println("ticketType : " + ticketType + "adult : " + adult + "child : " + child + "baby : " + baby );
		
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
	@PostMapping("airline/list_return")
	public String airlineInfoReturn(
			String airlineCode,
			HttpSession session,
			Model md) {
		
		session.setAttribute("airlineCode", airlineCode);
		String departLoc = (String) session.getAttribute("arrivalLoc");
		String arrivalLoc = (String) session.getAttribute("departLoc");
		String departDate = (String) session.getAttribute("departDate");
		String arrivalDate = (String) session.getAttribute("arrivalDate");
		String airlineCode2 = (String) session.getAttribute("airlineCode");
		
		System.out.println("선택된 항공 코드 : " +  airlineCode2);
		System.out.println("에어 오는편 컨트롤러");
		log.info("항공 오는 편 departLoc: {}, arrivalLoc: {}", departLoc, arrivalLoc);
		
		List<AirlineInfoDto> airlineReturnData = service.getAirlineInfo(departLoc, arrivalLoc, departDate, arrivalDate);
		// 선택한 항공
		AirlineInfoDto selectOneAirline = service.getSelectOne(airlineCode2);
		log.info("선택된 항공 코드 : ", airlineCode2);
		System.out.println("선택된 항공 코드 : " +  airlineCode2);
		
		Integer maxPrice = service.getMaxPrice(departLoc, arrivalLoc);
		System.out.println("출발지 : " + departLoc);
		System.out.println("도착지 : " + arrivalLoc);
		md.addAttribute("airlineCode", airlineCode2);
		md.addAttribute("selectOneAirline", selectOneAirline);
		md.addAttribute("airlineReturnData", airlineReturnData);
		md.addAttribute("maxPrice", maxPrice);
		log.debug("Return controller 왕복편 data : {}", airlineReturnData);
		
		// 3만원~6만원 사이의 랜덤 수
        Random random = new Random();
        for (AirlineInfoDto air : airlineReturnData) {
            int randomPrice = 30000 + random.nextInt(30001); // 30000에서 60000 사이의 랜덤 값
            air.setSeatRandomPrice(randomPrice);
        }

		
		return "airline/airline_list_return";
	}
	
	// 항공 목록 정렬 옵션
	@GetMapping("airline/list_select_options/ajax")
	//@ResponseBody
	public String airlineSelectOptions(
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
	// 항공 돌아오는 목록 정렬 옵션
	@GetMapping("airline/list_select_options_return/ajax")
	//@ResponseBody
	public String airlineReturnSelectOptions(
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
		String departLoc = (String) session.getAttribute("arrivalLoc");
		String arrivalLoc = (String) session.getAttribute("departLoc");
		String airlineCode2 = (String) session.getAttribute("airlineCode");
		
		System.out.println("컨트롤러 목록 정렬");
		System.out.println("출발지 : " + departLoc);
		System.out.println("도착지 : " + arrivalLoc);
		System.out.println("정렬 타입 : " + selectType);
		System.out.println("경유 타입 : " + viaType);
		System.out.println("가격 최댓 값 : " + maxPrice);
		
		AirlineInfoDto selectOneAirline = service.getSelectOne(airlineCode2);
		List<AirlineInfoDto> SortData = service.getAirlineSideTime(
				departLoc, arrivalLoc, departTimeLeft, deaprtTimeRight, arrivalTimeLeft, arrivalTimeRight, selectType, viaType, maxPrice
				);
		md.addAttribute("selectOneAirline", selectOneAirline);
		md.addAttribute("airlineReturnData", SortData);
		Integer maxPrice2 = service.getMaxPrice(departLoc, arrivalLoc);
		md.addAttribute("maxPrice", maxPrice2);
		log.debug("컨트롤러 디버깅 : " + SortData);
		
		return "airline/airline_returnlist_section";
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
		session.setAttribute("baby", baby);
		session.setAttribute("seatGrade", seatGrade);
		session.setAttribute("ticketType", ticketType);
		return "redirect:/airline/list";
	}

	// 항공 메인 페이지
	@PostMapping("/airline/customer/reserve/pay")
	public String airlinePay(HttpSession session, Model md,
			String airlineCodeDirect,
			String airlineCodeReturn,
			Principal principal
							) {
		System.out.println("예약페이지 컨트롤러");
		log.info("principal: {}",principal);
		String airlineCode2 = (String) session.getAttribute("airlineCode");
		String adult = (String) session.getAttribute("adult");
		String child = (String) session.getAttribute("child");
		String baby = (String) session.getAttribute("baby");
		md.addAttribute("adult", adult);
		md.addAttribute("child", child);
		md.addAttribute("baby", baby);
		md.addAttribute("airlineCodeReturn", airlineCodeReturn);
		if(principal != null) {
			String userId = principal.getName();
			md.addAttribute("userId",userId);
		}
		
		log.info("어른 adult: {}, 소아 child: {}, 유아 baby {}, 왕복 항공코드 {}, 오는편 항공코드{}, 편도 항공코드{}", adult, child, baby,airlineCode2,airlineCodeReturn,airlineCode2);
		
		return "airline/airline_pay";
	}
	
	
	//항공에서 받는 값
	
	@ResponseBody
	@PostMapping("/airline/input/reserverInfo")
	public int customerInfo(
			@RequestParam("reserver_name") String reserver_name,
			@RequestParam("phone_number") String phone_number,
			@RequestParam("reserver_email") String reserver_email		
			) {
		
		
		
		int result = 0;
		System.out.println("!@#$%^%^&2311322312132213              :       "+reserver_name+phone_number+reserver_email);
		 result = service.insertReserverInfo(reserver_name,phone_number,reserver_email);
		return result;
	}
	
	@ResponseBody
	@PostMapping("/airline/select/resCode")
	public String selectResCode(
			@RequestParam("reserver_name") String reserver_name,
			@RequestParam("phone_number") String phone_number,
			@RequestParam("reserver_email") String reserver_email	
			) {
		String result = null;
		result = service.selectResCode(reserver_name,phone_number,reserver_email);
		return result;
	}
	
	
	@ResponseBody
	@PostMapping("/airline/input/passengerInfo")
	public int passengerInfo(
			@RequestBody List<Map<String, Object>> param
			) {
		int result = 0;
		
		System.out.println("21416547sdafdsaf6677daafdsfasdadsfsafd : "+ param);
		result = service.insertPassengerInfo(param);
		return result;
		
	}
	
	
	@GetMapping("/airline/customer/reserve/pay/success")
	public String paySuccess() {
		return "airline/airline_pay_success";
	}
	
	
}