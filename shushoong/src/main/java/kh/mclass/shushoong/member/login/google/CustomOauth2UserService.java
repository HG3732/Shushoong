package kh.mclass.shushoong.member.login.google;

import org.springframework.stereotype.Service;

import kh.mclass.shushoong.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOauth2UserService {
	private final MemberRepository repository;
}
