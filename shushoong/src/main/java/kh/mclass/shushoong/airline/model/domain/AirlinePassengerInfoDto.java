package kh.mclass.shushoong.airline.model.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;


@Component
public class AirlinePassengerInfoDto {
	private String reserveCode;//예약코드 (PK info 정보와 연결 경로)
	
	private String lastName;
	private String firstName;
	private String birth;
	private char gender;
	private String nation;
	private int baggage;
	
	public String getReserveCode() {
		return reserveCode;
	}
	public void setReserveCode(String reserveCode) {
		this.reserveCode = reserveCode;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public int getBaggage() {
		return baggage;
	}
	public void setBaggage(int baggage) {
		this.baggage = baggage;
	}
	
	
	
}
