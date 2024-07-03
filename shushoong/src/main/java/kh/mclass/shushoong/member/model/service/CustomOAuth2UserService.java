package kh.mclass.shushoong.member.model.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kh.mclass.shushoong.member.login.google.GoogleUserDetails;
import kh.mclass.shushoong.member.model.domain.CustomOauth2UserDetails;
import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.member.model.domain.Oauth2UserInfo;
import kh.mclass.shushoong.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService{
//	private final MemberRepository memberRepository;
//	
//	@Override
//	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//		OAuth2User oAuth2User = super.loadUser(userRequest);
//		log.info("getAttributes : {}", oAuth2User.getAttributes());
//		
//		String provider = userRequest.getClientRegistration().getRegistrationId();
//		
//		Oauth2UserInfo oAuth2UserInfo = null;
//		
//		// 다른 소셜 서비스 로그인을 위해 구분
//		if(provider.equals("google")) {
//			log.info("구글 로그인");
//			oAuth2UserInfo = new GoogleUserDetails(oAuth2User.getAttributes());
//		}
//		
//		String providerId = oAuth2UserInfo.getProviderId();
//        String email = oAuth2UserInfo.getEmail();
//        String loginId = provider + "_" + providerId;
//        String name = oAuth2UserInfo.getName();
//        
//        MemberDto findMember = memberRepository.findByLoginId(loginId);
//        MemberDto member;
//        
//        if (findMember == null) {
//            member = MemberDto.builder()
//                    .loginId(loginId)
//                    .name(name)
//                    .provider(provider)
//                    .providerId(providerId)
//                    .role(MemberRole.USER)
//                    .build();
//            memberRepository.save(member);
//        } else{
//            member = findMember;
//        }
//
//        return new CustomOauth2UserDetails(member, oAuth2User.getAttributes());
//	}
}
