package kh.mclass.shushoong.airline.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AirlineReserverInfoDto {
	private String airlineReserveCode;
	private String userId;
	private String reservePhone;
	private String reserveEmail;

}
