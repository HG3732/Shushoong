package kh.mclass.shushoong.airline.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kh.mclass.shushoong.airline.model.domain.AirlineInfoDto;
import lombok.extern.log4j.Log4j2;
@Mapper
public interface AirlineRepository {
	// 항공 리스트
	List<AirlineInfoDto> selectAllList(String departLoc, String arrivalLoc);
	// 왕복 오는 항공편
	List<AirlineInfoDto> selectOne(String airlineCode);
	// 사이드 바 시간대 
	List<AirlineInfoDto> selectOptions(
			String departLoc, String arrivalLoc, 
			String departTimeLeft, String departTimeRight,
			String arrivalTimeLeft, String arrivalTimeRight, 
			String selectType, String viaType
			);
	
}
