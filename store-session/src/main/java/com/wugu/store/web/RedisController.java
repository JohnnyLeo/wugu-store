package com.wugu.store.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wugu.store.domain.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/session")
public class RedisController {

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @RequestMapping(value = "/createSession")
    public void createSession(String sessionID) {
        Session session = new Session();
        String sessionInfo = JSON.toJSONString(session, SerializerFeature.WriteMapNullValue);
        stringRedisTemplate.opsForValue().set(sessionID, sessionInfo, 30, TimeUnit.MINUTES);
    }

    @RequestMapping(value = "/selectSession")
    public boolean selectSession(@RequestBody String sessionID) {
        return stringRedisTemplate.hasKey(sessionID);
    }

    @RequestMapping(value = "/setAttribute")
    public boolean setAttribute(String sessionID, String key, String value) {
        if (selectSession(sessionID)) {
            Session session = getSession(sessionID);
            session.setAttribute(key, value);
            String sessionInfo = JSON.toJSONString(session, SerializerFeature.WriteMapNullValue);
            stringRedisTemplate.opsForValue().set(sessionID, sessionInfo, 30, TimeUnit.MINUTES);
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/getAttribute")
    public String getAttribute(String sessionID, String key) {
        if (selectSession(sessionID)) {
            Session session  = getSession(sessionID);
            return session.getAttribute(key);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/removeAttribute")
    public void removeAttribute(String sessionID) {
        if (selectSession(sessionID)) {
            Session session = new Session();
            String sessionInfo = JSON.toJSONString(session, SerializerFeature.WriteMapNullValue);
            stringRedisTemplate.opsForValue().set(sessionID, sessionInfo, 30, TimeUnit.MINUTES);
        }
    }

    @RequestMapping(value = "/removeSession")
    public void removeSession(String sessionID) {
        if (selectSession(sessionID)) {
            stringRedisTemplate.delete(sessionID);
        }
    }

    private Session getSession(String sessionID) {
        String sessionInfo = stringRedisTemplate.opsForValue().get(sessionID);
        return JSONObject.parseObject(sessionInfo, com.wugu.store.domain.Session.class);
    }
}
