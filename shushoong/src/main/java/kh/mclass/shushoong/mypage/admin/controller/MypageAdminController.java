package kh.mclass.shushoong.mypage.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.mclass.shushoong.mypage.admin.model.service.MypageAdminService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class MypageAdminController {

	@Autowired
	MypageAdminService service;
	
	// 관리자 로그인
	@GetMapping("/manager/login")
	public String managerLogin() {
		return "mypage/admin/adminLogin";
	}

	// 마이페이지 메인 페이지로 이동
	@GetMapping("/manager/home")
	public String managerHome(Model model) {
		model.addAttribute("notice", service.selectLatestNotice());
		model.addAttribute("qna", service.selectLatestFaq());
		return "mypage/admin/mypageAdminHome";
	}
	
	// 일반 회원 관리 페이지로 이동
	@GetMapping("/manager/customer")
	public String manageCustomer(Model model) {
		System.out.println(service.selectLatestFaq());
		model.addAttribute("latestFaq", service.selectLatestFaq());
		return "mypage/admin/manageCustomer";
	}
	
	//회원 검색 ajax
	@GetMapping("/manager/customer/searchMember.ajax")
	public String searchMember(Model model, String keyword) {
		model.addAttribute("result", service.selectAllList(keyword));
		return "mypage/admin/managecustomer/customerList";
	}
	
	//해당 회원의 1:1문의 내역 최신순 3개 조회
	@GetMapping("/manager/customer/viewQna.ajax")
	public String viewOnesNotice(Model model, String id) {
		model.addAttribute("latestFaq", service.selectOneLatestFaq(id));
		return "mypage/admin/managecustomer/userNotice";
	}
	
	
	//회원 세부 정보 조회 ajax
	@GetMapping("/manager/customer/viewMember.ajax")
	public String viewMember(Model model, String id) {
		model.addAttribute("userInfo", service.selectOne(id));
		if(service.selectHotelPayCount(id) != null) {
			model.addAttribute("userHotelPayInfo", service.selectHotelPayCount(id).size());
		} else {
			model.addAttribute("userHotelPayInfo", 0);
		}
		if(service.selectFlyPayCount(id) != null) {
			model.addAttribute("userFlyPayInfo", service.selectFlyPayCount(id).size());
		} else {
			model.addAttribute("userFlyPayInfo", 0);
		}
		model.addAttribute("faqCount", service.selectFAQCount(id));
		return "mypage/admin/managecustomer/viewMember";
	}
	
	//회원 계정 정지
	@GetMapping("/manager/customer/lockAccount.ajax")
	public String lockAccount(Model model, String id) {
		service.updateLockAccount(id);
		return "mypage/admin/managecustomer/viewMember";
	}
	
	//회원 계정 정지 해제
	@GetMapping("/manager/customer/unlockAccount.ajax")
	public String unlockAccount(Model model, String id) {
		service.updateUnlockAccount(id);
		return "mypage/admin/managecustomer/viewMember";
	}
	
	//장기 미사용 계정 검색
	@GetMapping("/manager/customer/searchSleeper.ajax")
	public String searchSleeper(Model model, String keyword) {
		model.addAttribute("result", service.selectDormantAccount(keyword));
		return "mypage/admin/managecustomer/sleeperList";
	}
	
	//장기 미사용 계정 세부 정보 조회
	@GetMapping("/manager/customer/viewSleeper.ajax")
	public String viewSleeper(Model model, String id) {
		model.addAttribute("userInfo", service.selectOne(id));
		model.addAttribute("term", service.selectUseTerm(id));
		model.addAttribute("faqCount", service.selectFAQCount(id));
		return "mypage/admin/managecustomer/viewSleeper";
	}
	
	//일괄 정지(list 데이터에 ""가 붙어서 인코딩해야하므로 Get대신 Post를 사용함)
	@PostMapping("/manager/customer/allLock.ajax")
	public String allLock(Model model, String keyword) {
			service.updateAllLock(keyword);
		return "mypage/admin/manageCustomer";
	}
	
	// 사업자 회원 관리 페이지로 이동
	@GetMapping("/manager/business")
	public String manageBusiness() {
		return "mypage/admin/manageBusiness";
	}

	//사업장 관리 페이지로 이동
	@GetMapping("/manager/product")
	public String manageProduct() {
		return "mypage/admin/manageProduct";
	}
	
	@PostMapping("/manager/product/searchHotel.ajax")
	public String searchProduct(Model model, String keyword) {
		model.addAttribute("result", service.selectProduct(keyword));
		return "mypage/admin/manageproduct/productList";
	}
	
	@GetMapping ("/hotel/test")
	public String test() {
		return "modal/hotelInformation";
	}

}
