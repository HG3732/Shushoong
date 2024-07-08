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
	public int loginLog(Map<String, Object> map);

	// 로그인 ID를 갖는 객체가 존재하는지 => 존재하면 true 리턴 (ID 중복 검사 시 필요)
	boolean existsByLoginId(String loginId);

	// 로그인 ID를 갖는 객체 반환
	MemberDto findByLoginId(String loginId);

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
	public int resetPwd(Map<String, Object> paraMap);

}
