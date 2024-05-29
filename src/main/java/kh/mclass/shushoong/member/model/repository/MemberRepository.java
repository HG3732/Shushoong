package kh.mclass.shushoong.member.model.repository;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.member.model.domain.MemberEntity;


@Mapper
public interface MemberRepository {
	public MemberEntity login(String userId);
	public MemberEntity insert(HashMap<String, Object> map);
}
