package com.student.util.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {
    @Value("${spring.redis.host:172.24.170.121}")
    private String host;
    @Value("${spring.redis.port:6379}")
    private Integer port;
    @Value("${spring.redis.jedis.pool.max-idle:1000}")
    private Integer maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait:3000}")
    private int maxWait;
    @Value("${spring.redis.jedis.pool.max-active:1000}")
    private int maxTotal;
    @Value("${spring.redis.jedis.pool.min-idle:100}")
    private int minIdle;
    @Bean
    public JedisPool  jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMinIdle(minIdle);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, maxWait);
        RedisUtil.setJedisPool(jedisPool);
        return jedisPool;
    }




}

