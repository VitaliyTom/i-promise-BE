package com.exadel.ipromise.dao.impl;

import com.exadel.ipromise.dao.ReasonDao;
import com.exadel.ipromise.entity.Reason;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Objects;

@Repository
public class ReasonDaoImpl implements ReasonDao {

    private final JdbcTemplate jdbcTemplate;

    private final String CREATE = "INSERT INTO jpa.reasons (promise_id, reason) VALUES (?, ?)";
    private final String GET_REASON_BY_ID = "SELECT * FROM jpa.reasons WHERE reason_id = ?";
    private final String UPDATE = "UPDATE jpa.reasons SET promise_id = ?, reason = ? WHERE reason_id = ? RETURNING *";
    private final String DELETE = "DELETE FROM jpa.reasons WHERE reason_id = ?";

    public ReasonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long create(Reason reason) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, new String[]{"reason_id"});
            preparedStatement.setLong(1, reason.getPromiseId());
            preparedStatement.setString(2, reason.getReason());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Reason getReasonById(Long reasonId) {
        return jdbcTemplate.queryForObject(GET_REASON_BY_ID, new BeanPropertyRowMapper<>(Reason.class), reasonId);
    }

    @Override
    public Reason update(Reason reason) {
        return jdbcTemplate.queryForObject(UPDATE, new BeanPropertyRowMapper<>(Reason.class), reason.getPromiseId(), reason.getReason(), reason.getReasonId());
    }

    @Override
    public int delete(Long reasonId) {
        return jdbcTemplate.update(DELETE, reasonId);
    }

}
