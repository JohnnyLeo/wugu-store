package com.wugu.store.service;

import com.wugu.store.dao.UserDao;
import com.wugu.store.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean doRegister(String userName, String passWord) {
        return userDao.insertUser(userName, passWord);
    }

    public boolean userNameVerify(String userName) {
        return userDao.selectUserName(userName);
    }
}
