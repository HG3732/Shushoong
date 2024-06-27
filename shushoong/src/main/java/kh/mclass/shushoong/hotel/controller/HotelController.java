package kh.mclass.shushoong.hotel.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.GsonBuilder;

import jakarta.servlet.http.HttpSession;
import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelFacilityDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelReviewDto;
import kh.mclass.shushoong.hotel.model.domain.HotelReviewOverallDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelRoomDto;
import kh.mclass.shushoong.hotel.model.domain.HotelViewDtoRes;
import kh.mclass.shushoong.hotel.model.service.HotelService;

//@Configuration
//@PropertySource("classpath:/keyfiles/apikey.properties")
@Controller
public class HotelController {

	@Autowired
	private HotelService service;
		
		//PortOne
//		@Value("${portone.store.key}")
//		private String portoneStoreKey;
//	
//		@Value("${portone.channel.key}")
//		private String portoneChannelKey;
//		
//		@Value("${portone.secret.key}")
//		private String portoneSecretKey;

		
	@GetMapping("/hotel/main")
	public String hotelMain(Model model, HttpSession session) {
		List<HotelDtoRes> hotHotelList = service.selectHotHotelList();
		model.addAttribute("hotHotelList", hotHotelList);
		session.setAttribute("userId", "ex1");
		return "hotel/hotel_main";
	}
	
	//main에서 지역, 인원 검색 시 호텔 리스트 표시
	@GetMapping("/hotel/list")
	public String hotelList(Model model, HttpSession session, String loc, String room, String adult, String child, String nation, String checkIn, String checkOut) {
		Integer child1 = Integer.parseInt(child)/2;
		Integer adult1 = Integer.parseInt(adult);
		String people = String.valueOf(child1+adult1);
		List<HotelDtoRes> result = service.selectAllHotelList(loc, people, null, null, null, null);
		Integer maxPrice = service.selectMaxRoomlPrice(loc, people, null);
		
		//좋아요 여부 검색
		String userId = (String)session.getAttribute("userId");
		List<String> likeList = service.selectLikeHotelList(loc, userId);
//		hotelDtoRes.setHotelPic(service.selectAllHotelList(loc));
//		hotelDtoRes.setHotelPic(service.selectAllHotelList(loc));
//		hotelDtoRes.setHotelPic(service.selectAllHotelList(loc));
		model.addAttribute("hotelList", result);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("likeList", likeList);
		session.setAttribute("nation", nation);
		session.setAttribute("checkIn", checkIn);
		session.setAttribute("checkOut", checkOut);
		session.setAttribute("adult", adult);
		session.setAttribute("child", child);
		session.setAttribute("room", room);
		System.out.println("session.nation : "+(String)session.getAttribute("nation"));
		return "hotel/hotel_list";
	}

	@GetMapping("/hotel/list/sort.ajax")
	public String hotelListSort(
			Model model,
			HttpSession session,
			String loccode,
			String people,
			String keyword,
			String maxPrice,
			String sortBy,
			String sortTo
			) {
		List<HotelDtoRes> result = service.selectAllHotelList(loccode, people, keyword, maxPrice, sortBy, sortTo);
		Integer maxPrice2 = service.selectMaxRoomlPrice(loccode, people, keyword);
		//session의 이름이 43행에 있는 List와 같아야 덮어쓰기됨, 다를 경우 기존 List + 새 List 출력
		String userId = (String)session.getAttribute("userId");
		List<String> likeList = service.selectLikeHotelList(loccode, userId);
		model.addAttribute("hotelList", result);
		model.addAttribute("maxPrice", maxPrice2);
		model.addAttribute("likeList", likeList);
		return "hotel/hotel_list_section";
	}
	
	@GetMapping("/hotel/list/price.ajax")
	public String hotelPriceSort(
			Model model,
			HttpSession session,
			String loccode,
			String people,
			String keyword,
			String maxPrice,
			String sortBy,
			String sortTo
			) {
		List<HotelDtoRes> result = service.selectAllHotelList(loccode, people, keyword, maxPrice, sortBy, sortTo);
		Integer maxPrice2 = service.selectMaxRoomlPrice(loccode, people, keyword);
		//session의 이름이 43행에 있는 List와 같아야 덮어쓰기됨, 다를 경우 기존 List + 새 List 출력
		String userId = (String)session.getAttribute("userId");
		List<String> likeList = service.selectLikeHotelList(loccode, userId);
		model.addAttribute("hotelList", result);
		model.addAttribute("maxPrice", maxPrice2);
		model.addAttribute("likeList", likeList);
		return "hotel/hotel_slide_bar";
	}
	
	//좋아요 테이블에 추가
	@GetMapping("/hotel/like/insert.ajax")
	public String insertHotelLike(Model model, HttpSession session, String hotelCode) {
		String userId = (String)session.getAttribute("userId");
		Integer result = service.insertHotelLike(userId, hotelCode);
		model.addAttribute("result", result);
		return "hotel/hotel_list_section";
	}
	
