package com.wugu.store.dao;

import com.wugu.store.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public class UserDao {

    private final static String selectUserNameSql = "select * from user where username = ?";
    private final static String selectUserSql = "select * from user where username = ? and password = ?";
    private final static String insertUserSql = "insert into user values(?, ?, ?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *
     * @param userName need to be unique
     * @return true:userName has not been used; false:userName has been used
     */
    @Transactional
    public boolean selectUserName(String userName) {
        return jdbcTemplate.queryForObject(selectUserNameSql, new Object[]{userName}, User.class) == null;
    }

    @Transactional
    public User selectUser(String userName, String passWord) {
        return jdbcTemplate.queryForObject(selectUserSql, new Object[]{userName, passWord}, User.class);
    }

    @Transactional
    public boolean insertUser(String userName, String passWord) {
        User user = new User();
        user.setUserID(UUID.randomUUID());
        user.setUserName(userName);
        user.setPassWord(passWord);
        return jdbcTemplate.update(insertUserSql, new Object[]{user.getUserID(), user.getUserName(), user.getPassWord()}) == 1;
    }
}
