package org.example.spring_assignment.ioc;

public class Main {
    static void main() {
        System.out.println("===== 2. DI: 제어를 바깥(main)으로 =====");
        new CoffeeMaker(new ColombiaBean()).brew();
        new CoffeeMaker(new EthiopiaBean()).brew();

        System.out.println("\n===== 3. IoC 컨테이너: 조립까지 위임 =====");
        CoffeeMaker maker = new CoffeeContainer().getCoffeeMaker();
        maker.brew();

        System.out.println("\n===== 4. 헐리우드 원칙 =====");
        Button button = new Button();
        button.setListener(new LikeAction());   // 끼워두기만
        button.press();                         // 호출은 Button이
        System.out.println();
        System.out.println();

        button.addListener(new LikeAction());
        button.addListener(new LikeAction());
        button.addListener(new LikeAction());
        button.addListener(new LikeAction());
        button.pressAll();
    }
}

/*
    @Component
    - Component annotation은 객체를 만들어 컨테이너에 등록한다.
    - Spring은 애플리케이션이 시작될 때 @Component가 붙은 클래스를 탐색해 Spring 컨테이너에 저장한다.
    - 저장된 객체를 빈(bean)이라고 하며 Spring 컨테이너가 생명주기를 관리한다.

    @Autowired
    - Autowired annotation은 필요한 객체를 찾아 연결한다.
    - 객체 생성 시 생성자의 파라미터를 확인해 필요한 객체가 있는지 확인한다.
    - 있을 경우 컨테이너에서 알맞은 타입의 객체를 검색해 할당한다.
 */
