package kh.mclass.shushoong.servicecenter.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.servicecenter.model.domain.NoticeDto;
import kh.mclass.shushoong.servicecenter.model.domain.NoticeFileDto;
import kh.mclass.shushoong.servicecenter.model.domain.OnlineQnADto;
import kh.mclass.shushoong.servicecenter.model.repository.ServiceCenterRepository;

@Service
public class NoticeService {
	
	@Autowired
	private ServiceCenterRepository repository;
	
	public List<NoticeDto> selectNoticeAllList(int pageSize, int pageBlockSize, int currentPageNum, String userId, String noticeCategory) {
		int offset = (currentPageNum - 1) * pageSize;
		RowBounds rb = new RowBounds(offset, pageSize); 
		return repository.selectNoticeAllList(pageSize,pageBlockSize,currentPageNum,rb,userId,noticeCategory);
	}
	
	public int selectTotalCount() {
		return repository.selectNoticeTotalCount();
	}
	
	public int insertNotice(NoticeDto noticeDto) {
		return repository.insertNotice(noticeDto);
	}
	
	public int insertNoticeFile(NoticeFileDto fileDto) {
		return repository.insertNoticeFile(fileDto);
	}
	
	public NoticeDto selectOneNotice(String noticeId) {
		return repository.selectOneNotice(noticeId);
	}
	
	public int updateNotice(String noticeId) {
		return repository.updateNotice(noticeId);
	}
	
	public int deleteNotice(String noticeId) {
		return repository.deleteNotice(noticeId);
	}
	
}
