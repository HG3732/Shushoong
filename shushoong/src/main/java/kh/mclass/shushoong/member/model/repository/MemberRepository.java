package kh.mclass.shushoong.member.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.member.model.domain.MemberDto;

@Mapper
public interface MemberRepository {

	List<MemberDto> selectAllList();

	List<MemberDto> selectOne(String userId);

	// 로그인
	public MemberDto login(String userId);

	// 로그인 기록
	public int log(Map<String, Object> map);

	// 회원가입
	public Integer join(MemberDto memberDto);

	// 아이디 찾기
	public String findId(String userName, String userEmail, String userGrade);

	// 비밀번호 찾기
	public int findPwd(String userId, String userEmail, String userGrade);
		
	// 계정 체크 
	public String accountCheck(String userName, String userEmail, String userGrade);

	// 아이디 중복 확인
	public int idCheck(String userId);

	// 비밀번호 재설정
	public String resetPwd(Map<String, Object> map);

}
