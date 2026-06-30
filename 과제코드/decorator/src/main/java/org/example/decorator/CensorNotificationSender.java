package org.example.decorator;

public class CensorNotificationSender implements NotificationSender {
    private final NotificationSender delegate;
    private final String FORBIDDEN_WORD = "안녕";
    public CensorNotificationSender(NotificationSender delegate) {
        this.delegate = delegate;
    }



    @Override
    public void send(String to, String message) {
        if (message.contains(FORBIDDEN_WORD)) {
//            throw new IllegalArgumentException(
//                    "금칙어가 포함되어 있어 전송할 수 없습니다: " + FORBIDDEN_WORD
//            );
            System.out.println("금칙어가 포함되어 있어 전송할 수 없습니다: " + FORBIDDEN_WORD);
            return;
        }

        delegate.send(to, message);
    }
}
