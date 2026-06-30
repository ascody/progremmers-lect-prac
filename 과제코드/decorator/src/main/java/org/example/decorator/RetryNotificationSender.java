package org.example.decorator;

public class RetryNotificationSender implements NotificationSender {
    private final NotificationSender delegate;
    private final int maxAttempts = 3;
    RetryNotificationSender(NotificationSender delegate) {
        this.delegate = delegate;
    }
    @Override
    public void send(String to, String message) {
        RuntimeException exception = null;
        for (int i = 1; i <= maxAttempts; i++) {
            try {
                delegate.send(to, message);
                return;
            } catch (RuntimeException e) {
                exception = e;
                System.out.println("다시 전송 시도합니다. 재시도 횟수: " + i);
            }
        }
        throw new RuntimeException("전송에 실패했습니다.", exception);
    }
}
