package com.wugu.store.web;

import com.wugu.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    private String loginLocation = "http://localhost:8050/login.html";

    private CartService cartService;

    @Autowired
    public void setService(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(value = "/add")
    public boolean add(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (cartService.add(cookies, request.getParameter("phoneName"))) {
            return true;
        } else {
            try {
                response.sendRedirect(loginLocation);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
