package kh.mclass.shushoong.servicecenter.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kh.mclass.shushoong.servicecenter.model.domain.NoticeDto;
import kh.mclass.shushoong.servicecenter.model.repository.ServiceCenterRepository;

@Service
public class NoticeService {
	
	private ServiceCenterRepository repository;
	
	public List<NoticeDto>  selectAllList(String noticeId) {
		return repository.selectNoticeAllList(noticeId);
	}
	
}
