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

    private final String CREATE = "INSERT INTO jpa.users (nick_name, email, password) VALUES(?, ?, ?)";
    private final String UPDATE = "UPDATE jpa.users SET nick_name = ?, email = ?, password = ? WHERE user_id = ? RETURNING *";
    private final String GET_BY_ID = "SELECT * FROM jpa.users WHERE user_id = ?";
    private final String CHECK_IF_USER_EXISTS_BY_EMAIL = "SELECT EXISTS (SELECT * FROM jpa.users WHERE email = ?)";
    private final String GET_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM jpa.users WHERE email=? AND password=?";

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long create(User user) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, new String[]{"user_id"});
            preparedStatement.setString(1, user.getNickName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            return preparedStatement;
        }, keyHolder);

        return (Long) keyHolder.getKey();
    }

    @Override
    public User update(User user) {
        return jdbcTemplate.queryForObject(UPDATE, new BeanPropertyRowMapper<>(User.class), user.getNickName(), user.getEmail(), user.getPassword(), user.getUserId());
    }

    @Override
    public User getById(Long id) {
        return jdbcTemplate.queryForObject(GET_BY_ID, new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public Boolean checkIfUserExistsByEmail(String email) {
        return jdbcTemplate.queryForObject(CHECK_IF_USER_EXISTS_BY_EMAIL, Boolean.class, email);
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return jdbcTemplate.queryForObject(GET_USER_BY_EMAIL_AND_PASSWORD, new BeanPropertyRowMapper<>(User.class), email, password);
    }

}