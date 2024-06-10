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
	public int join(MemberDto memberDto);
	
	// 아이디 찾기
	public String findId(Map<String, Object> map);
	
	// 아이디 중복 확인
	public int idCheck(String userId);
	
	// ---------- 마이페이지 ----------
	
	// 비밀번호 재설정 
	public String resetPwd(Map<String, Object> map);

}
