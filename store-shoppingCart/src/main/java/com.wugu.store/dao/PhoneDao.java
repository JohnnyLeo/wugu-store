package com.wugu.store.dao;

import com.wugu.store.domain.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneDao {
    private final static String selectPhoneSql = "select * from phone where phonename = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Phone select(String phoneName) {
        return jdbcTemplate.queryForObject(selectPhoneSql, new Object[]{phoneName}, Phone.class);
    }
}
