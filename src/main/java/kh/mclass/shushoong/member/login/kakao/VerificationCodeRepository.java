package kh.mclass.shushoong.member.login.kakao;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import kh.mclass.shushoong.member.email.dto.VerificationCode;

@Repository
public class VerificationCodeRepository {
	private final Map<String, VerificationCode> repository = new ConcurrentHashMap<>();
	
	public VerificationCode save(VerificationCode verificationCode) {
		return repository.put(verificationCode.getCode(), verificationCode);
	}
	
	public Optional<VerificationCode> findByCode(String code) {
		return Optional.ofNullable(repository.get(code));
	}
	
	public void remove(VerificationCode verificationCode) {
		repository.remove(verificationCode.getCode());
	}
}
