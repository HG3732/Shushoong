package kh.mclass.shushoong.servicecenter.model.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.mail.Multipart;
import jakarta.servlet.http.HttpSession;
import kh.mclass.shushoong.servicecenter.model.domain.NoticeDto;
import kh.mclass.shushoong.servicecenter.model.domain.NoticeFileDto;
import kh.mclass.shushoong.servicecenter.model.service.NoticeService;
import kh.mclass.shushoong.servicecenter.model.service.OnlineQnAService;
import lombok.RequiredArgsConstructor;

@Controller
public class ServiceCenterController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private OnlineQnAService service;
	
	
	int pageSize = 10;
	int pageBlockSize = 3;
	int currentPageNum = 1;
	
	@GetMapping("/support/qna/list")
	public String qnaList(Model model, String category, String keyword, String questCat) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication.getAuthorities().toString().contains("admin")) {
			int totalCount = service.selectTotalCount(null, category, keyword, questCat);
			model.addAttribute("result", service.selectAllList(pageSize, pageBlockSize, currentPageNum, null, category, keyword, questCat));
			int totalPageCount = (totalCount%pageSize == 0) ? totalCount/pageSize : totalCount/pageSize + 1;
			int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
			int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum + pageBlockSize - 1;
			
			model.addAttribute("currentPageNum", currentPageNum);
			model.addAttribute("totalPageCount", totalPageCount);
			model.addAttribute("startPageNum", startPageNum);
			model.addAttribute("endPageNum", endPageNum);
		} else {
			String loginId = authentication.getName();
			int totalCount = service.selectTotalCount(loginId, category, keyword, questCat);
			model.addAttribute("result", service.selectAllList(pageSize, pageBlockSize, currentPageNum, loginId, category, keyword, questCat));
			int totalPageCount = (totalCount%pageSize == 0) ? totalCount/pageSize : totalCount/pageSize + 1;
			int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
			int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum + pageBlockSize - 1;
			
			model.addAttribute("currentPageNum", currentPageNum);
			model.addAttribute("totalPageCount", totalPageCount);
			model.addAttribute("startPageNum", startPageNum);
			model.addAttribute("endPageNum", endPageNum);
		}
		
		return "servicecenter/onlineQnAlist";
	}
	
	@GetMapping("/support/notice/search.ajax")
	public String searchQnA(Model model, String pageNum, String category, String keyword, String questCatCategory) {
		currentPageNum = 1;
		if(pageNum != null && !pageNum.equals("")) {
			try {
				currentPageNum = Integer.parseInt(pageNum);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication.getAuthorities().toString().contains("admin")) {
			int totalCount = service.selectTotalCount(null, category, keyword, questCatCategory);
			model.addAttribute("result", service.selectAllList(pageSize, pageBlockSize, currentPageNum, null, category, keyword, questCatCategory));
			int totalPageCount = (totalCount%pageSize == 0) ? totalCount/pageSize : totalCount/pageSize + 1;
			int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
			int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum + pageBlockSize - 1;
			
			model.addAttribute("currentPageNum", currentPageNum);
			model.addAttribute("totalPageCount", totalPageCount);
			model.addAttribute("startPageNum", startPageNum);
			model.addAttribute("endPageNum", endPageNum);
		} else {
			String loginId = authentication.getName();
			int totalCount = service.selectTotalCount(loginId, category, keyword, questCatCategory);
			model.addAttribute("result", service.selectAllList(pageSize, pageBlockSize, currentPageNum, loginId, category, keyword, questCatCategory));
			int totalPageCount = (totalCount%pageSize == 0) ? totalCount/pageSize : totalCount/pageSize + 1;
			int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
			int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum + pageBlockSize - 1;
			
			model.addAttribute("currentPageNum", currentPageNum);
			model.addAttribute("totalPageCount", totalPageCount);
			model.addAttribute("startPageNum", startPageNum);
			model.addAttribute("endPageNum", endPageNum);
		}
		return "servicecenter/QnAlist";
	}
	
	@GetMapping("/support/qna/view/{faqId}")
	public String viewQnA(Model model, @PathVariable("faqId") String faqId) {
		model.addAttribute("result", service.selectOneQna(faqId));
		return "servicecenter/viewQnA";
	}
	
	@PostMapping("/support/qna/write/answer.ajax")
	public String writeQnAanswer(Model model, String faqId, String ansContent) {
		service.updateAnswer(faqId, ansContent);
		model.addAttribute("result", service.selectOneQna(faqId));
		return "servicecenter/viewQnA";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 마이페이지 공지사항
	@GetMapping("/support/notice/list")
	public String noticeList (Model md,
			String pageNum) {
		System.out.println("리스트 컨트롤러 pageNum : " + pageNum);
		if (pageNum != null && !pageNum.equals("")) {
			try {
				currentPageNum = Integer.parseInt(pageNum);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		System.out.println("리스트 컨트롤러 currentPageNum : " + currentPageNum);
		
		int totalCount = noticeService.selectTotalCount();
		int totalPageCount = (totalCount%pageSize == 0) ? totalCount/pageSize : totalCount/pageSize + 1;
		
		int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
		int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum + pageBlockSize - 1;
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		
		List<NoticeDto> noticeDto =  noticeService.selectNoticeAllList(pageSize,pageBlockSize,currentPageNum,userId);
		md.addAttribute("currentPageNum", currentPageNum);
		md.addAttribute("totalPageCount", totalPageCount);
		md.addAttribute("startPageNum", startPageNum);
		md.addAttribute("endPageNum", endPageNum);
		md.addAttribute("noticeDto", noticeDto);
		md.addAttribute("userId", userId);
		return "servicecenter/notice";
	}
	
	@GetMapping("/support/notice/list.ajax")
	public String noticeListAjax (Model md,
	String pageNum) {
		System.out.println("ajax 컨트롤러 pageNum : " + pageNum);
		if (pageNum != null && !pageNum.equals("")) {
			try {
				currentPageNum = Integer.parseInt(pageNum);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		System.out.println("ajax 컨트롤러 currentPageNum : " + currentPageNum);
		int totalCount = noticeService.selectTotalCount();
		int totalPageCount = (totalCount%pageSize == 0) ? totalCount/pageSize : totalCount/pageSize + 1;
		
		int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
		int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum + pageBlockSize - 1;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		List<NoticeDto> noticeDto =  noticeService.selectNoticeAllList(pageSize,pageBlockSize,currentPageNum,userId);
		md.addAttribute("currentPageNum", currentPageNum);
		md.addAttribute("totalPageCount", totalPageCount);
		md.addAttribute("startPageNum", startPageNum);
		md.addAttribute("endPageNum", endPageNum);
		md.addAttribute("noticeDto", noticeDto);
		return "servicecenter/notice_section";
	}
	
	// 공지사항 작성
	@GetMapping("/support/notice/write")
	public String getNoticeWrite (Model md) {
		return "servicecenter/notice_write";
	}
	
	@PostMapping("/support/notice/write")
	public String PostNoticeWrite (// RedirectAttributes rd, 
			String noticeTitle, String noticeContent, MultipartFile noticeFile, String noticeCategory,
			Model md
			) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		
		System.out.println("유저 아이디 : " + userId);
		System.out.println("공지 작성 포스트 컨트롤러");
		System.out.println("noticeTitle : " + noticeTitle);
		System.out.println("noticeContent : " + noticeContent);
		System.out.println("noticeFile : " + noticeFile);
		System.out.println("noticeCategory : " + noticeCategory);
		
	    NoticeDto dto = new NoticeDto();
	    dto.setNoticeTitle(noticeTitle);
	    dto.setNoticeContent(noticeContent);
	    // noticeFile과 noticeCategory가 NoticeDto에 있는 경우 설정
	    dto.setNoticeCategory(noticeCategory);
	    dto.setUserId(userId); 
//	    dto.setNoticeCategory("defaultUserGrade"); 
	    md.addAttribute("loginUserId", userId);
        List<NoticeFileDto> fileId = new ArrayList<>();
        if (noticeFile != null && !noticeFile.isEmpty()) {
            NoticeFileDto fileDto = new NoticeFileDto();
            fileDto.setOriginalFilename(noticeFile.getOriginalFilename());
            fileDto.setSavedFilePathName("/uploads/" + noticeFile.getOriginalFilename()); // 예시 경로, 실제 업로드 경로에 맞게 수정 필요
            fileId.add(fileDto);
        }
        dto.setFileId(fileId);
	    
	    int insertNotice = noticeService.insertNotice(dto);
		
//		rd.addAttribute("noticeTitle", noticeTitle);
//		rd.addAttribute("noticeContent", noticeContent);
//		rd.addAttribute("noticeFile", noticeFile);
//		rd.addAttribute("noticeCategory", noticeCategory);
		return "redirect:/support/notice/list";
	}
	
	@GetMapping("/support/notice/view/{noticeId}")
	public String viewNotice(Model md, @PathVariable("noticeId") String noticeId) {
//		String userId
		md.addAttribute("noticeDto", noticeService.selectOneNotice(noticeId));
		return "servicecenter/notice_view";
	}

	
}
