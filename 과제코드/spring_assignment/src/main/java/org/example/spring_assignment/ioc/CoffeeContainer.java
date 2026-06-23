package org.example.spring_assignment.ioc;

public class CoffeeContainer {
    public static final CoffeeMaker coffeeMaker = new CoffeeMaker(null);
    CoffeeMaker getCoffeeMaker() {
        coffeeMaker.setBean(new ColombiaBean());
        return coffeeMaker;
    }
    CoffeeMaker getCoffeeMaker(String type) {
        if (type.equals("colombia")) coffeeMaker.setBean(new ColombiaBean());
        else if (type.equals("ethiopia")) coffeeMaker.setBean(new EthiopiaBean());
        return coffeeMaker;
    }
}
