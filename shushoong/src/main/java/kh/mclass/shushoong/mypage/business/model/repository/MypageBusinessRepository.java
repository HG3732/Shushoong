package kh.mclass.shushoong.mypage.business.model.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.member.model.domain.MemberDto;

@Mapper
public interface MypageBusinessRepository {

	public MemberDto selectOne(String userId);

	// 비밀번호 체크
	public String pwdChecking(String userId);

	// 비밀번호 재설정
	public int resetPwd(Map<String, Object> paraMap);

}
