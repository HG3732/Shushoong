package kh.mclass.shushoong.airline.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.airline.model.domain.AirlineInfoDto;
import kh.mclass.shushoong.airline.model.domain.AirlineReserverInfoDto;
import kh.mclass.shushoong.airline.model.domain.DirectViaDto;
import kh.mclass.shushoong.airline.model.repository.AirlineRepository;

@Service
public class AirlineService {
	
	@Autowired
	private AirlineRepository airlineRepository;
	// 항공 목록
	public List<AirlineInfoDto> getAirlineInfo(String departLoc, String arrivalLoc, String departDate, String arrivalDate, String ticketType, String seatGrade){
		System.out.println("Service - departLoc: " + departLoc + ", arrivalLoc: " + arrivalLoc + ", departDate: " + departDate + ", arrivalDate: " + arrivalDate);
		
		return airlineRepository.selectAllList(departLoc, arrivalLoc, departDate, arrivalDate, ticketType, seatGrade);
	}
	// 왕복 오는 항공편
	public AirlineInfoDto getSelectOne(String airlineCode){
		System.out.println("Service - flightNo : " + airlineCode);
		
		return airlineRepository.selectOne(airlineCode);
	}
	// 사이드바 시간대 
	public List<AirlineInfoDto> getAirlineSideTime(
			String departLoc, String arrivalLoc, 
			String departTimeLeft, String departTimeRight, 
			String arrivalTimeLeft, String arrivalTimeRight, 
			String selectType, String viaType, String maxPrice, String ticketType, String seatGrade){
		System.out.println("서비스 사이드바 출발");
		
		return airlineRepository.selectOptions(
				departLoc, arrivalLoc, departTimeLeft, departTimeRight, arrivalTimeLeft, arrivalTimeRight, selectType, viaType, maxPrice, ticketType, seatGrade);
	}
	public List<AirlineInfoDto> getAirlineSideTimeReturn(
			String departLoc, String arrivalLoc, 
			String departTimeLeft, String departTimeRight, 
			String arrivalTimeLeft, String arrivalTimeRight, 
			String selectType, String viaType, String ticketType, String seatGrade){
		System.out.println("서비스 사이드바 출발");
		
		return airlineRepository.selectOptionsReturn(
				departLoc, arrivalLoc, departTimeLeft, departTimeRight, arrivalTimeLeft, arrivalTimeRight, selectType, viaType, ticketType, seatGrade);
	}
	
	public Integer getMaxPrice(String departLoc, String arrivalLoc, String ticketType, String seatGrade){
		
		return airlineRepository.getMaxPrice(departLoc, arrivalLoc, ticketType, seatGrade);
	}
	
//	// 항공 목록 셀렉트 바
//	public List<AirlineInfoDto> getSelectTypeList(
//			String departLoc, String arrivalLoc, String selectType){
//		return airlineRepository.selectType(departLoc, arrivalLoc, selectType);
//		
//	}
	
	public Character selectOneDomesticFunction(String airlineCode) {
		return airlineRepository.selectOneDomesticFunction(airlineCode);
	};
	
	public Character selectOneReturnDomesticFunction(String airlineCode) {
		return airlineRepository.selectOneDomesticFunction(airlineCode);
	};
	
//	예약자 정보 추가
	public int insertReserverInfo(AirlineReserverInfoDto reserverInfo) {
		return airlineRepository.insertReserverInfo(reserverInfo);
	}
	
//	탑승객 정보 추가
	public int insertPassengerInfo(List<Map<String, Object>> reserveInfo) {
		return airlineRepository.insertPassengerInfo(reserveInfo);
	}
	
//	직항, 경유 추가
	public int insertDirectViaDto(DirectViaDto directDto) {
		return airlineRepository.insertDirectViaDto(directDto);
	}
	
	public AirlineInfoDto selectOneAirlineInfo(String airlineCode) {
		return airlineRepository.selectOneAirlineInfo(airlineCode);
	}
	
	public List<AirlineInfoDto> selectListRecommenedCheap(){
		return airlineRepository.selectListRecommenedCheap();
	}
}
