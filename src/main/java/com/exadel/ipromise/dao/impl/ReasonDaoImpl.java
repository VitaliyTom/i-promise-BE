package com.exadel.ipromise.dao.impl;

import com.exadel.ipromise.dao.ReasonDao;
import com.exadel.ipromise.entity.Reason;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReasonDaoImpl implements ReasonDao {

    private final JdbcTemplate jdbcTemplate;

    private final String CREATE = "INSERT INTO jpa.reasons (promise_id, reason) VALUES (?, ?)";

    public ReasonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Reason reason) {
        return jdbcTemplate.update(CREATE, reason.getPromiseId(), reason.getReason());
    }
}
