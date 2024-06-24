package kh.mclass.shushoong.mypage.customer.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.member.model.domain.MemberDto;

@Mapper
public interface MypageCustomerRepository {
	
	// 관리자 전용
	// 회원, 사업자 전용
	// 회원 정보
	List<MemberDto> selectAllList();
	List<MemberDto> selectOne(String userId);
	
	// 비밀번호 재설정 
	public String resetPwd(Map<String, Object> map);

}
