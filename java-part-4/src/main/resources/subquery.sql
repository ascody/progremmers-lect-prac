# 서브쿼리: SQL문을 실행하는 데 필요한 데이터를 추가로 조회하기 위해, SQL문 내부에서 사용하는 SELECT문을 의미한다.\
USE scott;

-- 1. 사원 이름이 JONES인 사원의 급여 출력
SELECT SAL
FROM EMP
WHERE ENAME = 'JONES';
-- 2. 급여가 2975보다 높은 사원 정보 출력
SELECT ENAME
FROM EMP
WHERE SAL > 2975;
-- 1 + 2. JONES의 급여보다 높은 급여를 받는 사원 출력
--   → 2975라는 값을 직접 쓰는 대신, 1번 쿼리를 서브쿼리로 넣는다
SELECT *
FROM EMP
WHERE SAL > (SELECT SAL FROM EMP WHERE ENAME = 'JONES');