desc hotel;
desc hotel_facility;
desc hotel_room;
desc hotel_pic;

alter table hotel_room
modify hotel_price varchar2(15);

select * from hotel;
select * from hotel_pic order by hotel_code;
select * from hotel_room order by hotel_code;
select * from hotel_facility order by hotel_code;

insert into member values ('ex1', 'ex1', 'ex1', 'ex1@shushoong.com', 'business', 1, sysdate, 0, 0, sysdate);
insert into business_member values (0612, 'url', 'hotelName', 'ex1');
commit;

desc hotel_room;
-- cat, cap, att, code. price. discount. count
insert into hotel_room values ('3', '4', '4', '2OS005', '1216672', 0, 3);


desc business_member;
desc member;

delete from hotel;
delete from hotel_pic;
delete from hotel_room;
delete from hotel_facility;

desc hotel_reserve;
desc pay;
desc hotel_review;

select * from hotel_reserve;
select * from pay;
select * from hotel_review;
select * from hotel_room;
select * from hotel_room where hotel_code = '2OS001';

INSERT INTO hotel_reserve VALUES ('202405122OS001S01', 1, '2OS001', 2, 3, 'ex1', 'ex1@gmail.com', 'ex1', 'ex1', 'M', '고층', '20240512', '20240515', 'ex1');

INSERT INTO PAY VALUES (778, 3, 15771577, 10000000, DEFAULT, 0, '202405122OS001S01', NULL, 1, '2OS001', 2, 3);

INSERT INTO HOTEL_REVIEW VALUES (778, '202405122OS001S01', 1, '2OS001', 2, 3, '좋아용 ㅎㅎ', 4, 3, 4, 3, 1);

COMMIT;

alter table hotel
add (HOTEL_SAFETY VARCHAR2(1000));

select * from hotel_review;
update hotel_review
set review_title='좋아용';

desc hotel_review;
alter table hotel_review
rename column hotel_comment to review_comment;
select * from hotel_room;

select hotel_name, hotel_eng, hotel_address from hotel where substr(hotel_code, 1, 3) = '2OS';

select hotel_name, hotel_eng, hotel_address, room_cap, hotel_price, hotel_discount from hotel join hotel_room using(hotel_code);

select hotel_name, hotel_eng, hotel_address, min(hotel_price) from hotel_room join hotel using(hotel_code) 
where substr(hotel_code, 1, 3) = '2OS' group by hotel_code, hotel_name, hotel_eng, hotel_address;

select hotel_code,(f + c + co + k)/4 from (
select hotel_code, round(avg(hotel_facility), 1) f, round(avg(hotel_clean), 1) c, round(avg(hotel_conven), 1) co, round(avg(hotel_kind), 1) k 
from hotel_review join hotel h using(hotel_code) where h.hotel_loc_cat= 'OS' group by hotel_code);

select * from hotel_room where hotel_code = '2OS001';

update hotel_room
set hotel_discount = '3' where hotel_code = '2OS001' and room_cat = '1';

commit;
select hotel_name, hotel_eng, hotel_address, hotel_discount, min_price hotel_price, hotel_pic, NVL(score, 0) as hotel_score
		from hotel join (select hotel_code, max(hotel_discount) hotel_discount, MIN(hotel_price) min_price from hotel_room 
        join hotel using(hotel_code)  where room_count > 0 group by hotel_code) using(hotel_code)
        join (select distinct hotel_code, first_value(hotel_picture) over (partition by hotel_code) as hotel_pic from hotel_pic) using(hotel_code)
        left join (select hotel_code, ROUND((f + c + co + k)/4, 1) as score from (
            select hotel_code, avg(hotel_facility) f, avg(hotel_clean) c, avg(hotel_conven) co, avg(hotel_kind) k 
            from hotel_review join hotel h using(hotel_code) where h.hotel_loc_cat= 'OS' group by hotel_code)) using(hotel_code)
            ;

--호텔 사진 1개 호출
select hotel_code, hotel_picture from hotel_pic join hotel h using(hotel_code)
where h.hotel_loc_cat= 'OS';

select distinct hotel_code, first_value(hotel_picture) over (partition by hotel_code order by hotel_code) as hotel_picture
from hotel_pic join hotel h using(hotel_code)
where substr(hotel_code, 1, 3) = '2OS'
;

select * from hotel_room where hotel_price < 1000;
desc hotel_room;

select hotel_code 
    ,(select distinct first_value(hotel_picture) over (partition by hotel_code) as a 
    from hotel_pic  where thc.hotel_code = hotel_code)
