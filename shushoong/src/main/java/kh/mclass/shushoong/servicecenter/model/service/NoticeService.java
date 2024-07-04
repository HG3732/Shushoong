package kh.mclass.shushoong.servicecenter.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kh.mclass.shushoong.servicecenter.model.domain.NoticeDto;

@Service
public class NoticeService {
	
	private NoticeService service;
	
	public List<NoticeDto>  selectAllList(String noticeId) {
		return service.selectAllList(noticeId);
	}
	
}
