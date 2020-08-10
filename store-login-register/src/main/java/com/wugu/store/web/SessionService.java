package com.wugu.store.web;

import com.wugu.store.domain.WuguAttribute;
import com.wugu.store.domain.WuguSession;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jleo
 * @date 2020/8/10
 */
@FeignClient(value = "STORE-SESSION")
public interface SessionService {

    @RequestMapping(value = "/session/createSession", method = RequestMethod.POST)
    public boolean doLogin(WuguSession wuguSession);

    @RequestMapping(value = "/session/getAttribute", method = RequestMethod.POST)
    public String getLogin(WuguAttribute wuguAttribute);

    @PostMapping("/session/removeSession")
    public boolean loginOut(String sessionID);
}
