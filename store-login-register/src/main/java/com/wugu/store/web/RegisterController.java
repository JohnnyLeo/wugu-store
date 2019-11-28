package com.wugu.store.web;

import com.wugu.store.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    private RegisterService registerService;

    @Autowired
    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(value = "/doRegister")
    public boolean doRegister(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        return registerService.doRegister(userName, passWord);
    }

    /**
     * userName need to be unique
     * @param request HttpServletRequest
     * @return true:userName has not been used; false:userName has been used
     */
    @RequestMapping(value = "/userNameVerify")
    public boolean userNameVerify(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        return registerService.userNameVerify(userName);
    }
}
