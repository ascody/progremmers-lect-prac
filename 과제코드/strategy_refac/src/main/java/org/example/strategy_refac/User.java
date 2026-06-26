package org.example.strategy_refac;

public class User {
    String id;
    String name;

    User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
