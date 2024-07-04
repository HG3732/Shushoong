package kh.mclass.shushoong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AccessDeniedController {
	// 접근 제한 페이지로 이동
	@GetMapping("/error/redirect")
	public String acRefuse() {
		return "common/ACPage";
	}
}
