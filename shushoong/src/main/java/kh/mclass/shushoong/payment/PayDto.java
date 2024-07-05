package kh.mclass.shushoong.payment;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class PayDto {

	private String approveNo;
	private String reserveCorper;
	private String cardNum;
	private String payPrice;
	private String currency;
	private String payStatus;
	private String hotelReserveCode;
	private String airlineReserveCode;
	
}
