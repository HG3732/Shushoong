package kh.mclass.shushoong.member.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.member.model.domain.MemberRole;
import kh.mclass.shushoong.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberSecurityService implements UserDetailsService {
	
	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		log.debug("membersecurityservice ========	======");
		
		
		Optional<MemberDto> loginDtoOp = Optional.ofNullable(memberRepository.login(userId));
		if(loginDtoOp.isEmpty()) {
			throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
		}
		
		MemberDto loginDto = loginDtoOp.get();
		List<GrantedAuthority> authorities = new ArrayList<>();
		switch(loginDto.getUserGrade()) {
			case "admin" : authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue())); break;
			case "customer": authorities.add(new SimpleGrantedAuthority(MemberRole.CUSTOMER.getValue())); break;
			case "business":authorities.add(new SimpleGrantedAuthority(MemberRole.BUSINESS.getValue())); break;
		}
		return new User(loginDto.getUserId(), loginDto.getUserPwd(), authorities);
	}
}

