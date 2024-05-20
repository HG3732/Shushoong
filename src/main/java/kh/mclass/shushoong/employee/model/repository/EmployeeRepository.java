package kh.mclass.shushoong.employee.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.domain.EmployeeEntity;

@Mapper
public interface EmployeeRepository {
	public List<EmployeeEntity> selectAllList();
}
