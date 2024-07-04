package kh.mclass.shushoong.servicecenter.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.servicecenter.model.domain.NoticeDto;
import kh.mclass.shushoong.servicecenter.model.repository.ServiceCenterRepository;

@Service
public class NoticeService {
	
	@Autowired
	private ServiceCenterRepository repository;
	
	public List<NoticeDto> selectNoticeAllList(String noticeId) {
		return repository.selectNoticeAllList();
	}
	
}
