package kh.mclass.shushoong.servicecenter.model.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kh.mclass.shushoong.servicecenter.model.service.OnlineQnAService;
import lombok.RequiredArgsConstructor;

@Controller
public class ServiceCenterController {

	@Autowired
	private OnlineQnAService service;
	
	@GetMapping("/support/qna/list")
	public String qnaList(Model model, String category, String keyword, String questCat) {
		model.addAttribute("result", service.selectAllList(category, keyword, questCat));
		return "servicecenter/onlineQnAlist";
	}
	
	@GetMapping("/support/notice/search.ajax")
	public String searchQnA(Model model, String category, String keyword, String questCatCategory) {
		model.addAttribute("result", service.selectAllList(category, keyword, questCatCategory));
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
	@GetMapping("/servicecenter/notice")
	public String customerNotice () {
		return "servicecenter/notice";
	}
	
	
	
}
