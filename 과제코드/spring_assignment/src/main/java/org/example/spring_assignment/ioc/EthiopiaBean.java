package org.example.spring_assignment.ioc;

public class EthiopiaBean implements Bean {
    public String name;
    public EthiopiaBean() {
        this.name = "에티오피아 원두";
    }
    public String name() {
        return this.name;
    }
}
