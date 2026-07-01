package org.example.spring_theory.ch06.ex_6_3;

// * 핵심 구성
//   - DefaultAdvisorAutoProxyCreator (빈 후처리기):
//       컨테이너의 모든 Advisor를 모아두고, 빈이 만들어질 때마다 그 빈이 advisor의
//       Pointcut에 걸리는지 검사한다. 걸리면 자동으로 프록시로 바꿔 등록한다.
//   - Advisor(Pointcut + Advice)는 빈으로 등록만 해두면 자동으로 수집된다.
//   - target 빈(userService=UserServiceImpl)은 ProxyFactoryBean 없이 '평범하게' 등록.

//   => "어떤 빈을 프록시로 만들지"를 우리가 빈마다 지정하지 않는다.
//      Pointcut 조건에 맞는 빈은 '전부' 자동으로 프록시가 적용된다.
//      서비스가 10개든 100개든 advisor 하나면 끝(부가기능 적용의 완전 자동화).
public class Start {
    public static void main(String[] args) {

    }
}