	//좋아요 테이블에서 삭제
		@GetMapping("/hotel/like/delete.ajax")
		public String deleteHotelLike(Model model, HttpSession session, String hotelCode) {
			String userId = (String)session.getAttribute("userId");
			Integer result = service.deleteHotelLike(userId, hotelCode);
			model.addAttribute("result", result);
			return "hotel/hotel_list_section";
		}
	
	@GetMapping("/hotel/view/{hotelCode}")
	public String hotelview(Model model,HttpSession session, @PathVariable String hotelCode) {
		//main 에서 session 안에 checkIn, checkOut 정보 들고들어와서 그 정보를 밑에서 model 안에 넣고 html 페이지에 뿌림
		
		System.out.println("=========호텔코드=======" + hotelCode);
		//호텔 상세정보들 출력
		
		//list에서 들고온 checkIn, checkOut 정보 다시 session 에 담아서 hotelview페이지에 뿌리기 
		model.addAttribute("checkIn", session.getAttribute("checkIn"));
		model.addAttribute("checkOut", session.getAttribute("checkOut"));
		model.addAttribute("adult", session.getAttribute("adult"));		
		model.addAttribute("child", session.getAttribute("child"));
		model.addAttribute("room", session.getAttribute("room"));
		
		String people = null;
		String adult = (String) session.getAttribute("adult");
		String child = (String) session.getAttribute("child");
		Integer adult1 = Integer.parseInt(adult);
		Integer child1 = Integer.parseInt(child);
		people = String.valueOf(child1+adult1);
		//session에 담긴 정보는 string 으로 뽑을 수 있기 때문에 위에서 integer로 session에 넣었기 때문에 session 에서 꺼낼 때 string으로 뽑아주고 그 다음에 integer로 다시 변환해주기
		
		System.out.println(people + "=======================");
		
		HotelViewDtoRes result = service.selectOneHotel(hotelCode, people);
		//ajax와는 다르게 이 문장으로 인해서 service 가서 쭉쭉가서 db에서 정보 조회해서 dto에 넣고 다시 돌아옴
		//돌아온 데이터 밑에 넣음
		
		model.addAttribute("hotelCode", hotelCode);
		model.addAttribute("hotelViewList", result);
		//넣어서 보내면 사라짐(일회성) - setAttribute 같은 얘
		
		
		//편의시설
		List<HotelFacilityDtoRes> facilitylist = service.selectHotelFacility(hotelCode);
		for(int i = 0; i<facilitylist.size(); i++) {
			switch(facilitylist.get(i).getHotelFacCat()){
				case "0":
					facilitylist.get(i).setHotelFacCat("무선인터넷");
					break;
				case "1":
					facilitylist.get(i).setHotelFacCat("주차");
					break;
				case "2":
					facilitylist.get(i).setHotelFacCat("레스토랑");
					break;
				case "3":
					facilitylist.get(i).setHotelFacCat("수영장");
					break;
				case "4":
					facilitylist.get(i).setHotelFacCat("피트니스센터");
					break;
				case "5":
					facilitylist.get(i).setHotelFacCat("에어컨");
					break;
				case "6":
					facilitylist.get(i).setHotelFacCat("바");
					break;
				default:
					facilitylist.get(i).setHotelFacCat("카지노");
					break;		
			}
			
		}
		model.addAttribute("facilitylist", facilitylist);
		
		//전체 평균 리뷰
		List<HotelReviewOverallDtoRes> reviewOverallDto = service.selectReviewOverall(hotelCode);
		model.addAttribute("reviewOverallDto", reviewOverallDto);
		
		return "hotel/hotel_view";
	}
	
