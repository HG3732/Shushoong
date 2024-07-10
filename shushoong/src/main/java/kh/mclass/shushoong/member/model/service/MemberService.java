package kh.mclass.shushoong.member.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	public List<MemberDto> selectAllList() {
		return memberRepository.selectAllList();
	}

	public List<MemberDto> selectOne(String userId) {
		return memberRepository.selectOne(userId);
	}

	// 로그인
	public MemberDto login(String userId) {
		return memberRepository.login(userId);
	}

	// 로그인 기록
	public int loginLog(Map<String, Object> map) {
		return memberRepository.loginLog(map);
	}

	// 계정 상태 체크
	public String lockedCheck(String userId) {
		return memberRepository.lockedCheck(userId);
	}
	
	
	public boolean checkLoginIdDuplicate(String loginId) {
		return memberRepository.existsByLoginId(loginId);
	}

	// 아이디 중복 여부
	public int idCheck(String userId) {
		return memberRepository.idCheck(userId);
	}

	// 회원가입
	public Integer join(MemberDto memberDto) {
		return memberRepository.join(memberDto);
	}

	// 아이디 찾기
	public String findId(String userName, String userEmail, String userGrade) {
		return memberRepository.findId(userName, userEmail, userGrade);
	}

	// 비밀번호 찾기
	public int findPwd(String userId, String userEmail, String userGrade) {
		return memberRepository.findPwd(userId, userEmail, userGrade);
	}

	// 계정 확인
	public String accountCheck(String userId, String Email, String userGrade) {
		return memberRepository.accountCheck(userId, Email, userGrade);
	}

	// 비밀번호 재설정
	public int resetPwd(Map<String, Object> paraMap) {
		return memberRepository.resetPwd(paraMap);
	}
}
