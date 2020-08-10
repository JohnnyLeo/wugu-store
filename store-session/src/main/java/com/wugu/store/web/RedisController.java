package com.wugu.store.web;

import com.wugu.store.domain.SentinelPool;
import com.wugu.store.domain.WuguAttribute;
import com.wugu.store.domain.WuguSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/session")
public class RedisController {

    private final static String SESSION_ATTRIBUTE_USER_KEY = "userName";
    private final static int SESSION_TIME_OUT = 1800;

    private RedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping(value = "/createSession")
    public boolean createSession(@RequestBody WuguSession session) {
        if (session.getSessionID() != null && session.getUserName() != null) {
            redisTemplate.boundHashOps(session.getSessionID()).put(SESSION_ATTRIBUTE_USER_KEY, session.getUserName());
            redisTemplate.expire(session.getSessionID(), SESSION_TIME_OUT, TimeUnit.MINUTES);
//            Jedis jedis = SentinelPool.getJedis();
//            jedis.hsetnx(session.getSessionID(), SESSION_ATTRIBUTE_USER_KEY, session.getUserName());
//            jedis.expire(session.getSessionID(), SESSION_TIME_OUT);
//            jedis.close();
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/selectSession")
    public boolean selectSession(@RequestBody String sessionID) {
        return redisTemplate.hasKey(sessionID);
//        Jedis jedis = SentinelPool.getJedis();
//        boolean bool =  jedis.exists(sessionID);
//        jedis.close();
//        return  bool;
    }

    /**
     *
     * @param attribute sessionID:String key:String value:String
     * @return true or false
     */
    @RequestMapping(value = "/setAttribute")
    public boolean setAttribute(@RequestBody WuguAttribute attribute) {
        String sessionID = attribute.getSessionID();
        String key = attribute.getKey();
        String value = attribute.getValue();
        if (key != null && selectSession(sessionID)) {
            redisTemplate.boundHashOps(sessionID).put(key, value);
            redisTemplate.expire(sessionID, SESSION_TIME_OUT, TimeUnit.MINUTES);
//            Jedis jedis = SentinelPool.getJedis();
//            jedis.hset(sessionID, key, value);
//            jedis.expire(sessionID, SESSION_TIME_OUT);
//            jedis.close();
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param attribute sessionID:String key:String value:null
     * @return value:String or null
     */
    @RequestMapping(value = "/getAttribute")
    public String getAttribute(@RequestBody WuguAttribute attribute) {
        String sessionID = attribute.getSessionID();
        String key = attribute.getKey();
        if (selectSession(sessionID)) {
            String value = redisTemplate.boundHashOps(sessionID).get(key).toString();
            redisTemplate.expire(sessionID, SESSION_TIME_OUT, TimeUnit.MINUTES);
//            Jedis jedis = SentinelPool.getJedis();
//            String value = jedis.hget(sessionID, key);
//            jedis.expire(sessionID, SESSION_TIME_OUT);
//            jedis.close();
            return value;
        } else {
            return null;
        }
    }

    /**
     *
     * @param attribute sessionID:String key:String value:null
     */
    @RequestMapping(value = "/removeAttribute")
    public void removeAttribute(@RequestBody WuguAttribute attribute) {
        String sessionID = attribute.getSessionID();
        String key = attribute.getKey();
        if (selectSession(sessionID)) {
            redisTemplate.boundHashOps(sessionID).delete(key);
            redisTemplate.expire(sessionID, SESSION_TIME_OUT, TimeUnit.MINUTES);
//            Jedis jedis = SentinelPool.getJedis();
//            jedis.hdel(sessionID, key);
//            jedis.expire(sessionID, SESSION_TIME_OUT);
//            jedis.close();
        }
    }

    @RequestMapping(value = "/removeSession")
    public boolean removeSession(@RequestBody String sessionID) {
        if (selectSession(sessionID)) {
            redisTemplate.delete(sessionID);
//            Jedis jedis = SentinelPool.getJedis();
//            jedis.del(sessionID);
//            jedis.close();
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/test")
    public void test() {
        Jedis jedis = SentinelPool.getJedis();
        jedis.set("test", "123456");
        jedis.close();
    }
}
