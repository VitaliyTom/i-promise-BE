package com.exadel.ipromise.dao.impl;

import com.exadel.ipromise.entity.Reason;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReasonDao {

    private final JdbcTemplate jdbcTemplate;

    String CREATE = "INSERT INTO jpa.reasons (promise_id, reason) VALUES (?, ?)";

    public ReasonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int create(Reason reason) {
        return jdbcTemplate.update(CREATE, reason.getPromiseId(), reason.getReason());
    }
}
