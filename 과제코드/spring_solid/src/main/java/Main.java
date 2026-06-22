public class Main {
    static void main() {
        // SRP
        System.out.println("===== SRP: 단일 책임 =====");
        Journal journal = new Journal();
        journal.add("오늘은 자바를 배웠다");
        journal.add("SOLID는 어렵지만 재밌다");
        JournalSaver saver = new JournalSaver(journal);
        saver.print();

        // OCP
        System.out.println("===== OCP: 개방-폐쇄 =====");
        DiscountPolicy[] policies = { new BasicDiscount(), new GoldDiscount(), new VipDiscount() };
        String[] grade = {"일반", "골드", "VIP"};
        for (int i = 0; i<policies.length; i++) {
            System.out.println(grade[i] + ": " + policies[i].discount(policies[i].discount(1000)) + "원");
        }
        System.out.println();

        // LSP
        System.out.println("===== LSP: 리스코프 치환 =====");
        Bird[] birds = { new Sparrow(), new Penguin() };
        for (Bird bird : birds) {
            bird.eat();
        }
        System.out.println();

        // ISP
        System.out.println("===== ISP: 인터페이스 분리 =====");
        SimplePrinter simplePrinter = new SimplePrinter();
        SmartMachine smartMachine = new SmartMachine();
        simplePrinter.print();
        smartMachine.print();
        smartMachine.scan();
        System.out.println();

        // DIP
        System.out.println("===== DIP: 의존관계 역전 =====");
        new NotificationService(new EmailSender()).notifyUser("주문이 완료되었습니다.");
        new NotificationService(new SmsSender()).notifyUser("주문이 완료되었습니다.");
    }
}
