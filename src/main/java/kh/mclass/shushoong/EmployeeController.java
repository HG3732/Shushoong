package kh.mclass.shushoong;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import kh.mclass.shushoong.domain.EmployeeEntity;
import kh.mclass.shushoong.employee.model.service.EmployeeService;

//@RestController
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping("")
	@ResponseBody
	public List<EmployeeEntity> list() {
		List<EmployeeEntity> employeelist = service.selectAll();
		return employeelist;
	}
	
	@GetMapping("/list")
	public String list2(Model model) {
		List<EmployeeEntity> employeelist = service.selectAll();
		System.out.println(employeelist);
		model.addAttribute("dtolist", employeelist);
		return "employee/list";
	}
}
