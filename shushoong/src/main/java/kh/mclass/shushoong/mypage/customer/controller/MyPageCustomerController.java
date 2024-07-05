package kh.mclass.shushoong.mypage.customer.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.mypage.customer.model.repository.MypageCustomerRepository;
import kh.mclass.shushoong.mypage.customer.model.service.MypageCustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
@Slf4j
public class MyPageCustomerController {
	
	@Autowired
	private MypageCustomerRepository repository;
	
	private final MypageCustomerService service;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();	
	
	// 마이페이지 메인 페이지로 이동
	@GetMapping("/mypage/home")
	public String mypageHome() {
		return "mypage/customer/mypageCustomerHome";
	}
	
	// 비밀번호 확인 페이지로 이동 
	@GetMapping("/check/password")
	public String checkPwd() {
		return "mypage/customer/mypageCheckPwd";
	}
	
	@PostMapping(value = "/check/password")
	public String PwdChecking(@RequestParam("userPwd") String userPwd, 
							Authentication auth, RedirectAttributes rttr) {
		
		User user = (User) auth.getPrincipal();
		String member = repository.pwdChecking(user.getUsername());
		if(encoder.matches(userPwd, member)) {
			log.info("password 확인 완료");
			return "redirect:/customer/my/information";
		} else {
			rttr.addFlashAttribute("message", "오류");
			return "redirect:/customer/check/password";
		}
	}
	
	
	// 개인정보 수정 페이지로 이동
	@GetMapping("/my/information")
	public String correctInfoCustomer(Principal principal, ModelMap modelMap) {
		String userId = principal.getName();
		MemberDto dto = repository.selectOne(userId);
		modelMap.addAttribute("dto",dto);
		return "mypage/customer/mypageCorrectInfoCustomer";
	}
	
	@PostMapping("/changeInfo.ajax")
	public String changePwd(@RequestParam("userPwd") String userPwd,
							@RequestParam("emailReceive") String emailReceive,
							@RequestParam("msgReceive") String msgReceive,
							Principal principal, RedirectAttributes rttr,
							@RequestParam Map<String, Object> paramMap) {

		String userId = principal.getName();
		paramMap.put("userPwd", encoder.encode(userPwd));
		paramMap.put("userId", userId);
		paramMap.put("emailReceive", emailReceive);
		paramMap.put("msgReceive", msgReceive);
		
		int result = service.resetInfo(paramMap);
		
		String message = null;
		
		if(result > 0) {
			message = "변경사항이 변경되었습니다.";
			
		} else {
			message = "변경에 실패했습니다.";
		}
		
		rttr.addFlashAttribute("message", message);
		return "redirect:/business/my/information";
	}
	
	// 마이페이지 항공 리스트 페이지로 이동
	@GetMapping("/mypage/reserved/airline/list")
	public String getMethodName() {
		return "mypage/customer/mypageCustomerReservedAlirlineList";
	}
	
	// 마이페이지 호텔 리스트 페이지로 이동
	@GetMapping("/mypage/reserved/hotel/list")
	public String hotelReserve(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//security로 userId 불러오기
		String userId = authentication.getName();
		model.addAttribute("userId", userId);
		model.addAttribute("reserveList", service.selectReservedHotelList(userId));
		//service에서 불러온 값 변수 선언 따로 안하고 바로 model 에 넣기
		
		return "mypage/customer/mypageCustomerReservedHotelList";
	}
	
	// 마이페이지 호텔 예매내역 하나 선택
	@GetMapping("/mypage/reserved/hotel/{userId}/{hotelReserveCode}")
	public String selectOneHotel(Model model, @PathVariable("userId") String userId, @PathVariable("hotelReserveCode") String hotelReserveCode) {
		//input 태그에 있는 name 여기에 씀
		//getParameter 역할
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loginId = authentication.getName();
			if(!userId.equals(loginId)) {
				return "home";
				
			} else {
	
				Map<String, Object> reservationDetails = service.selectOneReservedHotelList(userId, hotelReserveCode);
				
				if (reservationDetails != null && !reservationDetails.isEmpty()) {
					// 'requestSum' 값을 Double로 변환하여 사용
					Integer requestNum = ((BigDecimal) reservationDetails.get("REQUEST_SUM")).intValue();
					//oracle 데이터베이스의 NUMBER(3) 타입은 최대 3자리의 정수를 표현할 수 있는 숫자형 데이터 타입이라서 
					//Java에서는 이를 BigDecimal 또는 Integer로 처리해야함
					
					String requestSumStr = "";
					//String requestSumStr = requestNum.toString(); -> 이렇게 쓰니까 당연히 숫자가 들어가지...
					
					if ( (requestNum & 1) != 0) {
						requestSumStr += "싱글, ";
					} 
					if ((requestNum & 2) != 0) {
						requestSumStr += "트윈, ";
					} 
					if ((requestNum & 4) != 0) {
						requestSumStr += "더블, ";
					}
					if ((requestNum & 8 ) != 0) {
						requestSumStr += "금연실, ";
					}
					if ((requestNum & 16) != 0) {
						requestSumStr += "흡연실, ";
					}
					if ((requestNum & 32) != 0) {
						requestSumStr += "고층, ";
					}
					
					if(!requestSumStr.isEmpty()) {
						//requestDesc 가 비어있지 않다면..
						
						requestSumStr = requestSumStr.substring(0, requestSumStr.length() - 2);
						//0 은 index를 나타냄
						//-2 는 쉼표와 공백 제거하기 위해 빼기
					}
					
					model.addAttribute("requestDesc", requestSumStr);
				} else {
					
				}
				
				model.addAttribute("reserveList", service.selectOneReservedHotelList(userId, hotelReserveCode));
				
				return "mypage/customer/mypageCustomerReservedHotel";
	
			}
	}
	
	@GetMapping("/mypage/hotel/interested")
	public String interestedPage(
			Principal principal,
			Model md
			) {
		String userId = principal.getName();
		
		List<HotelDtoRes> hotelList = service.selectListInterestedHotel(userId);
		md.addAttribute("hotelList",hotelList);
		
		return "mypage/customer/myapgeHotelLike";
	}

	
}
