package org.example.strategy_refac;

class DeleteAllStrategy implements StatementStrategy {
    public void run(Database db) {
        db.getUsers().clear();
        System.out.println("  [전략-별도클래스] 전체 삭제");
    }
}
