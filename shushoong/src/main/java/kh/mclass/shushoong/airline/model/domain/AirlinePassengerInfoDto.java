package kh.mclass.shushoong.airline.model.domain;

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
	
	public AirlinePassengerInfoDto(String lastName, String firstName, String birth, String nation) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.birth = birth;
		this.nation = nation;
	}
	
	
	
}
