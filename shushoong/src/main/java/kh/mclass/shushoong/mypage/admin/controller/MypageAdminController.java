package kh.mclass.shushoong.mypage.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.mclass.shushoong.mypage.admin.model.service.MypageAdminService;
import kh.mclass.shushoong.servicecenter.model.domain.NoticeDto;
import kh.mclass.shushoong.servicecenter.model.service.NoticeService;
import kh.mclass.shushoong.servicecenter.model.service.OnlineQnAService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class MypageAdminController {

	@Autowired
	MypageAdminService service;
	
	@Autowired
	private OnlineQnAService qnAService;
	
	@Autowired
	private NoticeService noticeService;
	
	int pageSize = 12;
	int pageBlockSize = 5;
	int currentPageNum = 1;
	
	int homePageSize = 5;
	
	// 마이페이지 메인 페이지로 이동
	@GetMapping("/manager/home")
	public String managerHome(Model model, String category, String keyword, String questCat) {
		// 공지 목록 출력 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId =  authentication.getName();
		String userGrade = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("anonymousUser"); // 기본값 설정
		
		String noticeCategory = null;
		int noticeTotalCount = noticeService.selectTotalCount(userGrade,noticeCategory);
		int noticeTotalPageCount = (noticeTotalCount%homePageSize == 0) ? noticeTotalCount/homePageSize : noticeTotalCount/homePageSize + 1;
		int noticeStartPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
		int noticeEndPageNum = (noticeStartPageNum+pageBlockSize > noticeTotalPageCount) ? noticeTotalPageCount : noticeStartPageNum + pageBlockSize - 1;
		List<NoticeDto> noticeDto =  noticeService.selectNoticeAllList(homePageSize,pageBlockSize,currentPageNum,userGrade);
		model.addAttribute("currentPageNum", currentPageNum);
		model.addAttribute("totalPageCount", noticeTotalCount);
		model.addAttribute("startPageNum", noticeStartPageNum);
		model.addAttribute("endPageNum", noticeEndPageNum);
		model.addAttribute("noticeDto", noticeDto);
		model.addAttribute("userGrade", userGrade);
		
		// qna 목록 출력
		int qnaTotalCount = qnAService.selectTotalCount(null, category, keyword, questCat);
		model.addAttribute("result", qnAService.selectAllList(homePageSize, pageBlockSize, currentPageNum, null, category, keyword, questCat));
		int qnaTotalPageCount = (qnaTotalCount%homePageSize == 0) ? qnaTotalCount/homePageSize : qnaTotalCount/homePageSize + 1;
		int qnaStartPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
		int qnaEndPageNum = (qnaStartPageNum+pageBlockSize > qnaTotalPageCount) ? qnaTotalPageCount : qnaStartPageNum + pageBlockSize - 1;
		
		model.addAttribute("currentPageNum", currentPageNum);
		model.addAttribute("totalPageCount", qnaTotalPageCount);
		model.addAttribute("startPageNum", qnaStartPageNum);
		model.addAttribute("endPageNum", qnaEndPageNum);
		
		
		model.addAttribute("notice", service.selectLatestNotice());
		model.addAttribute("qna", service.selectLatestFaq());
		return "mypage/admin/mypageAdminHome";
	}
	
//일반 회원 관리
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
		int memCat = 0;
		model.addAttribute("result", service.selectAllList(keyword, memCat));
		return "mypage/admin/managemember/customerList";
	}
	
	//해당 회원의 1:1문의 내역 최신순 3개 조회
	@GetMapping("/manager/customer/viewQna.ajax")
	public String viewOnesNotice(Model model, String id) {
		model.addAttribute("latestFaq", service.selectOneLatestFaq(id));
		return "mypage/admin/managemember/userQnaList";
	}
	
	
	//회원 세부 정보 조회 ajax
	@GetMapping("/manager/customer/viewMember.ajax")
	public String viewMember(Model model, String id, String currentPageNum) {
		int currentPage = 1;
		if(currentPageNum != null || !currentPageNum.equals("")) {
			currentPage = Integer.parseInt(currentPageNum);
		}
		model.addAttribute("userInfo", service.selectOne(id));
		model.addAttribute("userHotelPayInfo", service.selectHotelPayList(id, currentPage));
		model.addAttribute("userHotelPayCount", service.selectHotelPayCount(id));
		model.addAttribute("userFlyPayInfo", service.selectFlyPayList(id, currentPage));
		model.addAttribute("userFlyPayCount", service.selectFlyPayCount(id));
		model.addAttribute("faqCount", service.selectFAQCount(id));
		return "mypage/admin/managemember/viewMember";
	}	
	
	//결제 내역 페이징
	@PostMapping("/manager/customer/viewpay.ajax")
	public String viewPay(Model model, String id, String currentPageNum) {
		int currentPage = 1;
		if(currentPageNum != null || !currentPageNum.equals("")) {
			currentPage = Integer.parseInt(currentPageNum);
		}
		model.addAttribute("userInfo", service.selectOne(id));
		model.addAttribute("userHotelPayInfo", service.selectHotelPayList(id, currentPage));
		model.addAttribute("userHotelPayCount", service.selectHotelPayCount(id));
		model.addAttribute("userFlyPayInfo", service.selectFlyPayList(id, currentPage));
		model.addAttribute("userFlyPayCount", service.selectFlyPayCount(id));
		model.addAttribute("faqCount", service.selectFAQCount(id));
		return "mypage/admin/managemember/userInfoModal";
	}
	
	//회원 계정 정지
	@GetMapping("/manager/customer/lockAccount.ajax")
	public String lockAccount(Model model, String id) {
		service.updateLockAccount(id);
		return "mypage/admin/managemember/viewMember";
	}
	
	//회원 계정 정지 해제
	@GetMapping("/manager/customer/unlockAccount.ajax")
	public String unlockAccount(Model model, String id) {
		service.updateUnlockAccount(id);
		return "mypage/admin/managemember/viewMember";
	}
	
	//장기 미사용 계정 검색
	@PostMapping("/manager/customer/searchSleeper.ajax")
	public String searchSleeper(Model model, String keyword) {
		int memCat = 0;
		model.addAttribute("result", service.selectDormantAccount(keyword, memCat));
		return "mypage/admin/managemember/sleeperList";
	}
	
	//장기 미사용 계정 세부 정보 조회
	@GetMapping("/manager/customer/viewSleeper.ajax")
	public String viewSleeper(Model model, String id) {
		model.addAttribute("userInfo", service.selectOne(id));
		model.addAttribute("term", service.selectUseTerm(id));
		model.addAttribute("faqCount", service.selectFAQCount(id));
		return "mypage/admin/managemember/viewSleeper";
	}
	
	//일괄 정지(list 데이터에 ""가 붙어서 인코딩해야하므로 Get대신 Post를 사용함)
	@PostMapping("/manager/customer/allLock.ajax")
	public String allLock(Model model, String keyword) {
			service.updateAllLock(keyword);
		return "mypage/admin/manageCustomer";
	}

