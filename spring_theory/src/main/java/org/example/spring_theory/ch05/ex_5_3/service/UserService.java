package org.example.spring_theory.ch05.ex_5_3.service;

import org.example.spring_theory.ch05.ex_5_3.domain.User;

import java.sql.SQLException;

public interface UserService {
    void add(User user) throws SQLException, ClassNotFoundException;
    void upgradeLevels();
}
