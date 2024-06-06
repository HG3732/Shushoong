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
	
	public List<AirlineInfoDto> getAirlineInfo(String departLoc, String arrivalLoc){
		System.out.println("Service - departLoc: " + departLoc + ", arrivalLoc: " + arrivalLoc);
		
//		List<AirlineInfoDto> airlineData = airlineRepository.selectAllList(departLoc, arrivalLoc);
//		System.out.println("Fetched airline data: " + airlineData);
		
		return airlineRepository.selectAllList(departLoc, arrivalLoc);
	}
}
