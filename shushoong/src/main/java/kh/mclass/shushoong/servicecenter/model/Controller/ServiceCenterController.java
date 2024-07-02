package kh.mclass.shushoong.servicecenter.model.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kh.mclass.shushoong.servicecenter.model.service.OnlineQnAService;
import lombok.RequiredArgsConstructor;

@Controller
public class ServiceCenterController {

	@Autowired
	private OnlineQnAService service;
	
	@GetMapping("/support/notice/list")
	public String qnaList(Model model, String category, String keyword, String questCat) {
		model.addAttribute("result", service.selectAllList(category, keyword, questCat));
		return "servicecenter/onlineQnAlist";
	}
	
	@GetMapping("/support/notice/search.ajax")
	public String searchQna(Model model, String category, String keyword, String questCatCategory) {
		model.addAttribute("result", service.selectAllList(category, keyword, questCatCategory));
		return "servicecenter/QnAlist";
	}
}
