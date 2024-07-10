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
	private String noticeCategory;
	
//	Lombok의 @Data 어노테이션은 기본 생성자를 만들어 줍니다. 하지만 매개변수를 받는 생성자는 만들어 주지않음
//	따라서 직접 매개변수를 받는 생성자를 추가해야 할 경우에는 명시적으로 정의해야함.	
//public NoticeFileDto() {
//	
//	}
//public NoticeFileDto(String fileId, String noticeId, String originalFilename, String savedFilePathName,
//		String noticeCategory) {
//	this.fileId = fileId;
//	this.noticeId = noticeId;
//	this.originalFilename = originalFilename;
//	this.savedFilePathName = savedFilePathName;
//	this.noticeCategory = noticeCategory;
//	}
}