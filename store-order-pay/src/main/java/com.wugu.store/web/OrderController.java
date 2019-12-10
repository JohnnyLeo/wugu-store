package com.wugu.store.web;

import com.wugu.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/pay")
    public boolean pay(HttpServletRequest request) {
        return orderService.pay(request.getCookies());
    }

    @RequestMapping(value = "/query")
    public void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        String content = orderService.select(cookies);
        if (content != null) {
            response.setContentType("text/json");
            System.out.println(content);
            response.getWriter().write(content);
        }
    }

    @RequestMapping(value = "/add")
    public boolean add(HttpServletRequest request) {
        return orderService.add(request.getCookies(), request.getParameter("phoneName"));
    }
}
