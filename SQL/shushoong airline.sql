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
SELECT * FROM (SELECT * FROM (SELECT * FROM SEAT_GRADE OVER(PARTITION BY SEAT_PRICE ORDER BY DESC) JOIN AIRLINE_INFO ON airline_info.airline_code=seat_grade.airline_code)  ORDER BY SEAT_PRICE ASC) WHERE ROWNUM <= 6;

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

insert into HOTEL_LIKE values('customer','2OS005');
commit;

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

