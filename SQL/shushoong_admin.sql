select * from member where user_id like '%e%';

SELECT USER_ID, USER_NAME, USER_GRADE FROM MEMBER WHERE NOT USER_GRADE='admin' and USER_ID LIKE '%e%';