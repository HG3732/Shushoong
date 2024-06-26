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


select hotel_code, hotel_name, to_char(hotel_price, '999,999,999') as hotel_price, room_discount, hotel_pic
		from V_hotel_list
		order by hotel_pcount desc;

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
select * from hotel;
select * from hotel_room where hotel_code = '2OS001';


desc hotel_room;
desc hotel_room_att;
desc hotel_room_cat;
--호텔 방 속성 추가 구문
insert all into hotel_room_att values (0, '없음')
        into hotel_room_att values (1, '오션뷰')
        into hotel_room_att values (2, '마운틴뷰')
        into hotel_room_att values (3, '시티뷰')
        into hotel_room_att values (4, '기타')
        select * from dual;

--호텔 방 종류 추가 구문        
insert all into hotel_room_cat values (0, '스탠다드 룸')
        into hotel_room_cat values (1, '디럭스 룸')
        into hotel_room_cat values (2, '슈페리어 룸')
        into hotel_room_cat values (3, '스위트 룸')
        into hotel_room_cat values (4, '기타')
        select * from dual;
        
--호텔 방 예시 추가 구문
insert all
    into hotel_room values (0, 2, 0, '2OS001', 123456, 10, 4)
    into hotel_room values (1, 2, 1, '2OS001', 234567, 0, 3)
    into hotel_room values (2, 4, 3, '2OS001', 345678, 20, 2)
    into hotel_room values (3, 4, 2, '2OS001', 456789, 10, 2)
    into hotel_room values (0, 2, 0, '2OS002', 143456, 10, 4)
    into hotel_room values (1, 2, 2, '2OS002', 254567, 0, 2)
    into hotel_room values (2, 4, 1, '2OS002', 365678, 0, 1)
    into hotel_room values (3, 4, 0, '2OS002', 476789, 0, 3)
    into hotel_room values (0, 2, 2, '2OS003', 343456, 0, 2)
    into hotel_room values (1, 2, 3, '2OS003', 454567, 0, 4)
    into hotel_room values (2, 4, 2, '2OS003', 565678, 0, 2)
    into hotel_room values (3, 5, 4, '2OS003', 676789, 10, 1)
    into hotel_room values (0, 2, 1, '2OS004', 1043456, 0, 5)
    into hotel_room values (1, 2, 4, '2OS004', 2054567, 0, 5)
    into hotel_room values (2, 4, 3, '2OS004', 3065678, 0, 4)
    into hotel_room values (3, 4, 1, '2OS004', 4076789, 10, 5)
    into hotel_room values (0, 2, 2, '2OS005', 553456, 20, 2)
    into hotel_room values (1, 3, 3, '2OS005', 654567, 0, 1)
    into hotel_room values (2, 5, 1, '2OS005', 755678, 0, 3)
    into hotel_room values (3, 5, 0, '2OS005', 856789, 30, 2)
    select * from dual;


--호텔 예약 예시 추가 구문
INSERT all
    INTO hotel_reserve VALUES ('202404122OS001S01', 0, '2OS001', 2, 0, 'ex1', 'ex1@gmail.com', 'ex1', 'ex1', 'M', '고층', '20240412', '20240415', 'ex1')
    INTO hotel_reserve VALUES ('202405122OS001S01', 1, '2OS001', 2, 1, 'ex1', 'ex1@gmail.com', 'ex1', 'ex1', 'M', '고층', '20240512', '20240515', 'ex1')
    INTO hotel_reserve VALUES ('202406122OS001S01', 2, '2OS001', 4, 3, 'ex1', 'ex1@gmail.com', 'ex1', 'ex1', 'M', '고층', '20240512', '20240615', 'ex1')
    INTO hotel_reserve VALUES ('202304122OS001S02', 0, '2OS002', 2, 0, 'ex1', 'ex1@gmail.com', 'ex1', 'ex1', 'M', '고층', '20230412', '20230415', 'ex1')
    INTO hotel_reserve VALUES ('202305122OS001S02', 2, '2OS002', 4, 1, 'ex1', 'ex1@gmail.com', 'ex1', 'ex1', 'M', '고층', '20230512', '20230515', 'ex1')
    INTO hotel_reserve VALUES ('202306122OS001S02', 3, '2OS002', 4, 0, 'ex1', 'ex1@gmail.com', 'ex1', 'ex1', 'M', '고층', '20230512', '20230615', 'ex1')
    INTO hotel_reserve VALUES ('198204122OS001S03', 1, '2OS003', 2, 3, 'ex1', 'ex1@gmail.com', 'ex1', 'ex1', 'M', '고층', '19820412', '19820415', 'ex1')
    INTO hotel_reserve VALUES ('198205122OS001S03', 2, '2OS003', 4, 2, 'ex1', 'ex1@gmail.com', 'ex1', 'ex1', 'M', '고층', '19820512', '19820515', 'ex1')
 select * from dual;

