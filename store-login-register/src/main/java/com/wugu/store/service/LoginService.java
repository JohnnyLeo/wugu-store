package com.wugu.store.service;

import com.wugu.store.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean doLogin(String userName, String passWord) {
        if (userDao.selectUser(userName, passWord) == null) {
            return false;
        } else {
            return true;
        }
    }

}