	//페이징 처리
	@GetMapping("/hotel/view/review.ajax")
	public String hotelPaging(
			Model model,
			String hotelCode,
			String currentPage
		) {
		//정보를 받아올 때 어떤것을 참조해서 받아올지 --> 매개변수(java에서의 getParameter 역할을 대신해줌)
		
//		한 페이지 몇개씩 나올지 정하기(한페이지당글수) -> 3개
		int reviewNum = 1;
		
//		화면 하단에 나타날 페이지수는 5개(1, 2, 3, 4, 5)
		int reviewPageNum = 2;
		
//		누른 현재 페이지 알아야함(어떻게 기준으로 삼을지..)
		int currentPageNum = 1;  // 기본1
		
		if(currentPage != null && !currentPage.equals("") ) {
			try {
				currentPageNum = Integer.parseInt(currentPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Map<String, Object> reviewDetailDto = service.selectReviewDetailList(hotelCode, reviewNum, reviewPageNum, currentPageNum);
		
		List<HotelReviewDto> reviewDtoList = (List<HotelReviewDto>)reviewDetailDto.get("reviewDtoList");
		// Map 에 묶인 얘를 꺼내면 java에서 object로 인식해서 강제형변환 해줘야함
		// 어쩔수 없이 뜨는 오류..... 그냥 그러려니 하고 넘어가자...
		
		for(int i = 0; i<reviewDtoList.size(); i++) {
			switch(reviewDtoList.get(i).getTripperCat()){
				case "0":
					reviewDtoList.get(i).setTripperCat("혼자");
					break;
				case "1":
					reviewDtoList.get(i).setTripperCat("커플/부부");
					break;
				case "2":
					reviewDtoList.get(i).setTripperCat("가족");
					break;
				case "3":
					reviewDtoList.get(i).setTripperCat("단체");
					break;
				default:
					reviewDtoList.get(i).setTripperCat("출장");
					break;		
			}
		}
		model.addAttribute("reviewDetailDto", reviewDetailDto);		
		//매개변수를 가지고 가서 mapper에서 조회해서 dto에 넣고 여기로 가져와서 model 안에 넣음
		
		//System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(reviewDetailDto));
		
		return "hotel/hotel_view_review";
		//위에서 넣은 값을 session안에 담고 그 session을 여기로 보내서 띄움
	}
	
	@PostMapping("/hotel/customer/reserve/pay")
	public String hotelPay(HttpSession session, Model model, String hotel, String hotelCode, String roomCat, String roomCatDesc, String roomAtt, String hotelPrice, String roomCap) {
		model.addAttribute("hotel", hotel);
		model.addAttribute("hotelCode", hotelCode);
		model.addAttribute("roomCat", roomCat);
		model.addAttribute("roomCatDesc", roomCatDesc);
		model.addAttribute("roomAtt", roomAtt);
		model.addAttribute("hotelPrice", hotelPrice);
		model.addAttribute("roomCap", roomCap);
		
		//session 에 담겨있는 checkIn, checkOut 정보 model 에 담아서 html 페이지로 뿌리기
		model.addAttribute("checkIn", session.getAttribute("checkIn"));
		model.addAttribute("checkOut", session.getAttribute("checkOut"));
		model.addAttribute("userId", session.getAttribute("userId"));
		model.addAttribute("adult", session.getAttribute("adult"));
		model.addAttribute("child", session.getAttribute("child"));
		model.addAttribute("nation", session.getAttribute("nation"));
		model.addAttribute("room", session.getAttribute("room"));
		//이미 session 안에 이 위에 정보들 있는데 굳이 화면에 값 출력해서 결제페이지 이동할 떄 가지고 가야하나? 그냥 session 불러서 넣으면 안되나?
		
		System.out.println(roomCap + "=========================");
		
		return "hotel/hotel_pay";
	}

//	@PostMapping("/hotel/payment")
//	@ResponseBody
//	public int hotelPayment(String paymentId, String totalAmount, String[] items, String buyId, Principal principal) throws IOException, InterruptedException{
////		ajax로 보내지는 데이터 () 안에 작성
//		HttpRequest request = HttpRequest.newBuilder()
//			    .uri(URI.create("https://api.portone.io/payments/" + 결제번호 + "?storeId=" + storeId))
//			    //payments/" + 결제번호 - PathVariable(경로) - 어디로가냐 - 경로에 따라 목적지 달라짐... , ?storeId=" + storeId = 결제API에 전달할 data - 뭘 가지고 가냐(바껴도 상관없음)
//			    .header("Content-Type", "application/json")
//			    .header("Authorization", "PortOne " + paySecret)
//			    .method("GET", HttpRequest.BodyPublishers.ofString("{}"))
//			    .build();
//			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//			System.out.println(response.body());
//			
//		
//			// 결제 단건 조회 응답
//			Map<String, Object> responseBody = gson.fromJson(response.body(), Map.class);
//			System.out.println("responseBody >>>>>>>>> "+responseBody.toString());
//			
//			// 응답 중 결제 금액 세부 정보 항목 추출
//			Map<String, Object> amount = gson.fromJson(gson.toJson(responseBody.get("amount")), Map.class);
//			System.out.println("amount >>>>>>>>> "+amount);
//			// 그 중 지불된 금액
//			double paid = (double) amount.get("paid");
//			
//			int result;
//			// 결제 금액과 지불된 금액이 같다면
//			if(Double.parseDouble(totalAmount) == paid) {
//				Map<String, Object> map = new HashMap<>();
//				map.put("memEmail", principal.getName());
//				map.put("buyId", buyId);
//				List<String> list = new ArrayList<>();
//				for(int i = 0; i < items.length; i++) {
//					list.add(items[i]);
//				}
//				map.put("list", list);
//				result = storeService.pay(map);
//				return result;
//			}else {
//				return result = 0;
//			}
//			
//		
//			
//	}
	

	//지역, 인원수 선택 안한채로 hotel_list를 url에 직접 입력하여 진입할 경우 예외처리  
//	@ExceptionHandler(Exception.class)
//	public String ExceptionHandler() {
//		return "hotel/hotel_main";
//	}
}
