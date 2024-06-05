package kh.mclass.shushoong.servicecenter.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class NoticeFileDto {
	private String fileId;
	private String noticeId;
	private String originalFilename;
	private String savedFilePathName;
}
