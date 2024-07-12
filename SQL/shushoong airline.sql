SELECT
    *
FROM MEMBER;

INSERT INTO AIRLINE_RESERVE_INFO (AIRLINE_RESERVE_CODE, USER_ID , RESERVE_PHONE, RESERVE_EMAIL)
    		VALUES (1234,'ex1','asddf','fdsase');
INSERT INTO AIRLINE_RESERVE_INFO (AIRLINE_RESERVE_CODE, USER_ID , RESERVE_PHONE, RESERVE_EMAIL)
    		VALUES (1235,'ex1','asddf','fdsase');
INSERT INTO AIRLINE_RESERVE_INFO (AIRLINE_RESERVE_CODE, USER_ID , RESERVE_PHONE, RESERVE_EMAIL)
    		VALUES (1236,'ex1','asddf','fdsase');
INSERT INTO AIRLINE_RESERVE_INFO (AIRLINE_RESERVE_CODE, USER_ID , RESERVE_PHONE, RESERVE_EMAIL)
    		VALUES (1237,'ex1','asddf','fdsase');
INSERT INTO AIRLINE_RESERVE_INFO (AIRLINE_RESERVE_CODE, USER_ID , RESERVE_PHONE, RESERVE_EMAIL)
    		VALUES (1238,'ex1','asddf','fdsase');
SELECT
    *
FROM airline_reserve_info;


DELETE FROM airline_reserve_info
    WHERE airline_reserve_code = 21213;
    
SELECT
    *
FROM passenger_info;

INSERT INTO passenger_info (AIRLINE_RESERVE_CODE,LAST_NAME, FIRST_NAME, GENDER, BIRTH, NATION, BAGGAGE, PASSPORT_CODE)
    VALUES(1234,'SADF','ASFD','M','2023-05-14','ASD','1','1234');

 
INSERT INTO passenger_info_f VALUES (1234 , '2024-06-25');
INSERT INTO passenger_info_f VALUES (1235 , '2024-06-25');
INSERT INTO passenger_info_f VALUES (1236 , '2024-06-25');
INSERT INTO passenger_info_f VALUES (1237 , '2024-06-25');
INSERT INTO passenger_info_f VALUES (1238 , '2024-06-25');   
    
    
    
DELETE passenger_info WHERE PASSPORT_CODE IN( 1234,1235,1236,1237,1238);





INSERT ALL INTO passenger_info (AIRLINE_RESERVE_CODE,LAST_NAME, FIRST_NAME, GENDER, BIRTH, 
NATION, BAGGAGE, PASSPORT_CODE) VALUES( '1234', '영문성1', '영문이름1', 'M', '2024-06-27', '0', '1', 
'1234' ) INTO passenger_info (AIRLINE_RESERVE_CODE,LAST_NAME, FIRST_NAME, GENDER, BIRTH, NATION, 
BAGGAGE, PASSPORT_CODE) VALUES( '1235', '영문성2', '영문이름2', 'M', '2024-06-27', '0', '1', '1235' 
) INTO passenger_info (AIRLINE_RESERVE_CODE,LAST_NAME, FIRST_NAME, GENDER, BIRTH, NATION, BAGGAGE, 
PASSPORT_CODE) VALUES( '1236', '영문성3', '영문이름3', 'M', '2024-06-27', '0', '1', '1236' ) INTO 
passenger_info (AIRLINE_RESERVE_CODE,LAST_NAME, FIRST_NAME, GENDER, BIRTH, NATION, BAGGAGE, 
PASSPORT_CODE) VALUES( '1237', '영문성4', '영문이름4', 'M', '2024-06-27', '0', '1', '1237' ) INTO 
passenger_info (AIRLINE_RESERVE_CODE,LAST_NAME, FIRST_NAME, GENDER, BIRTH, NATION, BAGGAGE, 
PASSPORT_CODE) VALUES( '1238', '영문성5', '영문이름5', 'M', '2024-06-27', '0', '1', '1238' ) SELECT 
* FROM DUAL; 



SELECT * FROM SEAT_GRADE;

SELECT * FROM (SELECT * FROM SEAT_GRADE OVER(PARTITION BY SEAT_PRICE));
select * from member;

SELECT
    *
FROM hotel_like join hotel ON hotel_like.hotel_code=hotel.hotel_code
where hotel_like.user_id = 'customer';

SELECT
    *
FROM hotel;

SELECT
    *
FROM member;
SELECT
    *
