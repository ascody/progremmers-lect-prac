package org.example.decorator;

public class TimingNotificationSender implements NotificationSender {
    private final NotificationSender delegate;

    public TimingNotificationSender(NotificationSender delegate) {
        this.delegate = delegate;
    }

    @Override
    public void send(String to, String message) {
        long l = System.currentTimeMillis();
        delegate.send(to, message);
        long l1 = System.currentTimeMillis();
        System.out.println("걸린 시간: " + (l1 - l) + "ms");
    }
}
