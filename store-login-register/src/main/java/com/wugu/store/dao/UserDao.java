package com.wugu.store.dao;

import com.wugu.store.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    private final static String selectUserSql = "select * from user where username = ? and password = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User selectUser(String username, String password) {
        return jdbcTemplate.queryForObject(selectUserSql, new Object[]{username, password}, User.class);
    }
}
