package org.example.decorator;

public class RateLimitNotificationSender implements NotificationSender{
    private final NotificationSender delegate;
    private final long interval = 1000;
    public RateLimitNotificationSender(NotificationSender delegate) {
        this.delegate = delegate;
    }

    private long lastSent = 0;

    @Override
    public synchronized void send(String to, String message) {
        while (System.currentTimeMillis() - lastSent < interval) {
            System.out.println("대기중...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        lastSent = System.currentTimeMillis();
        delegate.send(to, message);
    }
}
