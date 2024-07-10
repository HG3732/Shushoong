package kh.mclass.shushoong.airline.model.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AirlinePassengerInfoDto {
	private String reserveCode;//예약코드 (PK info 정보와 연결 경로)
	private String lastName;
	private String firstName;
	private String birth;
	private char gender;
	private String nation;
	private int baggage;
	private String passportCode;
	private String passportDate; //여권만료일
}
