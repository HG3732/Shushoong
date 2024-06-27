package kh.mclass.shushoong.mypage.customer.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.mypage.customer.model.repository.MypageCustomerRepository;

@Service
public class MypageCustomerService {

	@Autowired
	private MypageCustomerRepository mypageRepository;

	private BCryptPasswordEncoder bcrypt;

	public MemberDto selectOne(String userId) {
		return mypageRepository.selectOne(userId);
	}

	// 비밀번호 체크
	public boolean pwdCheck(String userId, String userPwd) {
		MemberDto member;
		String realPassword = member.getUserPwd();
		boolean matches = encoder.matches(checkPassword, realPassword);
		return realPassword;
	}

	// 비밀번호 재설정
	public String resetPwd(MemberDto dto) {
		dto.setUserPwd(bcrypt.encode(dto.getUserPwd()));
		return mypageRepository.resetPwd(dto);
	}
}
