package org.example.spring_assignment.ioc;

public class CoffeeMaker {
    private Bean bean;
    public CoffeeMaker(Bean bean) {
        this.bean = bean;
    }
    public void setBean(Bean bean) {
        this.bean = bean;
    }
    void brew() {
        System.out.println(bean.name() + "로 커피를 내립니다");
    }
}
