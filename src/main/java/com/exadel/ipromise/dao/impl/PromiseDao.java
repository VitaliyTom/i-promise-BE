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

    public PromiseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final JdbcTemplate jdbcTemplate;

    public List<Promise> findAll() {
        List<Promise> promises = jdbcTemplate.query("SELECT * FROM jpa.promises", new BeanPropertyRowMapper<>(Promise.class));
        return promises;
    }

    public List<Promise> findByIdUser(int userId) {
        String sql = "SELECT * FROM jpa.promises AS PR INNER JOIN jpa.addictions AS ADCT on ADCT.addiction_id = PR.addiction_id WHERE user_id = ?";
        List<Promise> promises = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Promise.class), userId);
        return promises;
    }

    public Number create(Promise promise) {
        String addPromise = "INSERT INTO jpa.promises (user_id, addiction_id, start_date_stamp, amount_days) VALUES(?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(addPromise, new String[]{"promise_id"});
            preparedStatement.setInt(1, promise.getUserId());
            preparedStatement.setInt(2, promise.getAddictionId());
            preparedStatement.setInt(3, promise.getStartDateStamp());
            preparedStatement.setInt(4, promise.getAmountDays());
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey();
    }
}