//사업자 회원 관리
	// 사업자 회원 관리 페이지로 이동
	@GetMapping("/manager/business")
	public String manageBusiness() {
		return "mypage/admin/manageBusiness";
	}
	
	//회원 검색 ajax
	@GetMapping("/manager/business/searchMember.ajax")
	public String searchBusiness(Model model, String keyword) {
		int memCat = 1;
		model.addAttribute("result", service.selectAllList(keyword, memCat));
		return "mypage/admin/managemember/customerList";
	}
	
	//회원 세부 정보 조회 ajax
	@GetMapping("/manager/business/viewMember.ajax")
	public String viewBusiness(Model model, String id) {
		model.addAttribute("userInfo", service.selectOne(id));
		model.addAttribute("productcount", service.selectProductCount(id));
		model.addAttribute("faqCount", service.selectFAQCount(id));
		return "mypage/admin/managemember/viewMember";
	}
	
	//상품 미등록 계정 검색
	@PostMapping("/manager/business/searchNosale.ajax")
	public String searchNosaler(Model model, String keyword) {
		model.addAttribute("result", service.selectNoSaleAccount(keyword));
		return "mypage/admin/managemember/sleeperList";
	}
	
	//장기 미사용 계정 검색
	@PostMapping("/manager/business/searchSleeper.ajax")
	public String searchBusinessSleeper(Model model, String keyword) {
		int memCat = 1;
		model.addAttribute("result", service.selectDormantAccount(keyword, memCat));
		return "mypage/admin/managemember/sleeperList";
	}

	//사업장 관리 페이지로 이동
	@GetMapping("/manager/product")
	public String manageProduct() {
		return "mypage/admin/manageProduct";
	}
	
	//사업장 검색
	@PostMapping("/manager/product/searchProduct.ajax")
	public String searchProduct(Model model, String category, String keyword, String currentPage) {
		
		currentPageNum = 1;
		if(currentPage != null && !currentPage.equals("")) {
			try {
				currentPageNum = Integer.parseInt(currentPage);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		int totalCount = service.selectAllProductCount(category, keyword);
		int totalPageCount = (totalCount%pageSize == 0) ? totalCount/pageSize : totalCount/pageSize + 1;
		
		int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
		int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum + pageBlockSize - 1;
		
		
		model.addAttribute("currentPageNum", currentPageNum);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("result", service.selectProduct(pageSize, pageBlockSize, currentPageNum, category, keyword));
		return "mypage/manageproduct/productList";
	}
	
	@GetMapping ("/hotel/test")
	public String test() {
		return "modal/hotelInformation";
	}

}
