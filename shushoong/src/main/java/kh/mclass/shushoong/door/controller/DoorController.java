package kh.mclass.shushoong.door.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoorController {

	@GetMapping("/door")
	public String door() {
		
		return "door";
	}
}
