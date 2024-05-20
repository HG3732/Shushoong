package kh.mclass.shushoong.employee.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.domain.EmployeeEntity;
import kh.mclass.shushoong.employee.model.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<EmployeeEntity> selectAll() {
		return employeeRepository.selectAllList();
	}
}
