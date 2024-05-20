package kh.mclass.shushoong.domain;

import lombok.Data;

@Data
public class EmployeeEntity {
	private String empId;
	private String empName;
	private String empNo;
	private String deptCode;
	private String jobCode;
	private Integer salary;
}
