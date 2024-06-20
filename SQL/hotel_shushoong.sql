----------------------------------------호텔이름으로 검색----------------------------------------------------------
select * from hotel where hotel_name like '%${hotelName}%'; --mybatis용
select * from hotel where hotel_name like '%호텔%';

select * from hotel where hotel_name like '%호텔%';

select * from hotel join hotel_room using(hotel_code);


----------------------------------------객실 정보랑 호텔 상세정보랑 합친 table----------------------------------------
select hotel_name, hotel_eng, hotel_address, hotel_call, hotel_check_in, hotel_check_out, hotel_policy, hotel_intro, room_cat, hotel_price, room_att 
from hotel 
    join hotel_room using(hotel_code);


--사진 정보까지 합치기

select hotel_name, hotel_eng, hotel_address, hotel_call, hotel_check_in, hotel_check_out, hotel_policy, hotel_intro, room_cat, hotel_price, room_att 
from hotel 
    join hotel_room using(hotel_code);
	
select * 
from hotel
    join hotel_pic using(hotel_code)
    join hotel_room using(hotel_code)
where hotel_code='2OS001';

desc hotel_room;


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


--페이징처리 위한 sql --> 한페이지에 몇개 띄울건지.. startRonum이랑 endRounum 사이
select s2.* 
from
    (select s1.*, rownum rn 
    from
        (select user_id, tripper_cat, review_title, review_comment, SUBSTR(hotel_reserve_code, 1, 4) || '년 ' || SUBSTR(hotel_reserve_code, 5, 2) || '월 ' || SUBSTR(hotel_reserve_code, 7, 2) || '일' as review_date, 
                hotel_facility, hotel_clean, hotel_conven, hotel_kind, (hotel_facility + hotel_clean + hotel_conven + hotel_kind)/4 as rate_avg
        from hotel_review 
            join hotel_reserve hr using (hotel_reserve_code)
        where hr.hotel_code = '2OS001'
            order by review_date desc) 
    s1)
s2;
--WHERE RN BETWEEN #{startRounum} AND #{endRounum}    ;  


--- 리뷰 페이지 수 (호텔마다 페이지 수 달라짐)   
select count(*) from hotel_review
where hotel_code = '2OS001';

    
    

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
select count(*) as review_count, ROUND(avg(hotel_facility), 1) as avg_hotel_facility, ROUND(avg(hotel_clean), 1) as avg_hotel_clean, ROUND(avg(hotel_conven),1) as avg_hotel_conven, ROUND(avg(hotel_kind),1) as avg_hotel_kind, 
        ROUND((avg(hotel_facility) + avg(hotel_clean) + avg(hotel_conven) + avg(hotel_kind))/4, 1) as avg_all_rate
from hotel_review
    join hotel_reserve hr using (hotel_reserve_code)
where hr.hotel_code = '2OS001';      


--- 다 같이 합치기
SELECT hr.user_id, hr.tripper_cat, hr.review_title, hr.review_comment, SUBSTR(hr.hotel_reserve_code, 1, 4) || '년 ' || SUBSTR(hr.hotel_reserve_code, 5, 2) || '월 ' || SUBSTR(hr.hotel_reserve_code, 7, 2) || '일' as review_date, 
    hr.hotel_facility, hr.hotel_clean, hr.hotel_conven,  hr.hotel_kind, (hr.hotel_facility + hr.hotel_clean + hr.hotel_conven + hr.hotel_kind)/4 as rate_avg,
    stats.reply_count, stats.avg_hotel_facility, stats.avg_hotel_clean, stats.avg_hotel_conven, stats.avg_hotel_kind, stats.avg_all_rate
FROM 
    (SELECT hotel_reserve_code, user_id, tripper_cat, review_title, review_comment, hotel_facility, hotel_clean, hotel_conven, hotel_kind
    FROM hotel_review 
        JOIN hotel_reserve hr USING (hotel_reserve_code)
    WHERE hr.hotel_code = '2OS001'
    ) hr,
        (SELECT count(*) as reply_count, avg(hotel_facility) as avg_hotel_facility, avg(hotel_clean) as avg_hotel_clean, avg(hotel_conven) as avg_hotel_conven, avg(hotel_kind) as avg_hotel_kind, 
        (avg(hotel_facility) + avg(hotel_clean) + avg(hotel_conven) + avg(hotel_kind))/4 as avg_all_rate
        FROM hotel_review
          JOIN hotel_reserve hr USING (hotel_reserve_code)
        WHERE hr.hotel_code = '2OS001'
        ) stats;

----> 합친거 사용 못함.... 이거 사용하게 되면 반복문 돌렸을 때 전체 댓글이 2번 출력되서 해당 칸이 2개 생겨버림..... 난 하나만 필요해서....

