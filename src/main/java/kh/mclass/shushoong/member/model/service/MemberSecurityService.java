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
import kh.mclass.shushoong.member.model.domain.MemberEntity;
import kh.mclass.shushoong.member.model.domain.MemberRole;
import kh.mclass.shushoong.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberSecurityService implements UserDetailsService {
	
	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println("Member Service 체크.");
		Optional<MemberEntity> loginDtoOp = Optional.ofNullable(memberRepository.login(userId));
		System.out.println(loginDtoOp);
		if(loginDtoOp.isEmpty()) {
			throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
		}
		
		MemberEntity loginDto = loginDtoOp.get();
		System.out.println(loginDto);
		List<GrantedAuthority> authorities = new ArrayList<>();
		switch(loginDto.getUserGrade()) {
		case "00" : authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
		case "01": authorities.add(new SimpleGrantedAuthority(MemberRole.CUSTOMER.getValue()));
		case "02":authorities.add(new SimpleGrantedAuthority(MemberRole.BUSINESS.getValue()));
		}
		
		return new User(loginDto.getUserId(), loginDto.getUserPwd(), authorities);
	}
}

