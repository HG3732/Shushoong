select * from hotel;

select hotel_name, hotel_eng, hotel_address, hotel_call, hotel_check_in, hotel_check_out, hotel_policy, hotel_intro from hotel where hotel_code = #{hotelCode} ;

select room_cat, hotel_price, room_att from hotel_room where hotel_code = #{hotelCode}	;	


----------------------------------------객실 정보랑 호텔 상세정보랑 합친 table----------------------------------------
select hotel_name, hotel_eng, hotel_address, hotel_call, hotel_check_in, hotel_check_out, hotel_policy, hotel_intro, room_cat, hotel_price, room_att from hotel 
join hotel_room using(hotel_code);


----------------------------------------호텔이름으로 검색----------------------------------------------------------
select * from hotel where hotel_name like '%${hotelName}%'; --mybatis용
select * from hotel where hotel_name like '%호텔%';

select * from hotel where hotel_name like '%호텔%';

select * from hotel join hotel_room using(hotel_code);


------------펀의시설-------------
select * from hotel_facility where hotel_code='2OS001';

select * from hotel_facility;

select * from hotel_room where hotel_code='2OS001';

desc hotel_room;

insert into hotel_room values (
    '2', '3', '3', '2OS001', '171259', 0,  5
);


--------------------------------------------편의시설에 번호 메기기-------------------------------------------
update hotel_facility
set hotel_fac_cat ='1'
where hotel_fac_cat ='주차';
-- 0:무선인터넷 1:주차 2:레스토랑 3:수영장 4:피트니스센터 5:에어컨 6:바 7:카지노
-- 무선인터넷은 냉장고를 바꾼 것
--크롤링했을때 위에 자료들 들고 들어와지면 그때 밑에 구문 사용해서 번호 메기면 됨


------------------------------------- 리뷰 ----------------------------------------
----dto 에 추가할 때 reviewDate, rateAvg 로 필드명 추가
select user_id, tripper_cat, review_title, review_comment, SUBSTR(hotel_reserve_code, 1, 4) || '년 ' || SUBSTR(hotel_reserve_code, 5, 2) || '월 ' || SUBSTR(hotel_reserve_code, 7, 2) || '일' as review_date, 
hotel_facility, hotel_clean, hotel_conven, hotel_kind, (hotel_facility + hotel_clean + hotel_conven + hotel_kind)/4 as rate_avg
from hotel_review 
join hotel_reserve hr using (hotel_reserve_code)
    where hr.hotel_code = '2OS001';

------리뷰 전체 평균 구하기
--각 평점 항목 평균 먼저 구하기 -> chart js 용 
select count(*), avg(hotel_facility), avg(hotel_clean), avg(hotel_conven), avg(hotel_kind) from hotel_review
    join hotel_reserve hr using (hotel_reserve_code)
where hr.hotel_code = '2OS001';


--각 평점 항목 평균 가지고 전체 평균 구하기 + 전체 리뷰 갯수 불러오기 --> chart js 왼쪽에 표시될 내용 
select (avg(hotel_facility) + avg(hotel_clean) + avg(hotel_conven) + avg(hotel_kind))/4 as all_rate_avg from hotel_review
    join hotel_reserve hr using (hotel_reserve_code)
where hr.hotel_code = '2OS001';


---------위에 두개 합치기
select count(*) as review_count, avg(hotel_facility) as avg_facility, avg(hotel_clean) as avg_hotel_clean, avg(hotel_conven) as avg_hotel_conven, avg(hotel_kind) as avg_hotel_kind, 
        (avg(hotel_facility) + avg(hotel_clean) + avg(hotel_conven) + avg(hotel_kind))/4 as avg_all_rate
from hotel_review
    join hotel_reserve hr using (hotel_reserve_code)
where hr.hotel_code = '2OS001';      


--- 다 같이 합치기
SELECT hr.user_id, hr.tripper_cat, hr.review_title, hr.review_comment, SUBSTR(hr.hotel_reserve_code, 1, 4) || '년 ' || SUBSTR(hr.hotel_reserve_code, 5, 2) || '월 ' || SUBSTR(hr.hotel_reserve_code, 7, 2) || '일' as review_date, 
    hr.hotel_facility, hr.hotel_clean, hr.hotel_conven,  hr.hotel_kind, (hr.hotel_facility + hr.hotel_clean + hr.hotel_conven + hr.hotel_kind)/4 as rate_avg,
    stats.reply_count, stats.avg_facility, stats.avg_hotel_clean, stats.avg_hotel_conven, stats.avg_hotel_kind, stats.avg_all_rate
FROM 
    (SELECT hotel_reserve_code, user_id, tripper_cat, review_title, review_comment, hotel_facility, hotel_clean, hotel_conven, hotel_kind
    FROM hotel_review 
        JOIN hotel_reserve hr USING (hotel_reserve_code)
    WHERE hr.hotel_code = '2OS001'
    ) hr,
        (SELECT count(*) as reply_count, avg(hotel_facility) as avg_facility, avg(hotel_clean) as avg_hotel_clean, avg(hotel_conven) as avg_hotel_conven, avg(hotel_kind) as avg_hotel_kind, 
        (avg(hotel_facility) + avg(hotel_clean) + avg(hotel_conven) + avg(hotel_kind))/4 as avg_all_rate
        FROM hotel_review
          JOIN hotel_reserve hr USING (hotel_reserve_code)
        WHERE hr.hotel_code = '2OS001'
        ) stats;

----> 합친거 사용 못함.... 이거 사용하게 되면 반복문 돌렸을 때 전체 댓글이 2번 출력되서 해당 칸이 2개 생겨버림..... 난 하나만 필요해서....







commit;