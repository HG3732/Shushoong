package kh.mclass.shushoong.airline.model.domain;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class DirectViaDto {
	private String airlineReserveCode;
	private String seatGrade;
	private String airlineCode;
}
