package com.exadel.ipromise.dao.impl;

import com.exadel.ipromise.dao.UserDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.exadel.ipromise.entity.User;

import java.sql.PreparedStatement;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    String CREATE = "INSERT INTO jpa.users (nick_name, email, password) VALUES(?, ?, ?)";
    String UPDATE = "UPDATE jpa.users SET nick_name = ?, email = ?, password = ? WHERE user_id = ?";
    String GET_BY_ID = "SELECT * FROM jpa.users WHERE user_id = ?";
    String GET_BY_EMAIL = "SELECT * FROM jpa.users WHERE email = ?";
    String CHECK_IF_USER_EXISTS_BY_EMAIL = "SELECT EXISTS (SELECT * FROM jpa.users WHERE email = ?)";

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer create(User user) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, new String[]{"user_id"});
            preparedStatement.setString(1, user.getNickName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            return preparedStatement;
        }, keyHolder);
        return  (Integer) keyHolder.getKey();
    }

    public int update(User user) {
        return jdbcTemplate.update(UPDATE, user.getNickName(),
                user.getEmail(),
                user.getPassword(),
                user.getUserId());
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject(GET_BY_ID, new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.queryForObject(GET_BY_EMAIL, new BeanPropertyRowMapper<>(User.class), email);
    }

    @Override
    public Boolean checkIfUserExistsByEmail(String email) {
        return jdbcTemplate.queryForObject(CHECK_IF_USER_EXISTS_BY_EMAIL, Boolean.class, email);
    }

}