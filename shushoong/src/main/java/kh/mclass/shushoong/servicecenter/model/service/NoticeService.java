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
	
	public List<NoticeDto> selectNoticeAllList(int pageSize, int pageBlockSize, int currentPageNum, String userGrade) {
		int offset = (currentPageNum - 1) * pageSize;
		RowBounds rb = new RowBounds(offset, pageSize); 
		return repository.selectNoticeAllList(pageSize,pageBlockSize,currentPageNum,rb,userGrade);
	}
	public List<NoticeDto> selectNoticeAllListAjax(int pageSize, int pageBlockSize, int currentPageNum, String userGrade, String noticeCategory) {
		int offset = (currentPageNum - 1) * pageSize;
		RowBounds rb = new RowBounds(offset, pageSize); 
		return repository.selectNoticeAllListAjax(pageSize,pageBlockSize,currentPageNum,rb,userGrade, noticeCategory);
	}
	
	public int selectTotalCount(String userGrade, String noticeCategory) {
		return repository.selectNoticeTotalCount(userGrade, noticeCategory);
	}
	
	public int insertNotice(NoticeDto noticeDto) {
		repository.insertNotice(noticeDto);
		noticeDto.getNoticeId();
		return noticeDto.getNoticeId();
	}
	
	public int insertNoticeFile(NoticeFileDto noticeFiles) {
		return repository.insertNoticeFile(noticeFiles);
	}

	public NoticeDto selectOneNotice(String noticeId) {
		return repository.selectOneNotice(noticeId);
	}
	
	public List<NoticeFileDto> selectOneNoticeFile(String noticeId) {
		return repository.selectOneNoticeFile(noticeId);
	}
	
	public int updateNotice(NoticeDto noticeDto) {
		return repository.updateNotice(noticeDto);
	}
	public int updateNoticeFile(NoticeFileDto noticeFiles) {
		return repository.updateNoticeFile(noticeFiles);
	}
	
	public int deleteNotice(String noticeId) {
		return repository.deleteNotice(noticeId);
	}
	public int deleteNoticeFile(String noticeId) {
		return repository.deleteNoticeFile(noticeId);
	}
	
}
