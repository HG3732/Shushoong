package kh.mclass.shushoong.member.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	// 로그인
	public MemberDto login(String userId) {
		return memberRepository.login(userId);
	}

	// 로그인 기록
	public int log(Map<String, Object> map) {
		return memberRepository.log(map);
	}

	// 회원가입
	public Integer join(MemberDto memberDto) {
		return memberRepository.join(memberDto);
	}

	// 아이디 중복 여부
	public Integer Idcheck(String userId) {
		return memberRepository.Idcheck(userId);
	} 
	
	// 비밀번호 재설정
	public int setPwd(Map<String, Object> map) {
		return memberRepository.resetPwd(map);
	}
}
