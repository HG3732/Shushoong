package kh.mclass.shushoong.mypage.customer.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.member.model.domain.MemberDto;

@Mapper
public interface MypageCustomerRepository {

	// 로그인 정보 불러오기 
	public MemberDto selectOne(String userId);

	// 비밀번호 재설정
	public String resetPwd(MemberDto dto);

}
