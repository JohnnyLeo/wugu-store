package com.wugu.store.dao;

import com.wugu.store.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDao {

    private final static String selectUserNameSql = "select * from user where username = ?";
    private final static String selectUserSql = "select * from user where username = ? and password = ?";
    private final static String insertUserSql = "insert into user values(?, ?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *
     * @param userName need to be unique
     * @return false:userName has not been used; true:userName has been used
     */
    @Transactional
    public boolean selectUserName(String userName) {
        List<User> userList = jdbcTemplate.query(selectUserNameSql, new Object[]{userName}, new BeanPropertyRowMapper<>(User.class));
        return userList != null && userList.size() > 0;
    }

    @Transactional
    public User selectUser(String userName, String passWord) {
        List<User> userList = jdbcTemplate.query(selectUserSql, new Object[]{userName, passWord}, new BeanPropertyRowMapper<>(User.class));
        if(null != userList && userList.size()>0){
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Transactional
    public boolean insertUser(String userName, String passWord) {
        User user = new User();
        user.setUserName(userName);
        user.setPassWord(passWord);
        return jdbcTemplate.update(insertUserSql, user.getUserName(), user.getPassWord()) == 1;
    }
}
