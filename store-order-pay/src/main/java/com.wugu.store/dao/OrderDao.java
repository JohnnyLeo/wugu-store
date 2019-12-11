package com.wugu.store.dao;

import com.wugu.store.domain.Message;
import com.wugu.store.domain.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class OrderDao {

    private final static String selectOrderSql = "select * from message where message.username = ? and message.status = 2";
    private final static String payOrderSql = "update message set status = 3 where username = ? and status = 2";
    private final static String addOrderSql = "insert into message values (?, ?, ?, ?, ?, ?)";

    private JdbcTemplate jdbcTemplate;
    private PhoneDao phoneDao;

    @Autowired
    public void setPhoneDao(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Message> select(String userName) {
        return jdbcTemplate.query(selectOrderSql, new Object[]{userName}, new BeanPropertyRowMapper<>(Message.class));
    }

    public boolean pay(String userName) {
        return jdbcTemplate.update(payOrderSql, userName) != 0;
    }

    public boolean add(String userName, String phoneName) {
        Phone phone = phoneDao.select(phoneName);
        return jdbcTemplate.update(addOrderSql, UUID.randomUUID().toString(),
                userName,
                phoneName,
                phone.getPhonePrice(),
                1,
                2) == 1;
    }
}
