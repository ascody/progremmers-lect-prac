package org.example.strategy_refac;

@FunctionalInterface
interface StatementStrategy {
    void run(Database db);
}