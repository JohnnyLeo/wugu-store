package com.wugu.store.service;

import com.alibaba.fastjson.JSON;
import com.wugu.store.dao.CartDao;
import com.wugu.store.domain.Message;
import com.wugu.store.domain.WuguAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import java.util.List;

@Service
public class CartService {

    private RestTemplate restTemplate;
    private CartDao cartDao;

    @Autowired
    public void setCartDao(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean add(Cookie[] cookies, String phoneName) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionID".equals(cookie.getName())) {
                    String sessionID = cookie.getValue();
                    WuguAttribute wuguAttribute = new WuguAttribute();
                    wuguAttribute.setSessionID(sessionID);
                    wuguAttribute.setKey("userName");
                    String userName = restTemplate.postForObject("http://localhost:8280/session/getAttribute", wuguAttribute, String.class);
                    if (userName != null) {
                        return cartDao.add(userName, phoneName);
                    }
                }
            }
        }
        return false;
    }

    public boolean delete(String messageID) {
        return cartDao.delete(messageID);
    }

    public String select(Cookie[] cookies) {
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if ("sessionID".equals(cookie.getName())) {
                    String sessionID = cookie.getValue();
                    WuguAttribute wuguAttribute = new WuguAttribute();
                    wuguAttribute.setSessionID(sessionID);
                    wuguAttribute.setKey("userName");
                    String userName = restTemplate.postForObject("http://localhost:8280/session/getAttribute", wuguAttribute, String.class);
                    if (userName != null) {
                        List<Message> list = cartDao.select(userName);
                        if (list != null) {
                            return JSON.toJSONString(list);
                        }
                    }
                }
            }
        }
        return null;
    }
}
