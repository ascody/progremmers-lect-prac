package org.example.spring_assignment.singleton;

public class Main {
    static void main() {

        System.out.println("===== 1. 싱글톤 없이: 번호표 두 대 (버그!) =====");
        NaiveTicketMachine a = new NaiveTicketMachine();
        NaiveTicketMachine b = new NaiveTicketMachine();
        System.out.println("A 기계가 발급: " + a.issue() + "번");
        System.out.println("B 기계가 발급: " + b.issue() + "번");
        System.out.println("A 기계가 발급: " + a.issue() + "번");
        System.out.println("B 기계가 발급: " + b.issue() + "번");

        System.out.println("===== 2. 싱글톤 적용: 번호표는 하나뿐 =====");
        TicketMachine w1 = TicketMachine.getInstance();
        TicketMachine w2 = TicketMachine.getInstance();
        TicketMachine w3 = TicketMachine.getInstance();
        System.out.println("1번 창구가 발급: " + w1.issue() + "번");
        System.out.println("2번 창구가 발급: " + w2.issue() + "번");
        System.out.println("1번 창구가 발급: " + w1.issue() + "번");
        System.out.println("3번 창구가 발급: " + w3.issue() + "번");
        System.out.println("같은 기계인가? " + (w1 == w2));

        System.out.println("===== 3. lazy 초기화 (설정 관리자) =====");
        Settings s1 = Settings.getInstance();
        s1.setTheme("dark");
        System.out.println("앱 설정 - 테마: " + Settings.getInstance().getTheme());
        System.out.println("앱 설정 - 테마: " + s1.getTheme());

        System.out.println();
        w1.reset();
        System.out.println("1번 창구에서 리셋: " + w1.peek() + "번");
        System.out.println("2번 창구가 발급: " + w2.issue() + "번");
        System.out.println("3번 창구가 발급: " + w3.issue() + "번");

        System.out.println();

    }
}

/*
    static synchronized 메서드
    - synchronized가 없는 경우 두 스레드가 동시에 호출 시 싱글톤 객체가 두 번 생성될 수 있음
    - static synchronized 메서드는 한 번에 하나의 스레드만 실행 가능

    enum singleton
    - enum 싱글톤은 JVM이 인스턴스를 하나만 생성하도록 보장하므로 별도의 synchronized가 필요하지 않음
    - enum 내부의 인스턴스의 경우 enum이 처음 호출될 때 초기화됨 (lazy 효과)
    - 여러 스레드가 동시에 접근하더라도 가장 먼저 접근한 스레드가 초기화하고, 나머지 스레드는 이미 생성된 인스턴스에 접근함
    - 하지만 싱글톤 내부에 변경 가능한 상태가 있다면 별도의 동기화가 필요함 (synchronized, AtomicInteger 등)
    - 클래스를 상속받을 수는 없지만 인터페이스 구현은 가능함
    - 예시)
            public enum DatabaseManager {
                INSTANCE;

                private String connectionUrl;

                public void connect(String url) {
                    this.connectionUrl = url;
                    System.out.println(url + "에 연결");
                }

                public String getConnectionUrl() {
                    return connectionUrl;
                }
            }

    싱글톤의 단점
    * 전역 상태가 늘어남
        - 싱글톤 인스턴스는 애플리케이션 여러 곳에서 공유되므로 전역 변수처럼 사용될 수 있다.
        - 여러 객체가 같은 상태를 변경하면 어디에서 값이 바뀌었는지 추적하기 어려워진다.
    * 의존 관계가 숨겨짐
        - 클래스 내부에서 Singleton.INSTANCE나 getInstance()를 직접 호출하면 해당 클래스가 싱글톤에 의존한다는 사실이 외부에 명확하게 드러나지 않는다.
        - 클래스 간 결합도가 높아지고 구현을 변경하기 어려워질 수 있다.
    * 테스트하기 어려움
        - 테스트가 끝난 뒤에도 싱글톤의 상태가 남아 다른 테스트에 영향을 줄 수 있다.
        - 실제 싱글톤 객체를 Mock이나 Stub 같은 테스트용 객체로 교체하기 어렵다.
        - 테스트 실행 순서에 따라 결과가 달라질 가능성이 있다.
    * 동시성 문제가 발생할 수 있음
        - 싱글톤 객체의 생성은 스레드 안전하게 처리할 수 있지만, 내부의 변경 가능한 상태까지 자동으로 스레드 안전해지는 것은 아니다.
        - 여러 스레드가 같은 데이터를 변경한다면 별도의 동기화가 필요하다.
    * IoC/DI를 통한 단점 완화
        - 객체가 필요한 의존성을 직접 생성하거나 싱글톤에서 가져오지 않고 외부 컨테이너로부터 주입받는다.
        - 생성자 등을 통해 의존 관계가 명확하게 드러난다.
        - 테스트에서는 실제 구현 대신 Mock이나 Stub을 쉽게 주입할 수 있다.
        - 객체의 생성과 생명주기를 컨테이너가 관리하므로 전역 접근을 줄이면서도 하나의 인스턴스를 공유할 수 있다.
 */