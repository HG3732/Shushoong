package kh.mclass.shushoong.airline.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kh.mclass.shushoong.airline.model.domain.AirlineInfoDto;
@Mapper
public interface AirlineRepository {
	List<AirlineInfoDto> selectAllList(@Param("departLoc") String departLoc, @Param("arrivalLoc") String arrivalLoc);
}
