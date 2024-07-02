package kh.mclass.shushoong.servicecenter.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kh.mclass.shushoong.servicecenter.model.domain.OnlineQnADto;
import kh.mclass.shushoong.servicecenter.model.repository.ServiceCenterRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OnlineQnAService {
	
	private final ServiceCenterRepository serviceCenterRepository;
	
	public List<OnlineQnADto> selectAllList(String category, String keyword, String questCatCategory) {
		return serviceCenterRepository.selectAllList(category, keyword, questCatCategory);
	};
	
	public OnlineQnADto selectOneQna(String faqId) {
		return serviceCenterRepository.selectOneQna(faqId);
	}
	
	public int updateAnswer(String faqId, String ansContent) {
		return serviceCenterRepository.updateAnswer(faqId, ansContent);
	}
}
