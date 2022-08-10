package com.exadel.ipromise.dao.impl;

import com.exadel.ipromise.dao.ReasonDao;
import com.exadel.ipromise.entity.Reason;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class ReasonDaoImpl implements ReasonDao {

    private final JdbcTemplate jdbcTemplate;

    final String CREATE = "INSERT INTO jpa.reasons (promise_id, reason) VALUES (?, ?)";

    public ReasonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long create(Reason reason) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, new String[]{"reason_id"});
            preparedStatement.setString(1, reason.getPromiseId().toString());
            preparedStatement.setString(2, reason.getReason());
            return preparedStatement;
        }, keyHolder);

        return (Long) keyHolder.getKey();
    }
}
