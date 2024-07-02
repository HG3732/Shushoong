package kh.mclass.shushoong.member.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class SNSLoginDto {
	private String userId;
	private String SNSType;
	private String SNSName;
	private String SNSEmail;
	private String SNSConnecteDate;
}
