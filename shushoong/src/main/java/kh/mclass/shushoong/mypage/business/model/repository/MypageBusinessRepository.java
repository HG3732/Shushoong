package kh.mclass.shushoong.mypage.business.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.mclass.shushoong.hotel.model.domain.HotelReqDto;
import kh.mclass.shushoong.hotel.model.domain.HotelRoomDto;
import kh.mclass.shushoong.member.model.domain.MemberDto;
import kh.mclass.shushoong.mypage.admin.model.domain.ProductDtoRes;

@Mapper
public interface MypageBusinessRepository {

	public MemberDto selectOne(String userId);

	// 비밀번호 체크
	public String pwdChecking(String userId);

	// 비밀번호 재설정
	public int resetPwd(Map<String, Object> paraMap);

	//내 사업장 검색
	public List<ProductDtoRes> selectMyProduct(int pageSize, int pageBlockSize, int currentPageNum, String id, String keyword, RowBounds rowbounds);
	
	//상품 갯수
	public int selectMyAllProductCount(String id, String keyword);
	
	//상품 삭제 테이블로 이동
	public int insertHotelDeleted(String hotelCode);

	//상품 삭제
	public int deleteHotel(String hotelCode);
	
	//사업자 등록
	public int insertCerti(String businessCerti, String businessRegit, String userId);
	
	//호텔 등록
	public int insertHotel(HotelReqDto dto);
	
	//호텔 사진 등록
	public int insertHotelPic(String hotelCode, List<String> urls);
	
	//호텔 편의 시설 등록
	public int insertHotelFac(String hotelCode, List<String> hotelFacs);
	
	//호텔 방 등록
	public int insertHotelRoom(String hotelCode, List<HotelRoomDto> rooms);
	
}
