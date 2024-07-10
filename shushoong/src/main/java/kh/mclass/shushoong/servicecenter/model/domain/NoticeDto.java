package kh.mclass.shushoong.servicecenter.model.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class NoticeDto {
	private int noticeId;
	private String noticeTitle;
	private String noticeContent;
	private String noticeTime;
	private String userId;
	private String noticeCategory;
	private String userGrade;
	List<NoticeFileDto> fileId;
}
