package org.example.spring_assignment.ioc;

public class ColombiaBean implements Bean {
    public String name;
    public ColombiaBean() {
        this.name = "콜롬비아 원두";
    }
    public String name() {
        return this.name;
    }
}
