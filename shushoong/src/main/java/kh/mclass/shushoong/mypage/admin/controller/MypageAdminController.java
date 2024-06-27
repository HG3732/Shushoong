package kh.mclass.shushoong.mypage.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String managerHome() {
		return "mypage/admin/mypageAdminHome";
	}
	
	// 일반 회원 관리 페이지로 이동
	@GetMapping("/manager/customer")
	public String manageCustomer() {
		return "mypage/admin/manageCustomer";
	}
	
	//회원 검색 ajax
	@GetMapping("/manager/customer/searchMember.ajax")
	public String searchMember(Model model, String keyword, String currentPage) {
		//pageSize, pageBlockSize, CurrentPage
		int currentPageNum = 1;
		if(currentPage != null && !currentPage.equals("")) {
			try {
				currentPageNum = Integer.parseInt(currentPage);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("memberList", service.selectAllList(keyword, 8, 7, currentPageNum));
		return "mypage/admin/managecustomer/customerList";
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
		return "mypage/admin/managecustomer/viewMember";
	}
	
	// 사업자 회원 관리 페이지로 이동
	@GetMapping("/manager/business")
	public String manageBusiness() {
		return "mypage/admin/manageBusiness";
	}

	// 등록 상품 관리 페이지로 이동
	@GetMapping("/manager/product")
	public String manageProduct() {
		return "mypage/admin/manageProduct";
	}
	
	@GetMapping ("/hotel/test")
	public String test() {
		return "modal/hotelInformation";
	}

}
