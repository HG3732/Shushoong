package kh.mclass.shushoong.servicecenter.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.servicecenter.model.domain.NoticeFileDto;
import kh.mclass.shushoong.servicecenter.model.domain.OnlineQnADto;
import kh.mclass.shushoong.servicecenter.model.domain.OnlineQnAFileDto;
import kh.mclass.shushoong.servicecenter.model.repository.ServiceCenterRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OnlineQnAService {
	
	private final ServiceCenterRepository serviceCenterRepository;
	
	public List<OnlineQnADto> selectAllList(int pageSize, int pageBlockSize, int currentPageNum, String id, String category, String keyword, String questCatCategory) {

		int offset = (currentPageNum - 1) * pageSize;
		
		RowBounds rowBounds = new RowBounds(offset, pageSize);
		
		return serviceCenterRepository.selectAllList(pageSize, pageBlockSize, currentPageNum, id, category, keyword, questCatCategory, rowBounds);
	};
	
	public int selectTotalCount(String id, String category, String keyword, String questCatCategory) {
		return serviceCenterRepository.selectTotalCount(id, category, keyword, questCatCategory);
	}
	
	public OnlineQnADto selectOneQna(String faqId) {
		return serviceCenterRepository.selectOneQna(faqId);
	}
	
	public List<OnlineQnAFileDto> selectOneQnaFile(String faqId) {
		return serviceCenterRepository.selectOneQnaFile(faqId);
	}
	
	public int updateAnswer(String faqId, String ansContent) {
		return serviceCenterRepository.updateAnswer(faqId, ansContent);
	}
	
	public int insertQna(OnlineQnADto onlineQnaDto) {
		serviceCenterRepository.insertQna(onlineQnaDto);
		onlineQnaDto.getFaqId();
		return onlineQnaDto.getFaqId();
	}
	public int insertQnaCat(OnlineQnADto onlineQnaDto) {
		return serviceCenterRepository.insertQnaCat(onlineQnaDto);
	}
	public int insertQnaFile(OnlineQnAFileDto fileDto) {
		return serviceCenterRepository.insertQnaFile(fileDto);
	}
}
