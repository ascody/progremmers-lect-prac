package org.example.strategy_refac;

class AddStrategy implements StatementStrategy {
    private final User user;                 // ← 캡처를 못 하니 필드에 보관
    AddStrategy(User user) { this.user = user; }
    public void run(Database db) {
        db.getUsers().add(user);
        System.out.println("  [전략-별도클래스] 추가: " + user.getName());
    }
}
