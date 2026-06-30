package org.example.decorator;

public class Main {
    static void main() {
        System.out.println("===== A, B =====");

        // A-3: 다형성과 생성자 주입으로 인해 가능
        NotificationService service1 = new NotificationService(new EmailNotificationSender());
        service1.notifyUser("홍길동", "Hello!");

        NotificationService service2 = new NotificationService(new SmsNotificationSender());
        service2.notifyUser("홍길동", "Hello!");

        NotificationService service3 = new NotificationService(new KakaoNotificationSender());
        service3.notifyUser("홍길동", "Hello!");


        System.out.println("\n===== C =====");
        // C-2
        NotificationSender service4 =
                new TimingNotificationSender(       // ③ 가장 바깥: 전체 소요 시간 측정
                        new LoggingNotificationSender(  // ② 로그 남기고
                                new RetryNotificationSender(// ① 실패하면 재시도하며
                                        new FlakyEmailSender())));// (실제 발송 대상)

        new NotificationService(service4).notifyUser("user@test.com", "안녕하세요");

        System.out.println("\n===== D =====");
        // D
        NotificationSender service5 =
                new CensorNotificationSender(
                        new RateLimitNotificationSender(
                                new TimingNotificationSender(
                                        new LoggingNotificationSender(
                                                new RetryNotificationSender(
                                                        new FlakyEmailSender())))));
        new NotificationService(service5).notifyUser("user@test.com", "안녕하세요");

        NotificationSender service6 =
                new CensorNotificationSender(
                    new TimingNotificationSender(
                            new RateLimitNotificationSender(
                                    new LoggingNotificationSender(
                                            new RetryNotificationSender(
                                                    new EmailNotificationSender())))));
        for (int i = 1; i <= 10; i++) {
            new NotificationService(service6).notifyUser("user@test" + i + ".com", "잘가요");
        }
    }
}
