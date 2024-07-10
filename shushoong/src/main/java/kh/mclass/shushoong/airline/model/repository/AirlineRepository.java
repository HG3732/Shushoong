package kh.mclass.shushoong.airline.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kh.mclass.shushoong.airline.model.domain.AirlineInfoDto;
import kh.mclass.shushoong.airline.model.domain.AirlinePassengerInfoDto;
import kh.mclass.shushoong.airline.model.domain.AirlineReserverInfoDto;
import lombok.extern.log4j.Log4j2;
@Mapper
public interface AirlineRepository {
	// 항공 리스트
	List<AirlineInfoDto> selectAllList(String departLoc, String arrivalLoc, String departDate, String arrivalDate, String ticketType, String seatGrade);
	// 왕복 오는 항공편
	AirlineInfoDto selectOne(String airlineCode);
	// 사이드 바 시간대 
	List<AirlineInfoDto> selectOptions(
			String departLoc, String arrivalLoc, 
			String departTimeLeft, String departTimeRight,
			String arrivalTimeLeft, String arrivalTimeRight, 
			String selectType, String viaType, String maxPrice, String ticketType, String seatGrade
			);
	List<AirlineInfoDto> selectOptionsReturn(
			String departLoc, String arrivalLoc, 
			String departTimeLeft, String departTimeRight,
			String arrivalTimeLeft, String arrivalTimeRight, 
			String selectType, String viaType, String ticketType, String seatGrade
			);
	Integer getMaxPrice(String departLoc, String arrivalLoc, String ticketType);
	
	int insertReserverInfo(AirlineReserverInfoDto reserverInfo);
	
	/* String selectResCode(AirlineReserverInfoDto reserverInfo); */
	
	int insertPassengerInfo(List<Map<String, Object>> passengerList);
	
	Character selectOneDomesticFunction(String airlineCode);
	
	Character selectOneReturnDomesticFunction(String airlineCode);
	
	public AirlineInfoDto selectOneAirlineInfo(String airlineCode);
	
	List<AirlineInfoDto> selectListRecommenedCheap();
}
