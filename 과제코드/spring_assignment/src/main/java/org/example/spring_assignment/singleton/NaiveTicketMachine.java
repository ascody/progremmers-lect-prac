package org.example.spring_assignment.singleton;

public class NaiveTicketMachine {
    private int lastNumber = 0;
    int issue() { lastNumber++; return lastNumber; }
}
