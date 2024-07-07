package kh.mclass.shushoong.mypage.admin.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.mypage.admin.model.domain.ProductDtoRes;
import kh.mclass.shushoong.mypage.admin.model.repository.MypageAdminRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageAdminService {

	private final MypageAdminRepository mypageAdminRepository;
	
	public List<Map<String, Object>> selectLatestNotice() {
		return mypageAdminRepository.selectLatestNotice();
	}
	
	public List<Map<String, String>> selectLatestFaq() {
		return mypageAdminRepository.selectLatestFaq();
	};
	
	public List<String> selectAllList(String keyword, int memCat) {
		System.out.println("service : "+memCat);
		return mypageAdminRepository.selectAllList(keyword, memCat);
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
	
	public List<Map<String, String>> selectOneLatestFaq(String id) {
		return mypageAdminRepository.selectOneLatestFaq(id);
	};
	
	
	public int selectProductCount(String id) {
		return mypageAdminRepository.selectProductCount(id);
	}
	
	public List<String> selectNoSaleAccount(String keyword) {
		return mypageAdminRepository.selectNoSaleAccount(keyword);
	}
	
	public List<String> selectDormantAccount(String keyword, int memCat) {
		return mypageAdminRepository.selectDormantAccount(keyword, memCat);
	}
	
	public int updateAllLock(String keyword) {
		return mypageAdminRepository.updateAllLock(keyword);
	}
	
	public int selectUseTerm(String id) {
		return mypageAdminRepository.selectUseTerm(id);
	}
	
	public int selectAllProductCount(String category, String keyword) {
		return mypageAdminRepository.selectAllProductCount(category, keyword);
	}
	
	public List<ProductDtoRes> selectProduct(int pageSize, int pageBlockSize, int currentPageNum, String category, String keyword) {
		
		int offset = (currentPageNum - 1) * pageSize;
		
		RowBounds rowBounds = new RowBounds(offset, pageSize);
		
		return mypageAdminRepository.selectProduct(pageSize, pageBlockSize, currentPageNum, category, keyword, rowBounds);	}
	}
