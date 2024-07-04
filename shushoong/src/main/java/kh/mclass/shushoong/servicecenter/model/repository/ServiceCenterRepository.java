package kh.mclass.shushoong.servicecenter.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.servicecenter.model.domain.NoticeDto;
import kh.mclass.shushoong.servicecenter.model.domain.OnlineQnADto;

@Mapper
public interface ServiceCenterRepository {
	//1:1 문의 검색
	public List<OnlineQnADto> selectAllList(String category, String keyword, String questCatCategory);
	
	//1:1 문의 보기
	public OnlineQnADto selectOneQna(String faqId);
	
	//1:1 문의 답변 작성
	public int updateAnswer(String faqId, String ansContent);
	
	
	
	// 공지사항
	List<NoticeDto> selectNoticeAllList(String noticeId);
	
}
