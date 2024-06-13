package kh.mclass.shushoong.servicecenter.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class OnlineQnADto {
	private String faqId;
	private String userId;
	private String askTitle;
	private String askContent;
	private String askDate;
	private String ansContent;
	private String ansTime;
}
