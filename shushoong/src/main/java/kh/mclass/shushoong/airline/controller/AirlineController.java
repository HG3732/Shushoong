package kh.mclass.shushoong.airline.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpSession;
import kh.mclass.shushoong.airline.model.domain.AirlineInfoDto;
import kh.mclass.shushoong.airline.model.domain.AirlineReserverInfoDto;
import kh.mclass.shushoong.airline.model.domain.DirectViaDto;
import kh.mclass.shushoong.airline.model.service.AirlineService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AirlineController {

	@Autowired
	private AirlineService service;

	// 항공 목록
	@GetMapping("/airline/list")
	public String airlineInfo(@RequestParam String seatGrade, @RequestParam String departLoc,
			@RequestParam String arrivalLoc, @RequestParam String departDate, @RequestParam String arrivalDate,
			@RequestParam String adult, @RequestParam String child, @RequestParam String baby,
			@RequestParam String ticketType, Model md) {

		System.out.println(" ==== 항목 리스트 데이터 값 ====");
		System.out.println("departLoc : " + departLoc + ",arrivalLoc : " + arrivalLoc + "ticketType : " + ticketType
				+ ",adult : " + adult + ",child : " + child + ",baby : " + baby);

		System.out.println("=========");
		log.info("!!!Received departLoc: " + departLoc + ", arrivalLoc: " + arrivalLoc + ", departDate: " + departDate
				+ ", arrivalDate: " + arrivalDate);

		if (departLoc != null && arrivalLoc != null) {
			List<AirlineInfoDto> airlineData = service.getAirlineInfo(departLoc, arrivalLoc, departDate, arrivalDate,
					ticketType, seatGrade);
			for (AirlineInfoDto airlineInfo : airlineData) {
				switch (seatGrade) {
				case "1": {
					airlineInfo.setSeatGrade("First class");
					break;
				}
				case "2": {
					airlineInfo.setSeatGrade("Business class");
					break;
				}
				case "3": {
					airlineInfo.setSeatGrade("Economy class");
					break;
				}
				}
			}
			System.out.println("컨트롤러 airline data: " + airlineData);
			Integer maxPrice = service.getMaxPrice(departLoc, arrivalLoc, ticketType);

			System.out.println("maxPrice : " + maxPrice);
			System.out.println("adult : " + adult);
			System.out.println("child : " + child);
			System.out.println("baby : " + baby);
			System.out.println("departLoc : " + departLoc);
			System.out.println("arrivalLoc : " + arrivalLoc);
			System.out.println("seatGrade : " + seatGrade);
			System.out.println("ticketType: " + ticketType);

			md.addAttribute("departLoc", departLoc);
			md.addAttribute("arrivalLoc", arrivalLoc);
			md.addAttribute("adult", adult);
			md.addAttribute("child", child);
			md.addAttribute("baby", baby);
			md.addAttribute("ticketType", ticketType);
			md.addAttribute("airlineData", airlineData);
//			md.addAttribute("maxPrice", maxPriceStr);
			md.addAttribute("maxPrice", maxPrice);
			md.addAttribute("seatGrade", seatGrade);
		} else {
		}
		return "airline/airline_list";
	}

	// GET 요청을 처리하는 메소드
	@GetMapping("/airline/list_return")
	public String listReturn(Model md, @RequestParam String seatGrade, @RequestParam String departLoc,
			@RequestParam String arrivalLoc, @RequestParam String departDate, @RequestParam String arrivalDate,
			@RequestParam String adult, @RequestParam String child, @RequestParam String baby,
			@RequestParam String ticketType, @RequestParam String airlineCode, String flightNo) {

		System.out.println(" ==== 항목 리스트 데이터 값 ====");
		System.out.println("departLoc : " + departLoc + ",arrivalLoc" + arrivalLoc + "ticketType : " + ticketType
				+ ",adult : " + adult + ",child : " + child + ",baby : " + baby);

		List<AirlineInfoDto> airlineReturnData = service.getAirlineInfo(departLoc, arrivalLoc, departDate, arrivalDate,
				ticketType, seatGrade);
		AirlineInfoDto selectOneAirline = service.getSelectOne(airlineCode);

		for (AirlineInfoDto airlineInfo : airlineReturnData) {
			switch (seatGrade) {
			case "1": {
				airlineInfo.setSeatGrade("First class");
				break;
			}
			case "2": {
				airlineInfo.setSeatGrade("Business class");
				break;
			}
			case "3": {
				airlineInfo.setSeatGrade("Economy class");
				break;
			}
			}
		}

//		Integer maxPrice = service.getMaxPrice(departLoc, arrivalLoc, ticketType);
		// 모델에 데이터를 추가하여 뷰로 전달
		md.addAttribute("flightNo", flightNo);
		md.addAttribute("airlineCode", airlineCode);
		md.addAttribute("selectOneAirline", selectOneAirline);
		md.addAttribute("airlineReturnData", airlineReturnData);
		md.addAttribute("seatGrade", seatGrade);
		md.addAttribute("adult", adult);
		md.addAttribute("child", child);
		md.addAttribute("baby", baby);
		md.addAttribute("ticketType", ticketType);
//		md.addAttribute("maxPrice", maxPrice);

		// 각 항공편에 대해 30000에서 60000 사이의 랜덤 가격을 설정
		Random random = new Random();
		for (AirlineInfoDto air : airlineReturnData) {
			int randomPrice = 30000 + random.nextInt(30001); // 30000에서 60000 사이의 랜덤 값
			air.setSeatRandomPrice(randomPrice);
		}
		return "airline/airline_list_return";
	}

	// 항공 목록 정렬 옵션
	@GetMapping("airline/list_select_options/ajax")
	// @ResponseBody
	public String airlineSelectOptions(String seatGrade, String departLoc, String arrivalLoc, String departTimeLeft,
			String deaprtTimeRight, String arrivalTimeLeft, String arrivalTimeRight, String selectType, String viaType,
			String maxPrice, String ticketType, String adult, String child, String baby, Model md) {

		System.out.println("컨트롤러 목록 정렬");
		System.out.println("출발지 : " + departLoc);
		System.out.println("도착지 : " + arrivalLoc);
		System.out.println("정렬 타입 : " + selectType);
		System.out.println("경유 타입 : " + viaType);
		System.out.println("가격 최댓 값 : " + maxPrice);
		System.out.println("왕복/편도 : " + ticketType);
		System.out.println("좌석등급: " + seatGrade);
		System.out.println("adult: " + adult);
		System.out.println("child: " + child);
		System.out.println("baby: " + baby);
		System.out.println("ticketType: " + ticketType);

		md.addAttribute("adult", adult);
		md.addAttribute("child", child);
		md.addAttribute("baby", baby);
		md.addAttribute("ticketType", ticketType);

		List<AirlineInfoDto> SortData = service.getAirlineSideTime(departLoc, arrivalLoc, departTimeLeft,
				deaprtTimeRight, arrivalTimeLeft, arrivalTimeRight, selectType, viaType, maxPrice, ticketType,
				seatGrade);
		md.addAttribute("airlineData", SortData);
		Integer maxPrice2 = service.getMaxPrice(departLoc, arrivalLoc, ticketType);
		md.addAttribute("maxPrice", maxPrice2);
		log.debug("컨트롤러 디버깅 : " + SortData);

		for (AirlineInfoDto airlineInfo : SortData) {
			switch (seatGrade) {
			case "1": {
				airlineInfo.setSeatGrade("First class");
				break;
			}
			case "2": {
				airlineInfo.setSeatGrade("Business class");
				break;
			}
			case "3": {
				airlineInfo.setSeatGrade("Economy class");
				break;
			}
			}
		}

		return "airline/airline_list_section";
	}

	// 항공 돌아오는 목록 정렬 옵션
	@GetMapping("airline/list_select_options_return/ajax")
	// @ResponseBody
	public String airlineReturnSelectOptions(String seatGrade, String airlineCode, String departLoc, String arrivalLoc,
			String departTimeLeft, String deaprtTimeRight, String arrivalTimeLeft, String arrivalTimeRight,
			String selectType, String viaType, String maxPrice, String ticketType, Model md) {

		System.out.println("컨트롤러 목록 정렬");
		System.out.println("뱅기 편명 : " + airlineCode);
		System.out.println("출발지 : " + departLoc);
		System.out.println("도착지 : " + arrivalLoc);
		System.out.println("정렬 타입 : " + selectType);
		System.out.println("경유 타입 : " + viaType);
		System.out.println("ticketType: " + ticketType);
//		System.out.println("가격 최댓 값 : " + maxPrice);

		AirlineInfoDto selectOneAirline = service.getSelectOne(airlineCode);
		List<AirlineInfoDto> SortData = service.getAirlineSideTimeReturn(departLoc, arrivalLoc, departTimeLeft,
				deaprtTimeRight, arrivalTimeLeft, arrivalTimeRight, selectType, viaType, ticketType, seatGrade);
		System.out.println("항공사 이름: " + selectOneAirline.getAirlineName());
		md.addAttribute("selectOneAirline", selectOneAirline);
		md.addAttribute("airlineReturnData", SortData);
		md.addAttribute("seatGrade", seatGrade);
		md.addAttribute("ticketType" + ticketType);
//		Integer maxPrice2 = service.getMaxPrice(departLoc, arrivalLoc, ticketType);
//		md.addAttribute("maxPrice", maxPrice2);
		log.debug("컨트롤러 디버깅 : " + SortData);

		for (AirlineInfoDto airlineInfo : SortData) {
			switch (seatGrade) {
			case "1": {
				airlineInfo.setSeatGrade("First class");
				break;
			}
			case "2": {
				airlineInfo.setSeatGrade("Business class");
				break;
			}
			case "3": {
				airlineInfo.setSeatGrade("Economy class");
				break;
			}
			}
		}

		Random random = new Random();
		for (AirlineInfoDto air : SortData) {
			int randomPrice = 30000 + random.nextInt(30001); // 30000에서 60000 사이의 랜덤 값
			air.setSeatRandomPrice(randomPrice);
		}

		return "airline/airline_returnlist_section";
	}

	// 항공 메인 페이지
	@GetMapping("/airline/main")
	public String airlineMain(Model md) {
		List<AirlineInfoDto> recommendList = service.selectListRecommenedCheap();
		md.addAttribute("recommendList",recommendList);
		return "airline/airline_main"; // 폼이 있는 페이지로 이동
	}

	@PostMapping("/airline/main")
	public String airlineMainPost(String departLoc, String arrivalLoc, String departDate, String arrivalDate,
			String adult, String child, String baby, String seatGrade, String ticketType, RedirectAttributes rd) {
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
	public String airlinePay(Model md, @RequestParam String adult, @RequestParam String child,
			@RequestParam String baby, @RequestParam String ticketType, @RequestParam String departLoc,
			@RequestParam String arrivalLoc, @RequestParam String departDate, @RequestParam String arrivalDate,
			@RequestParam String seatPrice, @RequestParam String seatGrade, @RequestParam String flightNo,
			// return 붙은 param은 돌아오는 항공 편
			@RequestParam String departLocReturn, @RequestParam String arrivalLocReturn,
			@RequestParam String departDateReturn, @RequestParam String arrivalDateReturn,
			@RequestParam String airlineCodeReturn, @RequestParam String airlineCode,
			@RequestParam String seatPriceReturn, @RequestParam String seatGradeReturn, String flightNoReturn,
			Principal principal, HttpSession session) {

		// 왕복일 시
		if (airlineCodeReturn != null && !airlineCodeReturn.equals("")) {
			System.out.println("오는 비행기 항공코드 : " + airlineCodeReturn);
			Character dommesticReturn = service.selectOneReturnDomesticFunction(airlineCodeReturn);
			AirlineInfoDto airlineInfoReturn = service.selectOneAirlineInfo(airlineCodeReturn);
			md.addAttribute("airlineInfoReturn", airlineInfoReturn);
			md.addAttribute("dommesticReturn", dommesticReturn);

			// 이 부분 가는 편, 오는 편 구분 짓기 위해서 오는편은 뒤에 Return 붙여주세용
			System.out.println(airlineInfoReturn);
			if (airlineInfoReturn == null) {
				md.addAttribute("ticketType", 1);
				md.addAttribute("airlineName", "");
				md.addAttribute("airlineCode", "");
				md.addAttribute("departDate", "");
				md.addAttribute("departTime", "");
				md.addAttribute("departLoc", "");
				md.addAttribute("arrivalDate", "");
				md.addAttribute("arrivalLoc", "");
				md.addAttribute("arrivaldate", "");
			} else if (airlineInfoReturn != null) {
				md.addAttribute("ticketType", 2);
				md.addAttribute("airlineName", airlineInfoReturn.getAirlineName());
				md.addAttribute("airlineCode", airlineInfoReturn.getAirlineCode());
				md.addAttribute("departDate", airlineInfoReturn.getDepartDate());
				md.addAttribute("departTime", airlineInfoReturn.getDepartTime());
				md.addAttribute("departLoc", airlineInfoReturn.getDepartLoc());
				md.addAttribute("arrivalDate", airlineInfoReturn.getArrivalDate());
				md.addAttribute("arrivalLoc", airlineInfoReturn.getArrivalLoc());
				md.addAttribute("arrivaldate", airlineInfoReturn.getArrivalDate());
			}
		}

		System.out.println("가는 비행기 항공코드 : " + airlineCode);
		Character domestic = service.selectOneDomesticFunction(airlineCode);
		AirlineInfoDto airlineInfo = service.selectOneAirlineInfo(airlineCode);

		md.addAttribute("adult", adult);
		md.addAttribute("child", child);
		md.addAttribute("baby", baby);
		md.addAttribute("airlineCode", airlineCode);
		md.addAttribute("domestic", domestic);
		md.addAttribute("airlineInfo", airlineInfo);
		md.addAttribute("ticketType", ticketType);
		md.addAttribute("seatPrice", seatPrice);
		md.addAttribute("seatPriceReturn", seatPriceReturn);
		md.addAttribute("seatGrade", seatGrade);
		md.addAttribute("seatGradeReturn", seatGradeReturn);
		
		session.setAttribute("seatGrade", seatGrade);
		session.setAttribute("airlineCode", airlineCode);
		session.setAttribute("airlineCodeReturn", airlineCodeReturn);
		session.setAttribute("seatGradeReturn", seatGradeReturn);

		if (principal != null) {
			String userId = principal.getName();
			md.addAttribute("userId", userId);
		}

		System.out.println("어른 수: " + adult);
		System.out.println("소아 수: " + child);
		System.out.println("유아 수: " + baby);
		System.out.println("편도(1)왕복(2): " + ticketType);
		System.out.println("Domestic: " + domestic);
		System.out.println("가는 편 항공코드: " + airlineCode);
		System.out.println("오는 편 항공코드: " + airlineCodeReturn);
		System.out.println("가는 편 flightNO: " + flightNo);
		System.out.println("오는 편 flightNoReturn: " + flightNoReturn);
		System.out.println("가는 편 티켓 값: " + seatPrice);
		System.out.println("오는 편 티켓 값: " + seatPriceReturn);
		System.out.println("좌석 등급 : " + seatGrade);
		System.out.println("리턴 좌석 등급 : " + seatGradeReturn);

		return "airline/airline_pay";

	}

	// 예약자 정보 먼저 저장
	@ResponseBody
	@PostMapping("/airline/input/reserverInfo")
	public int customerInfo(@RequestBody AirlineReserverInfoDto reserverInfo, HttpSession session, Model model) {

		int result = service.insertReserverInfo(reserverInfo);

		session.setAttribute("airlineReserveCode", reserverInfo.getAirlineReserveCode());

		return result;
	}

//  탑승객 정보 및 직항/경유 정보 추가
	@ResponseBody
	@PostMapping("/airline/input/passengerInfo")
	public int passengerInfo(@RequestBody List<Map<String, Object>> passengerInfo, HttpSession session, Model model) {

		int result = 0;
		
		String airlineReserveCode = (String) session.getAttribute("airlineReserveCode");
		String seatGrade = (String) session.getAttribute("seatGrade");
		String seatGradeReturn = (String) session.getAttribute("seatGradeReturn");
		String airlineCode = (String)session.getAttribute("airlineCode");
		String airlineCodeReturn = (String)session.getAttribute("airlineCodeReturn");
		
		if (passengerInfo != null) {
			for (Map<String, Object> passenger : passengerInfo) {
				// 각 passenger 맵에서 값을 꺼내서 처리
				passenger.put("airlineReserveCode", airlineReserveCode);
			}
			result = service.insertPassengerInfo(passengerInfo);
		}
	
	    // 편도 예약인 경우
	    if (airlineCode != null && airlineCodeReturn == null) {
	    	DirectViaDto directDto = new DirectViaDto();
	        directDto.setAirlineReserveCode(airlineReserveCode);
	        directDto.setAirlineCode(airlineCode);
	        directDto.setSeatGrade(seatGrade);
	        
//	        directDtoList.add(directDto);
	        service.insertDirectViaDto(directDto);
	        
	    }
	    
	    // 왕복 예약인 경우
	    else if (airlineCode != null && airlineCodeReturn != null) {
	        
	    	// 가는 항공편 정보 저장
	    	DirectViaDto outboundDto = new DirectViaDto();
	    	outboundDto.setAirlineReserveCode(airlineReserveCode);
	    	outboundDto.setAirlineCode(airlineCode);
	    	outboundDto.setSeatGrade(seatGrade);
	        
//	    	directDtoList.add(outboundDto);
	    	service.insertDirectViaDto(outboundDto);
	        
	        // 오는 항공편 정보 저장
	    	DirectViaDto returnDto = new DirectViaDto();
	    	returnDto.setAirlineReserveCode(airlineReserveCode);
	    	returnDto.setAirlineCode(airlineCodeReturn);
	    	returnDto.setSeatGrade(seatGradeReturn);
	        
//	    	directDtoList.add(returnDto);
	    	service.insertDirectViaDto(returnDto);
	        
	    }
		
		
		return result;
	}

	@GetMapping("/airline/customer/reserve/pay/success")
	public String paySuccess() {
		
//		if (airlineInfoReturn == null) {
//			md.addAttribute("ticketType", 1);
//			md.addAttribute("airlineName", "");
//			md.addAttribute("airlineCode", "");
//			md.addAttribute("departDate", "");
//			md.addAttribute("departTime", "");
//			md.addAttribute("departLoc", "");
//			md.addAttribute("arrivalDate", "");
//			md.addAttribute("arrivalLoc", "");
//			md.addAttribute("arrivaldate", "");
//		} else if (airlineInfoReturn != null) {
//			md.addAttribute("ticketType", 2);
//			md.addAttribute("airlineName", airlineInfoReturn.getAirlineName());
//			md.addAttribute("airlineCode", airlineInfoReturn.getAirlineCode());
//			md.addAttribute("departDate", airlineInfoReturn.getDepartDate());
//			md.addAttribute("departTime", airlineInfoReturn.getDepartTime());
//			md.addAttribute("departLoc", airlineInfoReturn.getDepartLoc());
//			md.addAttribute("arrivalDate", airlineInfoReturn.getArrivalDate());
//			md.addAttribute("arrivalLoc", airlineInfoReturn.getArrivalLoc());
//			md.addAttribute("arrivaldate", airlineInfoReturn.getArrivalDate());
//		}
		
		return "airline/airline_pay_success";
	}

}