FROM hotel_like;
delete from hotel_like where user_id= 'customer';
insert all
    into HOTEL_LIKE(user_id,hotel_code) values('customer','2OS001')
    into HOTEL_LIKE(user_id,hotel_code) values('customer','2OS002')
    into HOTEL_LIKE(user_id,hotel_code) values('customer','2OS003')
    into HOTEL_LIKE(user_id,hotel_code) values('customer','2OS004')
    into HOTEL_LIKE(user_id,hotel_code) values('customer','2OS005')
        SELECT * FROM DUAL; 
commit;
SELECT x.hotel_code,x.hotel_name , z.hotel_picture as hotel_pic FROM hotel x join hotel_like 
y ON x.hotel_code=y.hotel_code join (select * from (SELECT hotel_pic.*,ROW_NUMBER() OVER(PARTITION 
BY hotel_pic.hotel_code ORDER BY hotel_pic.hotel_picture DESC) rn from hotel_pic) where rn 
= 1) z on x.hotel_code = z.hotel_code WHERE y.user_id = 'customer' ;
select * from hotel_pic;

SELECT hotel.hotel_code,hotel.hotel_name FROM hotel_like join hotel ON hotel_like.hotel_code=hotel.hotel_code where hotel_like.user_id 
= 'customer' ;

(select * from (SELECT hotel_pic.*,ROW_NUMBER() OVER(PARTITION BY hotel_pic.hotel_code ORDER BY hotel_pic.hotel_picture DESC) rn from hotel_pic) where rn = 1);

SELECT x.hotel_code,x.hotel_name , z.hotel_picture
FROM hotel x
    join hotel_like y ON x.hotel_code=y.hotel_code 
    join (select * from (SELECT hotel_pic.*,ROW_NUMBER() OVER(PARTITION BY hotel_pic.hotel_code ORDER BY hotel_pic.hotel_picture DESC) rn from hotel_pic) where rn = 1) z on x.hotel_code = z.hotel_code 
        WHERE y.user_id = 'customer'
;

SELECT x.hotel_code,x.hotel_name , z.hotel_picture as hotel_pic FROM hotel x join hotel_like y ON x.hotel_code=y.hotel_code 
join (select * from (SELECT hotel_pic.*,ROW_NUMBER() OVER(PARTITION BY hotel_pic.hotel_code 
ORDER BY hotel_pic.hotel_picture DESC) rn from hotel_pic) where rn = 1) z on x.hotel_code = 
z.hotel_code WHERE y.user_id = 'customer' ;


