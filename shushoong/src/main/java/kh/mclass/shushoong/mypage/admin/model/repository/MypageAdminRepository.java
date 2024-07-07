package kh.mclass.shushoong.mypage.admin.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.mypage.admin.model.domain.ProductDtoRes;

@Mapper
public interface MypageAdminRepository {
	
	//최신 공지사항 3개 출력
	public List<Map<String, Object>> selectLatestNotice();
	
	//최신 1:1문의 3개 출력
	public List<Map<String, String>> selectLatestFaq();
	
	//회원 아이디 키워드로 검색
	public List<String> selectAllList(String keyword, int memCat);
		
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
	
	//해당 유저의 1:1문의내역 최근 3개 조회
	public List<Map<String, String>> selectOneLatestFaq(String id);
	
	//상품 등록 갯수 조회
	public int selectProductCount(String id);
	
	//상품 미등록 계정 검색
	public List<String> selectNoSaleAccount(String keyword);
	
	//장기 미사용 계정 검색
	public List<String> selectDormantAccount(String keyword, int memCat);
	
	public int updateAllLock(String keyword);
	
	//마지막 로그인으로부터 경과시간 조회
	public int selectUseTerm(String id);
	
	
	
	//사업장 검색
	public List<ProductDtoRes> selectProduct(int pageSize, int pageBlockSize, int currentPageNum, String category, String keyword, RowBounds rowbounds);
	
	//상품 갯수
	public int selectAllProductCount(String category, String keyword);
}
