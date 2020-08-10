package com.wugu.store.web;

import com.wugu.store.domain.WuguAttribute;
import com.wugu.store.domain.WuguSession;
import com.wugu.store.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private RestTemplate restTemplate;
    private LoginService loginService;
    private SessionService sessionService;

    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/getLogin")
    public String getLogin(@RequestBody String sessionID) {
        WuguAttribute wuguAttribute = new WuguAttribute();
        wuguAttribute.setSessionID(sessionID);
        wuguAttribute.setKey("userName");
        String userName = sessionService.getLogin(wuguAttribute);//restTemplate.postForObject("http://store-session/session/getAttribute", wuguAttribute, String.class);
        if (userName != null) {
            return userName;
        }
        return null;
    }

    @RequestMapping(value = "/getUserName")
    public String getUserName(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionID".equals(cookie.getName())) {
                    String sessionID = cookie.getValue();
                    return getLogin(sessionID);
                }
            }
        }
        return "";
    }

    @RequestMapping(value = "/doLogin")
    public boolean doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("password");
        // {查数据库}
        if (loginService.doLogin(userName, passWord)) {
            // {存redis}
            String sessionID = UUID.randomUUID().toString();
            WuguSession wuguSession = new WuguSession();
            wuguSession.setSessionID(sessionID);
            wuguSession.setUserName(userName);
            //restTemplate.postForEntity("http://store-session/session/createSession", wuguSession, Boolean.class);
            sessionService.doLogin(wuguSession);
            Cookie cookie = new Cookie("sessionID", sessionID);
            cookie.setMaxAge(60 * 60);
            cookie.setDomain("localhost");
            cookie.setPath("/");
            response.addCookie(cookie);
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/loginOut")
    public boolean loginOut(@RequestBody String sessionID) {
        return sessionService.loginOut(sessionID);//restTemplate.postForObject("http://store-session/session/removeSession", sessionID, Boolean.class);
    }
}
