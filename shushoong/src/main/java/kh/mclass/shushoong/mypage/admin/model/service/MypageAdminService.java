package kh.mclass.shushoong.mypage.admin.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.mypage.admin.model.repository.MypageAdminRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageAdminService {

	private final MypageAdminRepository mypageAdminRepository;
	
	public List<String> selectAllList(String keyword, int pageSize, int pageBlockSize, int currentPageNum) {
		int totalCount = mypageAdminRepository.selectTotalCount(keyword);
		
		int totalPageCount = (totalCount%pageSize == 0) ? totalCount/pageSize : totalCount/pageSize + 1;
		
		int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
		int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum + pageBlockSize - 1;
		
		int offset = (currentPageNum - 1) * pageSize;
		RowBounds rowBounds = new RowBounds(offset, pageSize);
		
		return mypageAdminRepository.selectAllList(keyword, rowBounds);
	}
	
	public MemberDto selectOne(String id) {
		return mypageAdminRepository.selectOne(id);
	}
	
	public List<Map<String, String>> selectHotelPayCount(String id) {
		return mypageAdminRepository.selectHotelPayCount(id);
	}
	
	public List<Map<String, String>> selectFlyPayCount(String id) {
		return mypageAdminRepository.selectFlyPayCount(id);
	}
	
	public String selectFAQCount(String id) {
		return mypageAdminRepository.selectFAQCount(id);
	}
	
	public int updateLockAccount(String id) {
		return mypageAdminRepository.updateLockAccount(id);
	}
	public int updateUnlockAccount(String id) {
		return mypageAdminRepository.updateUnlockAccount(id);
	}
	
	public List<Map<String, String>> selectLatestFaq() {
		return mypageAdminRepository.selectLatestFaq();
	};
}