select*from hotel_review;
SELECT * FROM hotel_review WHERE user_id = 'ex1';
select * from HOTEL_REVIEW where USER_ID = 'ex1';
select * from hotel_review where user_id = 'ex1'; 
select * from hotel;
select * from hotel_reserve;
select* from hotel_like;
select*from pay;
select * from HOTEL_REVIEW;
delete from HOTEL_REVIEW where user_id= 'customer';
select * from hotel_reserve;
select * from pay;
INSERT all
    INTO HOTEL_REVIEW (APPROVE_NO,HOTEL_RESERVE_CODE,REVIEW_TITLE,REVIEW_COMMENT,USER_ID,HOTEL_FACILITY,HOTEL_CLEAN,HOTEL_CONVEN,HOTEL_KIND,TRIPPER_CAT) VALUES ('01908192-51dc-4d5f-4244-2fed73da00cd','20240709262KT0040122', '좋아용 ㅎㅎ','굿굿 ㅎㅎ',   'customer',   5, 3, 5, 5, 1)
    INTO HOTEL_REVIEW (APPROVE_NO,HOTEL_RESERVE_CODE,REVIEW_TITLE,REVIEW_COMMENT,USER_ID,HOTEL_FACILITY,HOTEL_CLEAN,HOTEL_CONVEN,HOTEL_KIND,TRIPPER_CAT)VALUES ('01908161-fed5-29c9-87f0-b8e76fa2444e','2024070962KT0021216', '좋아용 ㅎㅎ', '굿~ ㅎㅎ',   'customer',  4, 3, 4, 3, 2)
    INTO HOTEL_REVIEW (APPROVE_NO,HOTEL_RESERVE_CODE,REVIEW_TITLE,REVIEW_COMMENT,USER_ID,HOTEL_FACILITY,HOTEL_CLEAN,HOTEL_CONVEN,HOTEL_KIND,TRIPPER_CAT)VALUES ('01908166-bf19-25bd-54da-35c077f9832e', '20240703392OS0010','안좋아용 ㅎㅎ',  '안굿 ㅎㅎ',   'customer',2, 1, 2, 0, 3)
    INTO HOTEL_REVIEW (APPROVE_NO,HOTEL_RESERVE_CODE,REVIEW_TITLE,REVIEW_COMMENT,USER_ID,HOTEL_FACILITY,HOTEL_CLEAN,HOTEL_CONVEN,HOTEL_KIND,TRIPPER_CAT)VALUES ('01907d46-d70f-e9b8-6729-86d02c13239c', '2024070582OS0041','좋아용 ㅎㅎ', '굿 ㅎㅎㅎㅎ',  'customer',   4, 3, 3, 3, 2)
    INTO HOTEL_REVIEW (APPROVE_NO,HOTEL_RESERVE_CODE,REVIEW_TITLE,REVIEW_COMMENT,USER_ID,HOTEL_FACILITY,HOTEL_CLEAN,HOTEL_CONVEN,HOTEL_KIND,TRIPPER_CAT)VALUES ('01909617-973e-b592-345b-dd72c3586c02','20240708572KT0032','덜좋아용 ㅎㅎ', '덜굿 ㅎㅎ',  'customer',   2, 3, 4, 3, 2)
    INTO HOTEL_REVIEW (APPROVE_NO,HOTEL_RESERVE_CODE,REVIEW_TITLE,REVIEW_COMMENT,USER_ID,HOTEL_FACILITY,HOTEL_CLEAN,HOTEL_CONVEN,HOTEL_KIND,TRIPPER_CAT)VALUES ('019080d3-8669-88c9-9be0-8f560b5dd157',  '20240708432KT003221','넘좋아용 ㅎㅎ', '굿이랑께', 'customer',   5, 5, 5, 5, 3)
    INTO HOTEL_REVIEW (APPROVE_NO,HOTEL_RESERVE_CODE,REVIEW_TITLE,REVIEW_COMMENT,USER_ID,HOTEL_FACILITY,HOTEL_CLEAN,HOTEL_CONVEN,HOTEL_KIND,TRIPPER_CAT)VALUES ('019080da-bf0f-15d9-4e86-cb2bbdc49223','20240708472OS00502', '좋아유','아주 굿 ㅎㅎ',  'customer',5, 3, 5, 5, 1)
    INTO HOTEL_REVIEW (APPROVE_NO,HOTEL_RESERVE_CODE,REVIEW_TITLE,REVIEW_COMMENT,USER_ID,HOTEL_FACILITY,HOTEL_CLEAN,HOTEL_CONVEN,HOTEL_KIND,TRIPPER_CAT)VALUES ('0190917a-5dc8-df80-f355-7631670fc914','2024070802KT001003', '썩 좋지않소', '장사 이런식으로 계속하면 주인장 당신은 죽소', 'customer',  2, 1, 0, 2, 2)
    select * from dual;
   commit;
   select * from hotel_review where user_id = 'customer' ;
   
   select * from hotel_review join (select hotel_reserve_code,hotel_name from hotel_reserve join hotel using (hotel_code)) using (hotel_reserve_code) where user_id = 'customer';
   
   select * from (select hotel_code,approve_no from hotel_review join hotel_reserve using (hotel_reserve_code)) join hotel using (hotel_code) ;
   
   SELECT
       *
   FROM
    airline_info;
   
  select*from seat_grade ;  
select*from seat_grade where seat_grade = 3  order by seat_price asc;
select * from;
SELECT ROWNUM ,seat_grade.*, airline_info.* FROM seat_grade,airline_info,(SELECT*FROM seat_grade JOIN airline_info USING (airline_code) WHERE seat_grade = 3  ORDER BY seat_price ASC)WHERE ROWNUM BETWEEN 0 AND 6;

select * from hotel_review 
			join (
				select hotel_reserve_code,hotel_name 
					from hotel_reserve join hotel 
						using (hotel_code)
			) using (hotel_reserve_code) 
				where user_id = :1 ;, OriginalSql = select * from hotel_review 
			join (
				select hotel_reserve_code,hotel_name 
					from hotel_reserve join hotel 
						using (hotel_code)
			) using (hotel_reserve_code) ;
            
            
            DELETE FROM airline_reserve_info
    WHERE airline_reserve_code = 21213;
            
DELETE FROM hotel_review
    WHERE hotel_reserve_code = #{hotelResCode} AND user_id = #{userID};

select* FROM hotel_review WHERE hotel_reserve_code = 'customer' AND user_id = '' ;

select * from airline_info;

 SELECT DBMS_RANDOM.NORMAL AS RANDOM_NUM FROM DUAL;
 SELECT DBMS_RANDOM.STRING('X', 5) FROM DUAL;
 SELECT DBMS_RANDOM.VALUE(12, 18) FROM DUAL;
 SELECT FLOOR(5.1214) FROM DUAL;
 commit;