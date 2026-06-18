public class Main {
    static void main() {
        Chat chat = new Chat();
        QuestionThread qt1 = new QuestionThread(chat);
        QuestionThread qt2 = new QuestionThread(chat);
        AnswerThread at1 = new AnswerThread(chat);
        AnswerThread at2 = new AnswerThread(chat);
        qt1.start();
        at1.start();
        qt2.start();
        at2.start();
    }
}
