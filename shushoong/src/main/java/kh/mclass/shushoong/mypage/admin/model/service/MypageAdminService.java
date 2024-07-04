package kh.mclass.shushoong.mypage.admin.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.mypage.admin.model.domain.ProductDtoRes;
import kh.mclass.shushoong.mypage.admin.model.repository.MypageAdminRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageAdminService {

	private final MypageAdminRepository mypageAdminRepository;
	
	public List<String> selectAllList(String keyword) {
		return mypageAdminRepository.selectAllList(keyword);
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
	
	public List<String> selectDormantAccount(String keyword) {
		return mypageAdminRepository.selectDormantAccount(keyword);
	}
	
	public int updateAllLock(String keyword) {
		return mypageAdminRepository.updateAllLock(keyword);
	}
	
	public int selectUseTerm(String id) {
		return mypageAdminRepository.selectUseTerm(id);
	}
	
	public List<ProductDtoRes> selectProduct(String keyword) {
		return mypageAdminRepository.selectProduct(keyword);
	}
}
