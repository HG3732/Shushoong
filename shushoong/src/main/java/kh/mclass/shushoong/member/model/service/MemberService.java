package kh.mclass.shushoong.member.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
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
	public int log(Map<String, Object> map) {
		return memberRepository.log(map);
	}

	// 아이디 중복 여부
	public int idCheck(String userId) {
		return memberRepository.idCheck(userId);
	}

	// 회원가입
	public int join(MemberDto memberDto) {
		return memberRepository.join(memberDto);
	}

	// 아이디 찾기
	public String findId(Map<String, Object> map) {
		return memberRepository.findId(map);
	}

	// 비밀번호 재설정
	public String setPwd(Map<String, Object> map) {
		return memberRepository.resetPwd(map);
	}
}
