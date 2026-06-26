package org.example.strategy_refac;

public class UserDao {
    private Database db;
    UserDao(Database db) { this.db = db; }

    void context(StatementStrategy strategy) {
        db.open();                  // 공통: 자원 열기
        strategy.run(db);           // 변하는 부분을 전략에 위임
        db.close();                 // 공통: 자원 정리
    }

    void deleteAll() {
//        context(new DeleteAllStrategy());

//        StatementStrategy strategy = new DeleteAllStrategy() {
//            @Override
//            public void run(Database db) {
//                db.getUsers().clear();
//                System.out.println("  [전략-별도클래스] 전체 삭제");
//            }
//        };
//        context(strategy);

        context(db -> {
            db.getUsers().clear();
            System.out.println("  [전략-별도클래스] 전체 삭제");
        });
    }

    void add(User user) {
//        context(new AddStrategy(user));

//        StatementStrategy strategy = new DeleteAllStrategy() {
//            @Override
//            public void run(Database db) {
//                db.getUsers().add(user);
//                System.out.println("  [전략-별도클래스] 추가: " + user.getName());
//            }
//        };
//        context(strategy);

        context(db -> {
            db.getUsers().add(user);
            System.out.println("  [전략-별도클래스] 추가: " + user.getName());
        });
    }
}