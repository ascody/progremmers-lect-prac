package org.example.spring_theory.ch01.ex_1_3.dao;

import org.example.spring_theory.ch01.ex_1_1.domain.User;
import org.example.spring_theory.ch01.ex_1_2.dao.DConnectionMaker_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// * 문제점
// UserDAO의 다른 모든 곳에서는 인터페이스를 이용하게 만들어서
// DB커넥션을 제공하는 클래스에 대한 구체적인 정보는 모두 제거했지만,
// 초기에 한 번 어떤 클래스의 오브젝트를 사용할지를 결정하는 모든 생성자의 코드는 제거되지 않고 남아 있다.
// 여전히 UserDAO 소스코드를 함께 제공해서,
// 필요할 때마다  UserDAO의 생성자 메서드를 직접 수정하라고 하지 않고는
// 고객에게 자유로운 DB커넥션 확장 기능을 가진 UserDAO를 제공할 수 없다.

// "관계설정 책임의 분리"

public class UserDAO {

    private SimpleConnectionMaker simpleConnectionMaker;

    public UserDAO(SimpleConnectionMaker simpleConnectionMaker) {
        this.simpleConnectionMaker = simpleConnectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        String query = "INSERT INTO users (id, name, password) VALUES (?, ?, ?)";

        try (
                Connection conn = simpleConnectionMaker.makeNewConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
        ) {
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());
            pstmt.executeUpdate();
        }

    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM users WHERE id = ?";

        try (
                Connection conn = simpleConnectionMaker.makeNewConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
        ) {
            pstmt.setString(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            resultSet.next();

            User user = new User();
            user.setId( resultSet.getString("id") );
            user.setName( resultSet.getString("name") );
            user.setPassword( resultSet.getString("password") );

            return user;
        }
    }
}