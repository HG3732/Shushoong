package kh.mclass.shushoong.servicecenter.model.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class OnlineQnAFileDtio {
	private String fileId;
	private String faqId;
	private String originalFilename;
	private String savedFilePathName;
}
