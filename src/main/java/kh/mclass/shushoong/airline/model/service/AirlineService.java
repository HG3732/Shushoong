package kh.mclass.shushoong.airline.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.airline.model.domain.AirlineInfoDto;
import kh.mclass.shushoong.airline.model.repository.AirlineRepository;

@Service
public class AirlineService {
	
	@Autowired
	private AirlineRepository airlineRepository;
	
	// 항공 목록
	public List<AirlineInfoDto> getAirlineInfo(String departLoc, String arrivalLoc){
		System.out.println("Service - departLoc: " + departLoc + ", arrivalLoc: " + arrivalLoc);
		
		return airlineRepository.selectAllList(departLoc, arrivalLoc);
	}
	
	// 왕복 오는 항공편
	public List<AirlineInfoDto> getSelectOne(String airlineCode){
		System.out.println("Service - flightNo : " + airlineCode);
		
		return airlineRepository.selectOne(airlineCode);
	}
	
	// 사이드바 시간대 
	public List<AirlineInfoDto> getAirlineSideTime(
			String departLoc, String arrivalLoc, 
			String departTimeLeft, String departTimeRight, String arrivalTimeLeft, String arrivalTimeRight){
		System.out.println("서비스 사이드바 출발");
		
		return airlineRepository.selectSideTime(departLoc, arrivalLoc, departTimeLeft, departTimeRight, arrivalTimeLeft, arrivalTimeRight);
	}
}