---view 테이블 만들기
SELECT ROOM_CAT 
FROM HOTEL_ROOM;

DESC HOTEL_ROOM;

SELECT * FROM HOTEL_ROOM
WHERE hotel_code='2OS001';

SELECT ROOM_CAT_DESC, ROOM_CAP, ROOM_ATT, HOTEL_CODE FROM HOTEL_ROOM_CAT
JOIN HOTEL_ROOM USING(ROOM_CAT)
WHERE hotel_code='2OS001';

SELECT * FROM HOTEL_ROOM_ATT
JOIN HOTEL_ROOM USING(ROOM_ATT)
WHERE hotel_code='2OS001';


--내부적으로 데이터 처리를 위해 hotel_code 넣어야함
SELECT hotel_code, to_char(HOTEL_PRICE, '999,999'), ROOM_CAP, HOTEL_DISCOUNT, ROOM_COUNT, ROOM_ATT_DESC as room_att, ROOM_CAT_DESC as room_cat FROM HOTEL_ROOM
JOIN HOTEL_ROOM_ATT USING(ROOM_ATT)
JOIN HOTEL_ROOM_CAT USING(ROOM_CAT)
WHERE hotel_code='2OS001';


SELECT * FROM HOTEL_ROOM
JOIN HOTEL_ROOM_ATT USING(ROOM_ATT)
JOIN HOTEL_ROOM_CAT USING(ROOM_CAT)
WHERE hotel_code='2OS001';


CREATE OR REPLACE FORCE NONEDITIONABLE VIEW "SHOONG". "V_ROOM_LIST" (
    hotel_code, HOTEL_PRICE, ROOM_CAP, HOTEL_DISCOUNT, ROOM_COUNT, room_att, room_cat
) AS 
SELECT hotel_code, to_char(HOTEL_PRICE, '999,999,999'), ROOM_CAP, HOTEL_DISCOUNT, ROOM_COUNT, ROOM_ATT_DESC as room_att, ROOM_CAT_DESC as room_cat FROM HOTEL_ROOM
JOIN HOTEL_ROOM_ATT USING(ROOM_ATT)
JOIN HOTEL_ROOM_CAT USING(ROOM_CAT)
WHERE hotel_code='2OS001';

--WHERE hotel_code='2OS001' 이거는 create 할때 붙이면 X --> 그러면 where 한 데이터만 조회됨(모두를 위한 view)

select * from v_room_list
WHERE hotel_code='2OS001';

drop view v_room_list;


--------------------- 2024.06.20 리뷰 수정
select user_id, tripper_cat, review_title, review_comment, SUBSTR(hotel_reserve_code, 1, 4) || '년 ' || SUBSTR(hotel_reserve_code, 5, 2) || '월 ' || SUBSTR(hotel_reserve_code, 7, 2) || '일' as review_date, 
        ROUND((hotel_facility + hotel_clean + hotel_conven + hotel_kind)/4, 1) as rate_avg
        from hotel_review 
            join hotel_reserve using (hotel_reserve_code)
        where hotel_code = '2OS001'
            order by review_date desc;



select count(*) as review_count, ROUND(avg(hotel_facility), 1) as avg_hotel_facility, ROUND(avg(hotel_clean), 1) as avg_hotel_clean, ROUND(avg(hotel_conven),1) as avg_hotel_conven, ROUND(avg(hotel_kind),1) as avg_hotel_kind, 
        ROUND((avg(hotel_facility) + avg(hotel_clean) + avg(hotel_conven) + avg(hotel_kind))/4, 1) as avg_all_rate
from hotel_review
    join hotel_reserve hr using (hotel_reserve_code)
where hotel_code = '2OS001';



select count(*) c from hotel_review
    join hotel_reserve using(hotel_reserve_code)
where hotel_code = '2OS001';





-----------------------------------------------리뷰 등록하기-----------------------------------------

desc hotel_review;

insert into hotel_review values (
    #{approveNo}, #{hotelReserveCode}, #{roomCat}, #{hotelCode}, #{roomCap}, #{roomAtt}, #{reviewTitle}, #{reviewComment}, #{hotelFacility}, ${hotelClean} ,${hotelConven},
    #{hotelKind}, #{tripperCat}
);








-------------------------------------결제 관련 sql


select * from hotel_room;

select * from hotel_reserve
join (
select hotel_name, hotel_code
from hotel
    join hotel_room using (hotel_code)) using (hotel_code);

hotel_reserve 테이블
approve_no, room_cat, room_att, request, reserve_name

pay 테이블
pay_price

DESC HOTEL_PIC;


commit;