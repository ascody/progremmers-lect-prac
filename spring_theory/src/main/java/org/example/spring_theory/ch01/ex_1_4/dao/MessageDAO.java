package org.example.spring_theory.ch01.ex_1_4.dao;

public class MessageDAO {
    private SimpleConnectionMaker simpleConnectionMaker;

    public MessageDAO(SimpleConnectionMaker simpleConnectionMaker) {
        this.simpleConnectionMaker = simpleConnectionMaker;
    }
}