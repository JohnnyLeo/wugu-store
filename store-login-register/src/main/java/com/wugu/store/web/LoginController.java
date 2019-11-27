package com.wugu.store.web;

import com.wugu.store.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private RestTemplate restTemplate;
    private LoginService loginService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/isLogin")
    public boolean isLogin(@RequestBody String sessionID) {
        return restTemplate.postForObject("http://localhost:8280/session/selectSession", sessionID, Boolean.class);
    }

    @RequestMapping(value = "/doLogin")
    public boolean doLogin(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        // {查数据库}
        if (loginService.doLogin(userName, passWord)) {
            // {存redis}
            String sessionID = request.getSession().getId();
            restTemplate.put("http://localhost:8280/session/createSession", sessionID);
            Cookie cookie = new Cookie("sessionID", sessionID);
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/loginOut")
    public void loginOut(@RequestBody String sessionID) {
        restTemplate.delete("http://localhost:8280/session/removeSession", sessionID);
    }
}
