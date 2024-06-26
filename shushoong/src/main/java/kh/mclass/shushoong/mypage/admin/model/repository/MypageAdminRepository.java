package kh.mclass.shushoong.mypage.admin.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.mclass.shushoong.member.model.domain.MemberDto;

@Mapper
public interface MypageAdminRepository {
	
	// 관리자 전용
	// 회원, 사업자 전용
	// 회원 정보
	List<MemberDto> selectAllList();
	List<MemberDto> selectOne(String userId);
	
	//회원 아이디 키워드로 검색
	public List<String> selectAllList1(String keyword, RowBounds rowBounds);
	
	//회원 키워드 검색 시 조건에 맞는 회원 수 카운트(페이징용)
	public Integer selectTotalCount(String keyword);
	
	// 비밀번호 재설정 
	public int resetPwd(Map<String, Object> map);

}
