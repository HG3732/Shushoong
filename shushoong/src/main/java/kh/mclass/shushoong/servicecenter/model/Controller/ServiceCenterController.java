package kh.mclass.shushoong.servicecenter.model.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kh.mclass.shushoong.servicecenter.model.domain.NoticeDto;
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
	
	@GetMapping("/support/qna/list/{page}")
	public String qnaList(Model model ,@PathVariable("page") String pageNum, String category, String keyword, String questCat) {
		System.out.println("PathVariable pageNum : " + pageNum);
		if(pageNum != null && !pageNum.equals("")) {
			try {
				currentPageNum = Integer.parseInt(pageNum);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		int totalCount = service.selectTotalCount(category, keyword);
		int totalPageCount = (totalCount%pageSize == 0) ? totalCount/pageSize : totalCount/pageSize + 1;
		
		int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
		int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum + pageBlockSize - 1;
		
		
		model.addAttribute("currentPageNum", currentPageNum);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("result", service.selectAllList(pageSize, pageBlockSize, currentPageNum, category, keyword, questCat));
		return "servicecenter/onlineQnAlist";
	}
	
	@GetMapping("/support/notice/search.ajax")
	public String searchQnA(Model model, String pageNum, String category, String keyword, String questCatCategory) {
		if(pageNum != null && !pageNum.equals("")) {
			try {
				currentPageNum = Integer.parseInt(pageNum);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("result", service.selectAllList(pageSize, pageBlockSize, currentPageNum, category, keyword, questCatCategory));
		return "servicecenter/QnAlist";
	}
	
	@GetMapping("/support/qna/view/{faqId}")
	public String viewQnA(Model model, @PathVariable("faqId") String faqId) {
		model.addAttribute("result", service.selectOneQna(faqId));
		return "servicecenter/viewQnA";
	}
	
	@PostMapping("/support/qna/write/answer")
	public String writeQnAanswer(Model model, String faqId, String ansContent) {
		service.updateAnswer(faqId, ansContent);
		model.addAttribute("result", service.selectOneQna(faqId));
		return "servicecenter/viewQnA";
	}
	
	
	// 마이페이지 공지사항
	@GetMapping("/support/notice/list")
	public String customerNotice (Model md) {
		List<NoticeDto> noticeDto =  noticeService.selectNoticeAllList(null);
		md.addAttribute("noticeDto", noticeDto);
		return "servicecenter/notice";
	}
	
	
	
}
