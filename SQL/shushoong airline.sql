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
 SELECT FLOOR(DBMS_RANDOM.VALUE(12, 18))||':'||FLOOR(DBMS_RANDOM.VALUE(00, 60)) FROM DUAL;
set define off;
 commit;
 select * from AIRLINE_INFO;
 select * from COUNTRY_LOC_PIC;
 insert INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('CHINA','PVG','https://encrypted-tbn3.gstatic.com/licensed-image?q=tbn:ANd9GcQV51pF9uKWKZOKR3v5RBWoLU0xpmt6Q_zkimpI2LqF7IXn5ExM8lVuSw4ycWAzaI-TY9sxpjP-ZMhOBPN-XHB0GOFAX9pcJoVHVPha-Q');
 insert ALL 
    INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('KOREA','ICN','https://lh3.googleusercontent.com/proxy/m6tMTeP9Yc8e3LFjetj5khVq6UGIq5hwBL64oB4TB95FOlKgdk3fUrJf-6e3azWCO-rucr5XBmnFxkzPYQw3jEKV--mz9Chr2dypmjXxRtqkTfP8ed6WTqxKtZm7fdu3lxZSEiY6Ur8ErDSQ1ZMihWD9tQ1-O_I=s680-w680-h510')
    INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('KOREA','GMP','https://img.freepik.com/free-photo/downtown-cityscape-at-night-in-seoul-south-korea_335224-272.jpg?size=626&ext=jpg&ga=GA1.1.1359838702.1718694112&semt=ais_user')
    INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('KOREA','CJU','https://api.cdn.visitjeju.net/photomng/imgpath/202206/03/ee8dcfae-9eb3-4c53-a70d-e21a023d83be.jpg')
    INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('KOREA','PUS','https://www.visitbusan.net/uploadImgs/files/hqimgfiles/20200327141200390_thumbL')
    
    INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('JAPAN','NRT','https://media.istockphoto.com/id/1390815938/ko/%EC%82%AC%EC%A7%84/%EC%9D%BC%EB%B3%B8%EC%9D%98-%EB%8F%84%EC%BF%84%EC%8B%9C.jpg?s=612x612&w=0&k=20&c=M3ABDYoJPya2r3A-TsfPfsYCmb6Y-qm17TK8L3Mj-gs=')
    INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('JAPAN','FUK','https://d2mgzmtdeipcjp.cloudfront.net/files/good/2020/04/25/15878000229322.jpg')
    INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('JAPAN','OKA','https://rimage.gnst.jp/livejapan.com/public/article/detail/a/20/00/a2000366/img/basic/a2000366_main.jpg')
    
    INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('CHINA','PEK','https://res.klook.com/images/fl_lossy.progressive,q_65/c_fill,w_1295,h_720/w_80,x_15,y_15,g_south_west,l_Klook_water_br_trans_yhcmh3/activities/gjn7mejkbvlf4nlthgeg/%EB%B2%A0%EC%9D%B4%EC%A7%95%EC%9D%BC%EC%9D%BC%EC%B0%A8%EB%9F%89%ED%88%AC%EC%96%B4.jpg')
    INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('CHINA','CAN','https://i.namu.wiki/i/wz1RIp1hXC9ymSpQefKWMaIDJtlysM6dymRri1Rb2Ql5DhUutdoV16j_snMwJ1sRipVE0r4awXHIZzSFe0J35A.jpg')
    INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('CHINA','SHA','https://lh6.googleusercontent.com/proxy/W-gD7FZcSLlMMMYqludJgS330vc6F_MioKYVX1kc5Rm1HWLQMv-B4wmmbeucsleTgK2YCVo-H5UYnDjnl8QKI7oocgjH0-yZsHM')
    INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('CHINA','PKX','https://encrypted-tbn2.gstatic.com/licensed-image?q=tbn:ANd9GcRLWZR26T8cd_vNNYVUglqPhEf-Oksn0kh-8XYKpTQM8WQijOvsy4qxEvCFWtJFGdcYdREeqV3sJipluSQTIXCVK9HUexJlNaqwevqwFw')
    INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('CHINA','PVG','https://encrypted-tbn3.gstatic.com/licensed-image?q=tbn:ANd9GcQV51pF9uKWKZOKR3v5RBWoLU0xpmt6Q_zkimpI2LqF7IXn5ExM8lVuSw4ycWAzaI-TY9sxpjP-ZMhOBPN-XHB0GOFAX9pcJoVHVPha-Q')
    
    INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('USA','SEA','https://wimg.mk.co.kr/meet/neds/2018/10/image_readtop_2018_649598_15398256933498171.jpg')
    INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('USA','SFO','https://mediaim.expedia.com/destination/9/374fff8a75e97e4d955bb3b02d9a2caf.jpg')
    INTO COUNTRY_LOC_PIC (COUNTRY , LOCAL , PICTURE) VALUES ('USA','MIA','https://wimg.mk.co.kr/news/cms/202308/21/20230821_01110207000004_L00.jpg')
        SELECT * FROM DUAL
;
 
 SELECT * FROM airline_info JOIN seat_grade USING (airline_code) ORDER BY seat_price asc;
 SELECT ai,sg FROM (SELECT ai,sg FROM ai,sg, ROW_NUMBER() OVER(PARTITION BY (SELECT * FROM airline_info as ai JOIN seat_grade as sg USING (airline_code)) as info_warp ORDER BY sg.seat_price) as rnk) WHERE rnk = 1;
SELECT * FROM airline_info JOIN seat_grade USING (airline_code);
SELECT info_warp , ROW_NUMBER() OVER(PARTITION BY info_wrap.arrival_loc ORDER BY info_wrap.price asc) FROM (airline_info JOIN seat_grade USING (airline_code)) info_warp ;
SELECT result.* ,ROW_NUMBER() OVER(PARTITION BY airline_info.arrival_loc ORDER BY seat_grade.seat_price asc) FROM (airline_info JOIN seat_grade USING (airline_code)) result ;

SELECT addresult.* , country_loc_pic.country,country_loc_pic.picture
FROM
    (SELECT *
    FROM
        (SELECT *
        FROM
            (SELECT result.* ,ROW_NUMBER() OVER(PARTITION BY result.arrival_loc ORDER BY result.seat_price asc) as rn
            FROM (
                SELECT ai.* , sg.seat_grade , sg.seat_total , sg.seat_reserved , sg.seat_price
                FROM airline_info ai
                LEFT JOIN seat_grade sg
                ON (ai.airline_code = sg.airline_code)
            ) result
        ) 
        WHERE rn = 1  and domestic_flights = 'I' AND arrival_loc NOT LIKE 'ICN'
        ORDER BY seat_price asc)
    WHERE rownum BETWEEN 0 AND 6
    ) addresult
LEFT JOIN COUNTRY_LOC_PIC
ON (addresult.arrival_loc = COUNTRY_LOC_PIC.local)
;

SELECT ai.* , sg.*
    FROM airline_info ai
    JOIN seat_grade sg
    ON (ai.airline_code = sg.airline_code)
;
    
    
 select * from seat_grade;
 select * from airline_info;
  select * from COUNTRY_LOC_PIC;
 (select * from (SELECT hotel_pic.*,ROW_NUMBER() OVER(PARTITION BY hotel_pic.hotel_code ORDER BY hotel_pic.hotel_picture DESC) rn from hotel_pic) where rn = 1);