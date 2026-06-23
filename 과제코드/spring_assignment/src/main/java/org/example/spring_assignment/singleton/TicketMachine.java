package org.example.spring_assignment.singleton;

public class TicketMachine {
    private static final TicketMachine instance = new TicketMachine();
    private int lastNumber = 0;
    private TicketMachine() {}
    public static synchronized TicketMachine getInstance() { return instance; }
    int issue() { lastNumber++; return lastNumber; }
    int peek() { return lastNumber; }
    void reset() { lastNumber = 0; }
}
