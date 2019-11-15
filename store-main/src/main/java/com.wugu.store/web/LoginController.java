package com.wugu.store.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        System.out.println(request.getSession().getId().toString());
        return "hello";
    }
}
