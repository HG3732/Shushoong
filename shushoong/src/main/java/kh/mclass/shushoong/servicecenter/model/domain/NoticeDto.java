package kh.mclass.shushoong.servicecenter.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class NoticeDto {
	private String noticeId;
	private String noticeTitle;
	private String noticeContent;
	private String noticeTime;
	private String userId;
}
