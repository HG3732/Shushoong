package kh.mclass.shushoong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class MapController {
	
	@GetMapping("/map")
	public String map() {
		
		return "map";
	}
	
	@GetMapping("/layout")
	public String layout() {
		return "layout";
	}

}