INSERT ALL
    INTO PAY VALUES (770, 3, 15771577, 10000000, DEFAULT, 0, '202404122OS001S01', NULL, 0, '2OS001', 2, 0)
    INTO PAY VALUES (771, 3, 15771577, 12000000, DEFAULT, 0, '202405122OS001S01', NULL, 1, '2OS001', 2, 1)
    INTO PAY VALUES (772, 3, 15771577, 14000000, DEFAULT, 0, '202406122OS001S01', NULL, 2, '2OS001', 4, 3)
    INTO PAY VALUES (773, 3, 15771577, 20000000, DEFAULT, 0, '202304122OS001S02', NULL, 0, '2OS002', 2, 0)
    INTO PAY VALUES (774, 3, 15771577, 22000000, DEFAULT, 0, '202305122OS001S02', NULL, 2, '2OS002', 4, 1)
    INTO PAY VALUES (775, 3, 15771577, 24000000, DEFAULT, 0, '202306122OS001S02', NULL, 3, '2OS002', 4, 0)
    INTO PAY VALUES (70, 3, 15771577, 1000000, DEFAULT, 0, '198204122OS001S03', NULL, 1, '2OS003', 2, 3)
    INTO PAY VALUES (71, 3, 15771577, 1200000, DEFAULT, 0, '198205122OS001S03', NULL, 2, '2OS003', 4, 2)
    select * from dual;

INSERT ALL 
    INTO HOTEL_REVIEW VALUES (770, '202404122OS001S01', 0, '2OS001', 2, 0, '좋아용 ㅎㅎ', '굿굿 ㅎㅎ', 5, 3, 5, 5, 1)
    INTO HOTEL_REVIEW VALUES (771, '202405122OS001S01', 1, '2OS001', 2, 1, '좋아용 ㅎㅎ', '굿~ ㅎㅎ', 4, 3, 4, 3, 2)
    INTO HOTEL_REVIEW VALUES (772, '202406122OS001S01', 2, '2OS001', 4, 3, '안좋아용 ㅎㅎ', '안굿 ㅎㅎ', 2, 1, 2, 0, 3)
    INTO HOTEL_REVIEW VALUES (773, '202304122OS001S02', 0, '2OS002', 2, 0, '좋아용 ㅎㅎ', '굿 ㅎㅎㅎㅎ', 4, 3, 3, 3, 2)
    INTO HOTEL_REVIEW VALUES (774, '202305122OS001S02', 2, '2OS002', 4, 1, '덜좋아용 ㅎㅎ', '덜굿 ㅎㅎ', 2, 3, 4, 3, 2)
    INTO HOTEL_REVIEW VALUES (775, '202306122OS001S02', 3, '2OS002', 4, 0, '넘좋아용 ㅎㅎ', '아주 굿 ㅎㅎ', 5, 5, 5, 5, 3)
    INTO HOTEL_REVIEW VALUES (70, '198204122OS001S03', 1, '2OS003', 2, 3, '좋아유', '굿이랑께',5, 3, 5, 5, 1)
    INTO HOTEL_REVIEW VALUES (71, '198205122OS001S03', 2, '2OS003', 4, 2, '썩 좋지않소', '장사 이런식으로 계속하면 주인장 당신은 죽소', 2, 1, 0, 2, 2)
    select * from dual;

COMMIT;
select * from hotel_room;
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
    from hotel_pic;

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
                
                
select * from hotel_like;

insert all 
    into hotel_like values ('ex1', '2OS001', 1)
    into hotel_like values ('ex1', '2OS003', 1)
    select * from dual;

commit;

select * from hotel;
select substr(hotel_reserve_code, 9, 6) from hotel_review;

select * from hotel_review;

select h.hotel_code, round((avg(hotel_facility) + avg(hotel_clean) + avg(hotel_conven) + avg(hotel_kind))/4, 1) as avg_score, count(substr(hrv.hotel_reserve_code, 9, 6)) as review_num
from hotel_review hrv right join hotel h on substr(hrv.hotel_reserve_code, 9, 6) = h.hotel_code group by h.hotel_code;
-- 호텔 리스트 뷰 생성
  CREATE OR REPLACE FORCE NONEDITIONABLE VIEW "SHOONG"."V_HOTEL_LIST" ("HOTEL_CODE", "HOTEL_PIC", "HOTEL_NAME", "HOTEL_ENG", "HOTEL_ADDRESS", "HOTEL_PCOUNT", "HOTEL_SCORE", "HOTEL_REVIEW_NUM", "HOTEL_PRICE", "ROOM_DISCOUNT", "ROOM_CAP") AS 
  select distinct hp.hotel_code, first_value(hp.hotel_picture) over (partition by hp.hotel_code) as hotel_picture, h.hotel_name, h.hotel_eng, h.hotel_address, h.hotel_pcount, nvl(hotel_review2.avg_score, 0), hotel_review2.review_num, hrm.min_price, hrm.hotel_discount, hrm.room_cap

