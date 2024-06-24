package kh.mclass.shushoong.mypage.business.model.repository;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.member.model.domain.MemberDto;


@Mapper
public interface MypageBusinessRepository {
		
	// 비밀번호 재설정 
	public String resetPwd(MemberDto dto);

}
