package kh.mclass.shushoong.hotel.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.hotel.model.domain.HotelPic;
import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelRoomDto;
import kh.mclass.shushoong.hotel.model.domain.HotelSearchDtoRes;
import kh.mclass.shushoong.hotel.model.repository.HotelRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {

	private final HotelRepository hotelRepository;
	
	public List<HotelDtoRes> selectAllHotelList(String loc) {
		return hotelRepository.selectAllHotelList(loc);
	}
	
	public List<HotelPic> selectPicList(String hotelCode) {
		return hotelRepository.selectHotelPicList(hotelCode);
	}
	
	public List<HotelRoomDto> selectRoomList(String hotelCode) {
		return hotelRepository.selectRoomList(hotelCode);
	}
	
	public List<HotelSearchDtoRes> selectHotelSearchList(String hotelCode) {
		return hotelRepository.selectHotelSearchList(hotelCode);
	}


}
