package com.wugu.store.service;

import com.wugu.store.dao.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;

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

    public boolean add(Cookie[] cookies, String phoneName, String number) {
        if (cookies == null) {
            return false;
        } else {
            for (Cookie cookie : cookies) {
                if ("sessionID".equals(cookie.getName())) {
                    String sessionID = cookie.getValue();
                    String userName = restTemplate.postForObject("http://localhost:8280/session/getAttribute", sessionID, String.class);
                    if (userName != null) {
                        return cartDao.add(userName, phoneName, Integer.parseInt(number));
                    }
                }
            }
            return false;
        }
    }

}
