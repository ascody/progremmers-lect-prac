package com.flight.game.set;

import java.util.Scanner;

public class User {

    private String name;

    public void init() {

        Scanner sc = new Scanner( System.in );
        System.out.print("이름 : ");
        String name = sc.nextLine();
        setName( name );

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
