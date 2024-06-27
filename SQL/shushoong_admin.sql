--아이디에 특정 값이 들어간 회원 조회
select * from member where user_id like '%e%';

SELECT USER_ID, USER_NAME, USER_GRADE FROM MEMBER WHERE NOT USER_GRADE='admin' and USER_ID LIKE '%e%';

--특정 회원의 예약 내역 조회
--1. 호텔
select user_name, hotel_reserve_code, residence_phone from member 
join HOTEL_RESERVE using(user_id) where user_id = 'ex1';
--2. 항공
select user_name, airline_reserve_code, reserve_phone from member 
join AIRLINE_RESERVE_INFO using(user_id) where user_id = 'ex1';