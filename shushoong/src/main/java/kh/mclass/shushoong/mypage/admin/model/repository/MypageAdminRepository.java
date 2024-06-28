package kh.mclass.shushoong.mypage.admin.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.member.model.domain.MemberDto;

@Mapper
public interface MypageAdminRepository {
	
	//회원 아이디 키워드로 검색
	public List<String> selectAllList(String keyword);
		
	//회원 키워드 검색 시 조건에 맞는 회원 수 카운트(페이징용)
	public Integer selectTotalCount(String keyword);

	//회원 아이디로 세부정보 조회
	public MemberDto selectOne(String id);
	
	//회원 결제 내역 조회
	//1. 호텔
	public List<Map<String, String>> selectHotelPayCount(String id);
	
	//2. 항공
	public List<Map<String, String>> selectFlyPayCount(String id);

	//1:1문의내역 갯수 조회
	public String selectFAQCount(String id);
	
	//회원 정지, 정지해제
	public int updateLockAccount(String id);
	public int updateUnlockAccount(String id);
	
	//1:1문의내역 최근 3개 조회
	public List<Map<String, String>> selectLatestFaq();
	
	//장기 미사용 계정 검색
	public List<String> selectDormantAccount(String keyword);
	
	//마지막 로그인으로부터 경과시간 조회
	public int selectUseTerm(String id);
}
