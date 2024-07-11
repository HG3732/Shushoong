package kh.mclass.shushoong.airline.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AirlineReserveCompleteDtoRes {

	private String airlineReserveCode;//예약코드 (PK info 정보와 연결 경로)
	private String seatGrade;//좌석 등급 1=일등급 2=비즈니스 3=이코노미
	private String departLoc;  // 출발 지역
	private String departTime; // 출발 시간
	private String arrivalLoc; // 도착 지역
	private String arrivalTime; // 도착 시간
	private String departDate; // 출발 일자
	private String arrivalDate; // 도착 일자
	private String airlineName; // 항공사 명
	private String ticketPrice; // 티켓 값
}
