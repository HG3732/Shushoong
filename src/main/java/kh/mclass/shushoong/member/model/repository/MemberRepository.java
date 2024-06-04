package kh.mclass.shushoong.member.model.repository;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.member.model.domain.MemberDto;


@Mapper
public interface MemberRepository {
	public MemberDto login(String userId);
	public MemberDto insert(HashMap<String, Object> map);
}
