select * from hotel;

select hotel_name, hotel_eng, hotel_address, hotel_call, hotel_check_in, hotel_check_out, hotel_policy, hotel_intro from hotel where hotel_code = #{hotelCode};

select room_cat, hotel_price, room_att from hotel_room where hotel_code = #{hotelCode}	;	

--객실 정보랑 호텔 상세정보랑 합친 table
select hotel_name, hotel_eng, hotel_address, hotel_call, hotel_check_in, hotel_check_out, hotel_policy, hotel_intro, room_cat, hotel_price, room_att from hotel 
join hotel_room using(hotel_code);

--호텔이름으로 검색
select * from hotel where hotel_name like '%${hotelName}%'; --mybatis용
select * from hotel where hotel_name like '%호텔%';

select * from hotel where hotel_name like '%호텔%';

select * from hotel join hotel_room using(hotel_code);


------------펀의시설-------------
select * from hotel_facility where hotel_code='2OS001';

select * from hotel_facility;

-- 0:무선인터넷 1:주차 2:레스토랑 3:수영장 4:피트니스센터 5:에어컨 6:바 7:카지노
-- 무선인터넷은 냉장고를 바꾼 것
--크롤링했을때 위에 자료들 들고 들어와지면 그때 밑에 구문 사용해서 번호 메기면 됨

----편의시설에 번호 메기기
update hotel_facility
set hotel_fac_cat ='1'
where hotel_fac_cat ='주차';






















