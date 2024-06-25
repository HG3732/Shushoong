package kh.mclass.shushoong.airline.model.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.airline.model.domain.AirlineInfoDto;
import kh.mclass.shushoong.airline.model.repository.AirlineRepository;

@Service
public class AirlineService {
	
	@Autowired
	private AirlineRepository airlineRepository;
	// 항공 목록
	public List<AirlineInfoDto> getAirlineInfo(String departLoc, String arrivalLoc, String departDate, String arrivalDate){
		System.out.println("Service - departLoc: " + departLoc + ", arrivalLoc: " + arrivalLoc + ", departDate: " + departDate + ", arrivalDate: " + arrivalDate);
		
		return airlineRepository.selectAllList(departLoc, arrivalLoc, departDate, arrivalDate);
	}
	// 왕복 오는 항공편
	public List<AirlineInfoDto> getSelectOne(String airlineCode){
		System.out.println("Service - flightNo : " + airlineCode);
		
		return airlineRepository.selectOne(airlineCode);
	}
	// 사이드바 시간대 
	public List<AirlineInfoDto> getAirlineSideTime(
			String departLoc, String arrivalLoc, 
			String departTimeLeft, String departTimeRight, 
			String arrivalTimeLeft, String arrivalTimeRight, 
			String selectType, String viaType, String maxPrice){
		System.out.println("서비스 사이드바 출발");
		
		return airlineRepository.selectOptions(
				departLoc, arrivalLoc, departTimeLeft, departTimeRight, arrivalTimeLeft, arrivalTimeRight, selectType, viaType, maxPrice);
	}
	
	public Integer getMaxPrice(String departLoc, String arrivalLoc){
		
		return airlineRepository.getMaxPrice(departLoc, arrivalLoc);
	}
	
//	// 항공 목록 셀렉트 바
//	public List<AirlineInfoDto> getSelectTypeList(
//			String departLoc, String arrivalLoc, String selectType){
//		return airlineRepository.selectType(departLoc, arrivalLoc, selectType);
//		
//	}
	
	public void insertPassengerInfo(String phoneNum,String email) {
		
		airlineRepository.insertPassengerInfo(phoneNum, email);
		
		
	}
}
