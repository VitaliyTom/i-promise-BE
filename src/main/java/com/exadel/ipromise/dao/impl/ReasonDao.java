package com.exadel.ipromise.dao.impl;

import com.exadel.ipromise.entity.Reason;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReasonDao {

    public ReasonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final JdbcTemplate jdbcTemplate;

    public int createReason(Reason reason) {
        String addReason = "INSERT INTO jpa.reasons (promise_id, reason) VALUES (?, ?)";
        return jdbcTemplate.update(addReason, reason.getPromiseId(), reason.getReason());
    }
}
