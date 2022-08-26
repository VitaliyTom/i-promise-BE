package com.exadel.ipromise.dao.impl;

import com.exadel.ipromise.dao.PromiseDao;
import com.exadel.ipromise.entity.Promise;
import com.exadel.ipromise.extractors.ExtractorPromise;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.*;

@Repository
public class PromiseDaoImpl implements PromiseDao {

    private final JdbcTemplate jdbcTemplate;

    private final String CREATE = "INSERT INTO jpa.promises (user_id, addiction_id, start_date_stamp, amount_days) VALUES(?, ?, ?, ?)";
    private final String UPDATE = "UPDATE jpa.promises SET amount_days = ? WHERE promise_id = ?";
    private final String DELETE = "DELETE FROM jpa.promises WHERE promise_id = ?";
    private final String GET_PROMISES_BY_ID_USER = "SELECT * FROM jpa.promises AS PR " +
            "INNER JOIN jpa.addictions AS ADCT on ADCT.addiction_id = PR.addiction_id " +
            "INNER JOIN jpa.reasons AS RS on PR.promise_id = RS.promise_id " +
            "WHERE user_id = ?";


    public PromiseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long addPromise(Promise promise) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, new String[]{"promise_id"});
            preparedStatement.setLong(1, promise.getUserId());
            preparedStatement.setLong(2, promise.getAddictionId());
            preparedStatement.setLong(3, promise.getStartDateStamp());
            preparedStatement.setLong(4, promise.getAmountDays());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public List<Promise> getPromisesByIdUser(Long userId) {
        return jdbcTemplate.query(GET_PROMISES_BY_ID_USER, new ExtractorPromise(), userId);
    }

    @Override
    public int update(Promise promise) {
        return jdbcTemplate.update(UPDATE, promise.getAmountDays(), promise.getPromiseId());
    }

    @Override
    public int delete(Long promiseId) {
        return jdbcTemplate.update(DELETE, promiseId);
    }
}
