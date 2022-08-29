package com.exadel.ipromise.dao.impl;

import com.exadel.ipromise.dao.AddictionDao;

import com.exadel.ipromise.entity.Addiction;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddictionDaoImpl implements AddictionDao {

    private final JdbcTemplate jdbcTemplate;

    private final String GET_ALL_ADDICTIONS = "SELECT * FROM jpa.addictions ORDER BY addiction_id";

    public AddictionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Addiction> getAddictions() {
        return jdbcTemplate.query(GET_ALL_ADDICTIONS, new BeanPropertyRowMapper<>(Addiction.class));
    }
}