from hotel thc
where hotel_loc_cat= 'OS'
;

-- 각 호텔 사진 한장씩
select distinct hotel_code, first_value(hotel_picture) over (partition by hotel_code) as a 
    from hotel_pic 
    --where  hotel_code = '2OS001'
    ;

--해당 호텔의 항목별 별점 평균
select round(avg(hotel_facility), 1), round(avg(hotel_clean), 1), round(avg(hotel_conven), 1), round(avg(hotel_kind), 1)
from hotel_review join hotel h using(hotel_code) where hotel_code = '2OS001';

--각 호텔의 총 별점 평균
select hotel_code,(f + c + co + k)/4 from (
select hotel_code, round(avg(hotel_facility), 1) f, round(avg(hotel_clean), 1) c, round(avg(hotel_conven), 1) co, round(avg(hotel_kind), 1) k 
from hotel_review join hotel h using(hotel_code) where h.hotel_loc_cat= 'OS' group by hotel_code);

--선택한 호텔의 방 최저가 조회
select min(hotel_price) from hotel_room join hotel using(hotel_code) where hotel_code = '2OS001';

--각 호텔의 방 최저가 조회
select hotel_name, hotel_eng, hotel_address, min_price 
from hotel join (select hotel_code, min(hotel_price) min_price from hotel_room join hotel using(hotel_code)
where hotel_loc_cat = 'OS' and room_count > 0 and room_cap = '3' group by hotel_code) using(hotel_code);
select hotel_code, min(hotel_price) min_price from hotel_room join hotel using(hotel_code) where hotel_loc_cat = 'OS' group by hotel_code;

select * from hotel_review join hotel h using(hotel_code) where h.hotel_loc_cat= 'OS';













select hotel_code, hotel_name, hotel_eng, hotel_address from hotel where hotel_loc_cat='OS';

select hotel_picture from hotel_pic where hotel_code='2OS001' and rownum = 1;

--각 호텔 항목별 별점 평균
select  hotel_code, round(avg(hotel_facility), 2), round(avg(hotel_clean), 1), round(avg(hotel_conven),1), round(avg(hotel_kind), 1) 
from hotel_review R
    join hotel h using(hotel_code)
    where h.hotel_loc_cat = 'OS' group by hotel_code;
    

--최저가
select min(hotel_price) 
from hotel_room
    join hotel using(hotel_code) where hotel_code='2OS001';


--각 호텔의 방 최저가 조회
select hotel_code, min(hotel_price) 
from hotel_room
    join hotel using(hotel_code) 
where hotel_loc_cat='OS'
    group by hotel_code;

--선택한 호텔의 항목별 별점 평균
select round(avg(hotel_facility), 1), round(avg(hotel_clean), 1), round(avg(hotel_conven),1), round(avg(hotel_kind), 1) 
from hotel_review
    join hotel using(hotel_code)
    where hotel_code = '2OS001';

--후기 갯수
select count(*) from hotel_review
where hotel_code='2OS001';

--각 호텔의 후기 갯수
select hotel_code, count(*)
from hotel_review
    join hotel using(hotel_code)
    where hotel_loc_cat = 'OS'
    group by hotel_code;

--호텔 편의시설 넘버링
select * from hotel_facility;
--0 무선인터넷 1 주차 2 레스토랑 3 수영장 4 피트니스 5 에어컨 6 바 7 카지노
update hotel_facility
set hotel_fac_cat='1'
where hotel_fac_cat='주차';


--호텔 최고가 조회(슬라이드바 전용)
 select max(min_price) as hotel_price
			from hotel join (select hotel_code, max(hotel_discount) room_discount, MIN(hotel_price) min_price from hotel_room 
        	join hotel using(hotel_code) where SUBSTR(hotel_code, 1, 3) = '2OS' and room_count > 0 and room_cap > 3 group by hotel_code) using(hotel_code)
        	join (select distinct hotel_code, first_value(hotel_picture) over (partition by hotel_code) as hotel_pic from hotel_pic) using(hotel_code)
        	left join (select hotel_code, ROUND((f + c + co + k)/4, 1) as score from (
            	select hotel_code, avg(hotel_facility) f, avg(hotel_clean) c, avg(hotel_conven) co, avg(hotel_kind) k 
            	from hotel_review join hotel h using(hotel_code) where h.hotel_loc_cat= 'OS' group by hotel_code)) using(hotel_code);