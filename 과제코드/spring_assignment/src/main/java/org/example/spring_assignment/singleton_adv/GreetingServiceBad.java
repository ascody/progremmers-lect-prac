package org.example.spring_assignment.singleton_adv;

public class GreetingServiceBad {
    private static final GreetingServiceBad instance = new GreetingServiceBad();
    private GreetingServiceBad() {}
    public static GreetingServiceBad getInstance() { return instance; }

    private String name;

    String greet(String reqName) {
        this.name = reqName;
        try { Thread.sleep(5); } catch (InterruptedException e) {}
        return this.name;
    }
}
