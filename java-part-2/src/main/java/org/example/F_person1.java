package org.example;

public class F_person1 {
    String name;
    int age;

    public F_person1() {
        System.out.println("기본 생성자");
        name = "Paul";
        age = 20;
    }

    public void display() {
        System.out.println(name + " " + age);
    }
}
