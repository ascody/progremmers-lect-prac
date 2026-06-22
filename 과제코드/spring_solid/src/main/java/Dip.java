interface MessengerSender {
    void send(String msg);
}
class EmailSender implements MessengerSender {
    public void send(String msg) {
        System.out.println("이메일: " + msg);
    }
}
class SmsSender implements MessengerSender {
    public void send(String msg) {
        System.out.println("SMS: " + msg);
    }
}
class NotificationService {
    MessengerSender sender;
    NotificationService(MessengerSender sender) {
        this.sender = sender;
    }
    void notifyUser(String msg) { sender.send(msg); }
}
