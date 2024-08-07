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

--FAQ_CAT에 예시용 레코드 등록 반복문
BEGIN 
    FOR N IN 1..50 LOOP
        insert into faq_cat values (N, 1);
    END LOOP;
    commit;
END;
/

BEGIN 
    FOR N IN 1..50 LOOP
        insert into service_center values (N, 'ex1', '문의' || N, '어떻게 하냐고 ' || N || '번째 물어봅니다', to_char(to_date('2024-06-26 16:' || N, 'YYYY-MM-DD HH24:MI'), 'YYYY-MM-DD HH24:MI'), NULL, NULL);
    END LOOP;
    commit;
END;
/

select * from service_center join (select * from faq_cat join faq_cat_desc using(quest_cat)) using(faq_id);

--문의내역 최신 3개 등록
select * from (
select faq_id, quest_cat, user_id, ask_title, ask_content, ask_date, ans_content, ans_time from service_center join faq_cat using(faq_id) order by ask_date desc)
where rownum <= 3;

select FAQ_ID, ASK_TITLE, QUEST_CAT_DESC, USER_ID, ASK_DATE from (
        select faq_id, quest_cat_desc, user_id, ask_title, ask_content, ask_date, ans_content, ans_time from service_center 
            join (select faq_id, quest_cat_desc from faq_cat join faq_cat_desc using(quest_cat)) using(faq_id) order by ask_date desc)
        where rownum <= 3;
        
        select FAQ_ID, ASK_TITLE, QUEST_CAT_DESC, USER_ID, ASK_DATE from (
			select faq_id, quest_cat, user_id, ask_title, ask_content, ask_date, ans_content, ans_time from service_center
            join faq_cat using(faq_id) order by ask_date desc)
            join faq_cat_desc using(quest_cat)
			where rownum <= 3;

desc member;
--예시용 회원 레코드 등록 반복문
BEGIN 
    FOR N IN 2..50 LOOP
        insert into member values ('ex' || N, 'name' || N, 'pwd' || N, 'ss' || N || '@shoong.com', 'customer', 1, to_date('230101'), 0, 0, to_date('230102'));
    END LOOP;
    commit;
END;
/

BEGIN 
    FOR N IN 1..50 LOOP
        insert into service_center values (N, 'ex1', '문의' || N, '어떻게 하냐고 ' || N || '번째 물어봅니다', to_char(to_date('2024-06-26 16:' || N, 'YYYY-MM-DD HH24:MI'), 'YYYY-MM-DD HH24:MI'), NULL, NULL);
    END LOOP;
    commit;
END;
/

select (sysdate - year) from dual;

-- 1년이상 로그인 안한 유저들 조회
select add_months(sysdate, -12) from dual;
select * from member where latest_login < add_months(sysdate, -12);
select to_number(trunc(sysdate - latest_login)) from member;

-- 1:1문의 뷰
select quest_cat_desc, faq_id, user_id, ask_title, ask_content, ask_date, ans_content, ans_time from service_center join faq_cat using (faq_id) join faq_cat_desc using(quest_cat);

update service_center
set ans_time = sysdate, ans_content = '이렇게 하면 된다고 47번째 말씀드립니다.'
where faq_id = '47';

--최근 등록된 공지사항 3개 조회
select * from (
select notice_id, notice_title, notice_content, notice_time, user_id from notice_board order by notice_time desc) where rownum < 4;

select FAQ_ID, ASK_TITLE, QUEST_CAT_DESC, USER_ID, ASK_DATE, ANS_TIME from (
			select faq_id, quest_cat, user_id, ask_title, ask_content, ask_date, ans_content, ans_time from service_center
            join faq_cat using(faq_id) where user_id = 'ex1' order by ask_date desc)
            join faq_cat_desc using(quest_cat)
			where rownum <= 3 and user_id = 'ex1';

--사업자 회원 등록한 상품 건수 조회
select  user_id, count(business_num) from business_member a
    right join (select * from member where user_grade = 'business') using (user_id)
    group by user_id;

--상품 미등록 사업자 회원 조회
select user_id
    from (select user_id, count(user_id) count from member left join business_member using(user_id) where  user_grade='business' group by user_id)
    where count = 1;
