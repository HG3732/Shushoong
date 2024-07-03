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
import org.springframework.web.bind.annotation.ModelAttribute;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
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
	        @RequestParam String departLoc,
	        @RequestParam String arrivalLoc,
	        @RequestParam String departDate,
	        @RequestParam String arrivalDate,
	        @RequestParam String adult,
	        @RequestParam String child,
	        @RequestParam String baby,
	        @RequestParam String ticketType,
	        Model md) {
		
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
			
			System.out.println(adult);
			System.out.println(child);
			System.out.println(baby);
			
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
	
	// GET 요청을 처리하는 메소드
	@GetMapping("airline/list_return")
	public String listReturn(HttpSession session, Model md) {
	    // 세션에서 항공 코드 가져오기
	    String airlineCode2 = (String) session.getAttribute("airlineCode");
	    
	    // 세션에 항공 코드가 있는지 확인
	    if (airlineCode2 != null) {
	        // 세션에서 출발지, 도착지, 출발 날짜, 도착 날짜 가져오기
	        String departLoc = (String) session.getAttribute("arrivalLoc");
	        String arrivalLoc = (String) session.getAttribute("departLoc");
	        String departDate = (String) session.getAttribute("departDate");
	        String arrivalDate = (String) session.getAttribute("arrivalDate");

	        // 세션에서 항공편 리스트, 선택된 항공편, 최대 가격 가져오기
	        List<AirlineInfoDto> airlineReturnData = (List<AirlineInfoDto>) session.getAttribute("airlineReturnData");
	        AirlineInfoDto selectOneAirline = (AirlineInfoDto) session.getAttribute("selectOneAirline");
	        Integer maxPrice = (Integer) session.getAttribute("maxPrice");

	        // 모델에 데이터를 추가하여 뷰로 전달
	        md.addAttribute("airlineCode", airlineCode2);
	        md.addAttribute("selectOneAirline", selectOneAirline);
	        md.addAttribute("airlineReturnData", airlineReturnData);
	        md.addAttribute("maxPrice", maxPrice);
	    } else {
	        // 세션에 항공 코드가 없을 경우 에러 페이지로 리다이렉트
	        return "redirect:/errorPage"; // 에러 페이지로 리다이렉트
	    }
	    // 뷰 페이지로 이동
	    return "airline/airline_list_return";
	}

	// POST 요청을 처리하는 메소드
	@PostMapping("airline/list_return")
	public String airlineInfoReturn(
			String departLoc,
			String arrivalLoc,
			String departDate,
			String arrivalDate,
	        String airlineCode,
	        HttpSession session) {

	    // 서비스 메소드를 호출하여 항공편 데이터를 가져오기
	    List<AirlineInfoDto> airlineReturnData = service.getAirlineInfo(departLoc, arrivalLoc, departDate, arrivalDate);
	    AirlineInfoDto selectOneAirline = service.getSelectOne(airlineCode);
	    Integer maxPrice = service.getMaxPrice(departLoc, arrivalLoc);

	    // 각 항공편에 대해 30000에서 60000 사이의 랜덤 가격을 설정
	    Random random = new Random();
	    for (AirlineInfoDto air : airlineReturnData) {
	        int randomPrice = 30000 + random.nextInt(30001); // 30000에서 60000 사이의 랜덤 값
	        air.setSeatRandomPrice(randomPrice);
	    }

	    // 세션에 가져온 데이터를 저장
	    session.setAttribute("airlineReturnData", airlineReturnData);
	    session.setAttribute("selectOneAirline", selectOneAirline);
	    session.setAttribute("maxPrice", maxPrice);

	    // GET 메소드로 리다이렉트하여 데이터 처리
	    return "redirect:/airline/list_return";
	}

	
	// 항공 목록 정렬 옵션
	@GetMapping("airline/list_select_options/ajax")
	//@ResponseBody
	public String airlineSelectOptions(
			String departLoc,
			String arrivalLoc,
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
//		String departLoc = (String) session.getAttribute("departLoc");
//		String arrivalLoc = (String) session.getAttribute("arrivalLoc");
		
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
			String airlineCode,
			String departLoc,
			String arrivalLoc,
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
		
		System.out.println("컨트롤러 목록 정렬");
		System.out.println("뱅기 편명 : " + airlineCode);
		System.out.println("출발지 : " + departLoc);
		System.out.println("도착지 : " + arrivalLoc);
		System.out.println("정렬 타입 : " + selectType);
		System.out.println("경유 타입 : " + viaType);
		System.out.println("가격 최댓 값 : " + maxPrice);
		
		AirlineInfoDto selectOneAirline = service.getSelectOne(airlineCode);
		List<AirlineInfoDto> SortData = service.getAirlineSideTime(
				departLoc, arrivalLoc, departTimeLeft, deaprtTimeRight, arrivalTimeLeft, arrivalTimeRight, selectType, viaType, maxPrice
				);
		System.out.println("항공사 이름: " + selectOneAirline.getAirlineName());
		md.addAttribute("selectOneAirline", selectOneAirline);
		md.addAttribute("airlineReturnData", SortData);
		Integer maxPrice2 = service.getMaxPrice(departLoc, arrivalLoc);
		md.addAttribute("maxPrice", maxPrice2);
		log.debug("컨트롤러 디버깅 : " + SortData);
		
		Random random = new Random();
	    for (AirlineInfoDto air : SortData) {
	        int randomPrice = 30000 + random.nextInt(30001); // 30000에서 60000 사이의 랜덤 값
	        air.setSeatRandomPrice(randomPrice);
	    }
		
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
			//Model md
			RedirectAttributes rd
			) {
	    rd.addAttribute("departLoc", departLoc);
	    rd.addAttribute("arrivalLoc", arrivalLoc);
	    rd.addAttribute("departDate", departDate);
	    rd.addAttribute("arrivalDate", arrivalDate);
	    rd.addAttribute("adult", adult);
	    rd.addAttribute("child", child);
	    rd.addAttribute("baby", baby);
	    rd.addAttribute("seatGrade", seatGrade);
	    rd.addAttribute("ticketType", ticketType);
		return "redirect:/airline/list";
	}

	// 항공 메인 페이지
	@PostMapping("/airline/customer/reserve/pay")

		public String airlinePay(Model md,
		        @RequestParam String adult,
		        @RequestParam String child,
		        @RequestParam String baby,
		        @RequestParam String airlineCode,
		        Principal principal,
		        String airlineCodeReturn) {
			
			char domestic = service.selectOneDomesticFunction(airlineCode);
			
			md.addAttribute("adult", adult);
			md.addAttribute("child", child);
			md.addAttribute("baby", baby);
			md.addAttribute("airlineCodeReturn", airlineCodeReturn);
			md.addAttribute("domestic",domestic);
			
			if(principal != null) {
				String userId = principal.getName();
				md.addAttribute("userId",userId);
			}
		

		    log.info("어른 adult: {}, 소아 child: {}, 유아 baby {}, 가는편 항공코드{}, 오는편 항공코드{}", adult, child, baby, airlineCode, airlineCodeReturn);
		    System.out.println("어른 수: " + adult);
		    System.out.println("소아 수: " + child);
		    System.out.println("유아 수: " + baby);
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