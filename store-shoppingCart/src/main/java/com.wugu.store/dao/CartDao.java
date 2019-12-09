package com.wugu.store.dao;

import com.wugu.store.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class CartDao {

    private final static String insertMessageSql = "insert into message values (?, ?, ?, ?, ?, ?)";
    private final static String deleteMessageSql = "delete from message where messageid = ?";
    private final static String selectMessageSql = "select * from message where userName = ? and status = 1";

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

    @Transactional
    public boolean add(String userName, String phoneName) {
        Message message = new Message();
        UUID messageID = UUID.randomUUID();
        message.setMessageID(messageID);
        message.setUserName(userName);
        message.setPhoneName(phoneName);
        message.setPrice(phoneDao.select(phoneName).getPhonePrice());
        message.setNumber(1);
        message.setState(1);
        return jdbcTemplate.update(insertMessageSql, message.getMessageID(), message.getUserName(), message.getPhoneName(), message.getState()) == 1;
    }

    @Transactional
    public boolean delete(String messageID) {
        return jdbcTemplate.update(deleteMessageSql, messageID) == 1;
    }

    public List<Message> select(String userName) {
        return jdbcTemplate.queryForList(selectMessageSql, new Object[]{userName}, Message.class);
    }
}
