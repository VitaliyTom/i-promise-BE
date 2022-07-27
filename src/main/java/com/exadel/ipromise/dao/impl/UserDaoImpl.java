package com.exadel.ipromise.dao.impl;

import com.exadel.ipromise.dao.UserDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.exadel.ipromise.entity.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final JdbcTemplate jdbcTemplate;

    String EMAIL = " email";
    String VALUES = " VALUES (?,?,?)";
    String NICKNAME_EMAIL_PASSWORD = " (nickname, email, password)";
    String INSERT_INTO = "INSERT INTO";
    String EXISTS = " EXISTS";
    String SELECT = "SELECT";
    String SELECT_ALL = "SELECT * FROM";
    String WHERE = " WHERE";
    String JPA_USERS = " jpa.users";
    String USER_ID = " user_id";
    String QUESTION_MARK = " = ?";
    String UPDATE = "UPDATE";
    String SET = " SET";

    StringBuilder updateUser = new StringBuilder(UPDATE)
            .append(JPA_USERS)
            .append(SET)
            .append("nickname = ?, email = ?, password = ?")
            .append(WHERE)
            .append(USER_ID)
            .append(QUESTION_MARK);

    StringBuilder create = new StringBuilder(INSERT_INTO)
            .append(JPA_USERS)
            .append(NICKNAME_EMAIL_PASSWORD)
            .append(VALUES);

    StringBuilder searchUserByEmail = new StringBuilder(SELECT)
            .append(EXISTS)
            .append("(")
            .append(SELECT_ALL)
            .append(JPA_USERS)
            .append(WHERE)
            .append(EMAIL)
            .append(QUESTION_MARK)
            .append(")");

    StringBuilder checkUserByEmail = new StringBuilder(SELECT_ALL)
            .append(JPA_USERS)
            .append(WHERE)
            .append(EMAIL)
            .append(QUESTION_MARK);

    StringBuilder searchById = new StringBuilder(SELECT_ALL).
            append(JPA_USERS)
            .append(WHERE)
            .append(USER_ID)
            .append(QUESTION_MARK);

    StringBuilder findAll = new StringBuilder(SELECT_ALL)
            .append(JPA_USERS);

    @Override
    public void create(User user) {
        jdbcTemplate.update(create.toString(), user.getNickname(), user.getEmail(), user.getPassword());
    }

    @Override
    public Boolean checkUserByEmail(User user) {
        return jdbcTemplate.queryForObject(searchUserByEmail.toString(), Boolean.class, user.getEmail());
    }

    @Override
    public User getUserByEmail(User user) {
        User userEntity = jdbcTemplate.queryForObject(checkUserByEmail.toString(), new BeanPropertyRowMapper<>(User.class), user.getEmail());
        assert userEntity != null;
        return userEntity;
    }

    @Override
    public User getUserById(int id) {
        User user = jdbcTemplate.queryForObject(searchById.toString(), new BeanPropertyRowMapper<>(User.class), id);
        assert user != null;
        return user;
    }

    public List<User> findAll() {
        return jdbcTemplate.query(findAll.toString(), new BeanPropertyRowMapper<>(User.class));
    }

    public int update(User user) {
        return jdbcTemplate.update(updateUser.toString(), user.getNickname(),
                user.getEmail(),
                user.getPassword(),
                user.getUserId());
    }
}