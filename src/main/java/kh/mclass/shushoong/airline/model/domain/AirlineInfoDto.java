package kh.mclass.shushoong.airline.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;

@Data
@Component
public class AirlineInfoDto {
	private String airlineCode; // 운항 코드
	private String flightNo; // 항공편명
	private String departLoc;  // 출발 지역
	private String arrivalLoc; // 도착 지역
	private String departTime; // 출발 시간
	private String arrivalTime; // 도착 시간
	private String departDate; // 출발 일자
	private String arrivalDate; // 도착 일자
	private String airlineName; // 항공사 명
	private String airlineImg; // 항공사 로고
	private String domesticFlights; // 국내선/국제선
	private Integer viaCount; // 경로 횟수
	private String spareSeat; // 잔여 좌석
	private String ticketPrice; // 티켓 값
	private String flightTime; // 비행 시간
}
