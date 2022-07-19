package com.exadel.ipromise.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.exadel.ipromise.entity.User;

import java.util.List;

@Repository
public class UserJdbcDao {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        List<User> users = jdbcTemplate.query("SELECT * FROM jpa.users", new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    public int insert(User user) {
        String insertSql = "INSERT INTO jpa.users (nickname, email, promise_id) VALUES (?,?,?)";
        return jdbcTemplate.update(insertSql, user.getNickname(), user.getEmail(), user.getPromiseId());
    }

    public int update(User user) {
        String updateSql = "UPDATE jpa.users SET nickname = ?, email = ?, promise_id = ? WHERE user_id = ?";
        return jdbcTemplate.update(updateSql, user.getNickname(),
                user.getEmail(),
                user.getPromiseId(),
                user.getUserId());
    }
}