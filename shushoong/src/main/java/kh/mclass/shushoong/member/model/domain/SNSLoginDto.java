package kh.mclass.shushoong.member.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class SNSLoginDto {
	private String userId;
	private String snsType;
	private String snsName;
	private String snsEmail;
	private String snsConnecteDate;
}
