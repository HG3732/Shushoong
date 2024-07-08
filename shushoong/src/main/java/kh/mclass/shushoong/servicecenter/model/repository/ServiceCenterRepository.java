package kh.mclass.shushoong.servicecenter.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.mclass.shushoong.servicecenter.model.domain.NoticeDto;
import kh.mclass.shushoong.servicecenter.model.domain.NoticeFileDto;
import kh.mclass.shushoong.servicecenter.model.domain.OnlineQnADto;

@Mapper
public interface ServiceCenterRepository {
	//1:1 문의 검색
	public List<OnlineQnADto> selectAllList(int pageSize, int pageBlockSize, int currentPageNum, String id, String category, String keyword, String questCatCategory, RowBounds rowBounds);
	
	//1:1 문의 글 갯수(페이징용)
	public int selectTotalCount(String id, String category, String keyword, String questCatCategory);
	
	//1:1 문의 보기
	public OnlineQnADto selectOneQna(String faqId);
	
	//1:1 문의 답변 작성
	public int updateAnswer(String faqId, String ansContent);
	
	
	
	// 공지사항
	List<NoticeDto> selectNoticeAllList(int pageSize, int pageBlockSize, int currentPageNum, RowBounds rb, String userGrade);
	
	int selectNoticeTotalCount(String userGrade);
	
	// 공지사항 작성
    int insertNotice(NoticeDto noticeDto);
    int insertNoticeFile(NoticeFileDto noticeFileDto);
    NoticeDto selectOneNotice(String noticeId);
    int updateNotice(NoticeDto noticeDto);
    int deleteNotice(String noticeId);
		
	
}
