package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Description : [类描述]
 * Created on : 2018年01月31日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */

@Slf4j
@Configuration
@EnableAutoConfiguration
//@ConfigurationProperties(prefix = "spring.redis", locations = "classpath:applicationII.properties")
@ConfigurationProperties
public class RedisConfig {


    private String hostName = "127.0.0.1";

    private int port = 6379;

    private String password = "";

    private int timeout = 200000;

    @Bean
    public JedisPoolConfig getRedisConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Bean
    public JedisPool getJedisPool() {
        JedisPoolConfig config = getRedisConfig();
        JedisPool pool = new JedisPool(config, hostName, port, timeout, null);
        log.info("init JredisPool ...");
        return pool;
    }
}