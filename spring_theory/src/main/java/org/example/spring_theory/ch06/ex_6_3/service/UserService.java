package org.example.spring_theory.ch06.ex_6_3.service;

import org.example.spring_theory.ch06.ex_6_3.domain.User;

import java.sql.SQLException;

public interface UserService {
    void add(User user) throws SQLException, ClassNotFoundException;
    void upgradeLevels();
}
