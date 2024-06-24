package kh.mclass.shushoong.mypage.admin.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.mypage.admin.model.repository.MypageAdminRepository;

@Service
public class MypageAdminService {

	@Autowired
	private MypageAdminRepository mypageRepository;
	
	// 관리자
	
	// 일반, 사업자
	// 회원 정보 
	public List<MemberDto> selectAllList() {
		return mypageRepository.selectAllList();
	}
	
	public List<MemberDto> selectOne(String userId) {
		return mypageRepository.selectOne(userId);
	}
	
	// 비밀번호 재설정
	public String resetPwd(Map<String, Object> map) {
		return mypageRepository.resetPwd(map);
	}
}
