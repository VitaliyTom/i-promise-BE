package com.exadel.ipromise.dao.impl;

import com.exadel.ipromise.entity.Promise;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class PromiseDao {

    private final JdbcTemplate jdbcTemplate;

    String CREATE = "INSERT INTO jpa.promises (user_id, addiction_id, start_date_stamp, amount_days) VALUES(?, ?, ?, ?)";
    String GET_BY_ID_USER = "SELECT * FROM jpa.promises AS PR INNER JOIN jpa.addictions AS ADCT on ADCT.addiction_id = PR.addiction_id WHERE user_id = ?";

    public PromiseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long create(Promise promise) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, new String[]{"promise_id"});
            preparedStatement.setInt(1, promise.getUserId());
            preparedStatement.setInt(2, promise.getAddictionId());
            preparedStatement.setInt(3, promise.getStartDateStamp());
            preparedStatement.setInt(4, promise.getAmountDays());
            return preparedStatement;
        }, keyHolder);
        return  (Long) keyHolder.getKey();
    }

    public List<Promise> getByIdUser(int userId) {
        return jdbcTemplate.query(GET_BY_ID_USER, new BeanPropertyRowMapper<>(Promise.class), userId);
    }
}
