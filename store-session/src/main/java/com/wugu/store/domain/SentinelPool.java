package com.wugu.store.domain;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jleo
 * @date 2020/3/27
 */
public class SentinelPool {
    private static JedisSentinelPool pool;
    private static Set<String> sentinel = new HashSet<>();

    static {
        sentinel.add("127.0.0.1:26379");
        sentinel.add("127.0.0.1:26380");
        initPool();
    }

    private static void initPool() {
        pool = new JedisSentinelPool("master1",
                sentinel,
                new GenericObjectPoolConfig(),
                3000);
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }
}