from hotel_pic hp, hotel h, 
(select h.hotel_code, round((avg(hotel_facility) + avg(hotel_clean) + avg(hotel_conven) + avg(hotel_kind))/4, 1) as avg_score, count(substr(hrv.hotel_reserve_code, 9, 6)) as review_num
from hotel_review hrv right join hotel h on substr(hrv.hotel_reserve_code, 9, 6) = h.hotel_code group by h.hotel_code) hotel_review2,
(select t1.*, t2.min_price from hotel_room t1 join (select hotel_code, min(hotel_price) min_price from hotel_room where room_count > 0 group by hotel_code, room_cap) t2 on t1.hotel_code = t2.hotel_code and t1.hotel_price = t2.min_price) hrm

where hp.hotel_code = h.hotel_code and hp.hotel_code = hotel_review2.hotel_code and hp.hotel_code = hrm.hotel_code
;

select * from v_hotel_list;

drop view V_hotel_list;

select * from hotel_room where hotel_discount>0;

desc V_hotel_list;

select hotel_code, hotel_name, to_char(hotel_price, '999,999') as hotel_price, room_discount, hotel_pic, hotel_pcount
		from V_hotel_list
        where SUBSTR(hotel_code, 1, 1) = '2' order by hotel_pcount desc;

select v.hotel_code, v.hotel_pic, v.hotel_name, v.hotel_eng, v.hotel_address, v.hotel_price, trunc(v.hotel_price*(1 - hr.hotel_discount/100)), hr.room_count, hr.hotel_discount 
from V_hotel_list v join hotel_room hr on v.hotel_code = hr.hotel_code and v.hotel_price = hr.hotel_price; 

-- 할인 중 호텔 리스트
select v.hotel_code hotel_code, v.hotel_pic hotel_pic, v.hotel_name hotel_name, v.hotel_eng hotel_eng,
		v.hotel_address hotel_address, hr.room_cat_desc room_cat, v.hotel_price hotel_price, to_char(trunc(v.hotel_price*(1 - hr.hotel_discount/100)), '999,999,999') discounted_price, hr.hotel_discount discount_ratio, hr.room_count room_count 
		from (select * from hotel_room join hotel_room_cat using(room_cat)) hr join v_hotel_list v on hr.hotel_code = v.hotel_code where hr.hotel_discount > 0
		order by hotel_discount desc;
        
        select * from pay;

select t1.* 
from (select hotel_code, hotel_name, hotel_eng, hotel_address, to_char(hotel_price, '999,999,999') as hotel_price, room_discount, hotel_pic, hotel_score, hotel_review_num, rownum rn
		from V_hotel_list 
		where SUBSTR(hotel_code, 1, 3) = '2OS' and room_cap = 2) t1
        where rn between 3 and 5;

select rank() over(order by hotel_reserve_code desc) daterank, substr(hotel_reserve_code, 9, 6) hotel_code, review_title, review_comment from hotel_review order by hotel_reserve_code desc;

select distinct hp.hotel_code hotel_code, first_value(hotel_picture) over (partition by hp.hotel_code) as hotel_picture, review_title, review_comment
    from hotel_pic hp
    join (select rank() over(order by hotel_reserve_code desc) daterank, substr(hotel_reserve_code, 9, 6) hotel_code, review_title, review_comment from hotel_review) hr
    on hp.hotel_code = hr.hotel_code
    where daterank = 1
    ;

--이 레코드에 조회 되면 리뷰 달 수 있는 테이블
select * from(
select hotel_reserve_code, approve_no, substr(hotel_reserve_code, 9, 6) hotel_code, to_date(substr(hotel_reserve_code, 1, 8)) check_in, sysdate
from hotel_reserve join pay using(hotel_reserve_code))
where check_in < to_date('240101')
-- and user_Id = '' and hotel_code = ''
;
select * from hotel_review;
select count(*) from hotel_review
where user_Id = 'ex1';

select * from hotel_room;

select * from hotel_room where hotel_code = '2OS001' order by room_cap;

select max(hotel_price) from V_hotel_list where SUBSTR(hotel_code, 1, 3) = '2OS' and room_cap >= '4';

--
