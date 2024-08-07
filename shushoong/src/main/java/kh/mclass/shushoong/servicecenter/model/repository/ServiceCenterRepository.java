package kh.mclass.shushoong.servicecenter.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.mclass.shushoong.servicecenter.model.domain.NoticeDto;
import kh.mclass.shushoong.servicecenter.model.domain.NoticeFileDto;
import kh.mclass.shushoong.servicecenter.model.domain.OnlineQnADto;
import kh.mclass.shushoong.servicecenter.model.domain.OnlineQnAFileDto;

@Mapper
public interface ServiceCenterRepository {
	//1:1 문의 검색
	public List<OnlineQnADto> selectAllList(int pageSize, int pageBlockSize, int currentPageNum, String id, String category, String keyword, String questCatCategory, RowBounds rowBounds);
	
	//1:1 문의 글 갯수(페이징용)
	public int selectTotalCount(String id, String category, String keyword, String questCatCategory);
	
	//1:1 문의 보기
	public OnlineQnADto selectOneQna(String faqId);
	List<OnlineQnAFileDto> selectOneQnaFile(String faqId);
	
	//1:1 문의 답변 작성
	public int updateAnswer(String faqId, String ansContent);
	// 문의 작성
	int insertQna(OnlineQnADto onlineQnaDto);
	int insertQnaCat(OnlineQnADto onlineQnaDto);
	int insertQnaFile(OnlineQnAFileDto qnaFiles);
	// 문의 삭제
	int deleteQna(String faqId);
	int deleteQnaFile(String faqId);
	int deleteQnaCat(String faqId);
	
	// 공지사항
	List<NoticeDto> selectNoticeAllList(int pageSize, int pageBlockSize, int currentPageNum, RowBounds rb, String userGrade);
	List<NoticeDto> selectNoticeAllListAjax(int pageSize, int pageBlockSize, int currentPageNum, RowBounds rb, String userGrade, String noticeCategory);
	
	int selectNoticeTotalCount(String userGrade, String noticeCategory);
	
	// 공지사항 작성
    int insertNotice(NoticeDto noticeDto);
    int insertNoticeFile(NoticeFileDto noticeFiles);
    // 공지 view
    NoticeDto selectOneNotice(String noticeId);
    List<NoticeFileDto> selectOneNoticeFile(String noticeId);
    // 공지 update
    int updateNotice(NoticeDto noticeDto);
//    int updateNoticeFile(NoticeFileDto noticeFiles);
    // 공지 삭제
    int deleteNotice(String noticeId);
    int deleteNoticeFile(String noticeId);
	
}
