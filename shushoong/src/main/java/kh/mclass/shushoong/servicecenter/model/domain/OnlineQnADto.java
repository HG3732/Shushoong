package kh.mclass.shushoong.servicecenter.model.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class OnlineQnADto {
	private int faqId;
	private String userId;
	private String questCat;
	private String questCatDesc;
	private String askTitle;
	private String askContent;
	private String askDate;
	private String ansContent;
	private String ansTime;
	private List<String> fileId;
}
