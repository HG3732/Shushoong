select * from hotel;

select hotel_name, hotel_eng, hotel_address, hotel_call, hotel_check_in, hotel_check_out, hotel_policy, hotel_intro from hotel where hotel_code = #{hotelCode};

select room_cat, hotel_price, room_att from hotel_room where hotel_code = #{hotelCode}	;	

--객실 정보랑 호텔 상세정보랑 합친 table
select hotel_name, hotel_eng, hotel_address, hotel_call, hotel_check_in, hotel_check_out, hotel_policy, hotel_intro, room_cat, hotel_price, room_att from hotel 
join hotel_room using(hotel_code);

--펀의시설
select * from hotel_facility where hotel_code='2OS001';

select hotel_code, hotel_comment  from hotel_review;

--호텔이름으로 검색
select * from hotel where hotel_name like '%${hotelName}%'; --mybatis용
select * from hotel where hotel_name like '%호텔%';

select * from hotel where hotel_name like '%호텔%';
