package org.example.strategy_refac;

public class Main {
    static void main() {

        System.out.println("== (별도 클래스) deleteAll ==");
        Database db = new Database();
        UserDao dao = new UserDao(db);

        dao.deleteAll();
        System.out.println();

        System.out.println("== (익명 클래스) add(김) ==");
        dao.add(new User("u1", "김"));
        System.out.println();

        System.out.println("== (람다) add(이) ==");
        dao.add(new User("u2", "이"));

        System.out.println("\n현재 사용자 수: " + db.getUsers().size());
        for (User u : db.getUsers()) System.out.println("사용자: " + u.getName());
    }
}
