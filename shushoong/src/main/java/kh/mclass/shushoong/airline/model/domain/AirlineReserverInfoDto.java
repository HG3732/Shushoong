package kh.mclass.shushoong.airline.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AirlineReserverInfoDto {
	private String reserverCode;
	private String userId;
	private String phoneNum;
	private String email;

}
