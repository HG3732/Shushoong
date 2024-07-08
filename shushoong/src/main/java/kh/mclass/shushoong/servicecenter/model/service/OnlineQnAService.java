package kh.mclass.shushoong.servicecenter.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.servicecenter.model.domain.OnlineQnADto;
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
	
	public int selectTotalCount(String id, String category, String keyword) {
		return serviceCenterRepository.selectTotalCount(id, category, keyword);
	}
	
	public OnlineQnADto selectOneQna(String faqId) {
		return serviceCenterRepository.selectOneQna(faqId);
	}
	
	public int updateAnswer(String faqId, String ansContent) {
		return serviceCenterRepository.updateAnswer(faqId, ansContent);
	}
}
