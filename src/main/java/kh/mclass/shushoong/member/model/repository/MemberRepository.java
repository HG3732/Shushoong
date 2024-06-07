package kh.mclass.shushoong.member.model.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.member.model.domain.MemberDto;


@Mapper
public interface MemberRepository {
	
	// 로그인 
	public MemberDto login(String userId);
	
	// 로그인 기록
	public int log(Map<String, Object> map);
	
	// 회원가입
	public Integer join(MemberDto memberDto);
	
	// 아이디 중복 확인
	public Integer Idcheck(String userId);
	
	// 비밀번호 재설정 
	public int resetPwd(Map<String, Object> map);
	
}
