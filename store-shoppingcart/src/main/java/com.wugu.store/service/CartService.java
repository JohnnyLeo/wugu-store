package com.wugu.store.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.io.IOException;

@Service
public class CartService {

    public boolean add(Cookie[] cookies) {
        if (cookies == null) {
            return false;
        } else {
            for (Cookie cookie : cookies) {
                if ("sessionID".equals(cookie.getName())) {

                }
            }
            return false;
        }
    }

}
