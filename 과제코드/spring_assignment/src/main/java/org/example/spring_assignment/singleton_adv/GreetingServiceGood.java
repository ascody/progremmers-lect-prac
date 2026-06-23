package org.example.spring_assignment.singleton_adv;

public class GreetingServiceGood {
    private static final GreetingServiceGood instance = new GreetingServiceGood();
    private GreetingServiceGood() {}
    static GreetingServiceGood getInstance() { return instance; }

    String greet(String reqName) {
        try { Thread.sleep(5); } catch (InterruptedException e) {}
        return reqName;
    }
}
