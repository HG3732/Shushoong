package kh.mclass.shushoong.member.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BusinessInfoDto {
	private String userId;
	private Integer businessNum;
	private String hotelName;
	private String savedFilePath;
}
