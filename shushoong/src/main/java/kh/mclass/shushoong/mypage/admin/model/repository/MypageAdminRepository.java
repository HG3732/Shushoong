package kh.mclass.shushoong.mypage.admin.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.mclass.shushoong.member.model.domain.MemberDto;

@Mapper
public interface MypageAdminRepository {
	
	//회원 아이디 키워드로 검색
	public List<String> selectAllList(String keyword, RowBounds rowBounds);
		
	//회원 키워드 검색 시 조건에 맞는 회원 수 카운트(페이징용)
	public Integer selectTotalCount(String keyword);
<<<<<<< Updated upstream

=======
	
	//회원 아이디로 세부정보 조회
	public MemberDto selectOne(String id);
	
	//회원 결제 내역 조회
	//1. 호텔
	public List<Map<String, String>> selectHotelPayCount(String id);
	
	//2. 항공
	public List<Map<String, String>> selectFlyPayCount(String id);
>>>>>>> Stashed changes
}
