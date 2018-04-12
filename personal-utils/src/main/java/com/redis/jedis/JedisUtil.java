package com.redis.jedis;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @see http://blog.csdn.net/u013467442/article/details/51405271
 * <p>Description: [jedis连接池]</p>
 * Created on 2017年5月5日
 * @author  <a href="mailto: zuorenzhi@clt.com">zuorenzhi</a>
 * @version 1.0 
 * Copyright (c) 2017 camelot jiaofubu
 */
public class JedisUtil {

//	private final static String IP = "172.21.119.63";
//	private final static String IP = "192.168.189.128";
	private final static String IP = "192.168.30.128";
	private final static int PORT = 6379;
	
	private static JedisPool pool = null;  
	
	/*static{
		 // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(1000);
        // 设置空间连接
        config.setMaxIdle(200);
        // 设置最大阻塞时间，记住是毫秒数milliseconds
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(true); 
        // 创建连接池
        pool = new JedisPool(config, IP, PORT);  
	}*/
	
	 /**
	 * 建立连接池 真实环境，一般把配置参数缺抽取出来。
	 */
    private static JedisPool createJedisPool(){
        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(1000);
        // 设置空间连接
        config.setMaxIdle(200);
        // 设置最大阻塞时间，记住是毫秒数milliseconds
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(true); 
        // 创建连接池
        pool = new JedisPool(config, IP, PORT);  
        return pool;
    }
    
    public static Jedis getJedis(){
    	if(pool == null){
    		return createJedisPool().getResource();
    	}else {
			return pool.getResource();
		}
    }
	
	public static Jedis createJedis() {
        Jedis jedis = new Jedis(IP);
        return jedis;
    }

    public static Jedis createJedis(String host, int port) {
        Jedis jedis = new Jedis(host, port);

        return jedis;
    }

    
    public static Jedis createJedis(String host, int port, String passwrod) {
        Jedis jedis = new Jedis(host, port);
        if (!StringUtils.isNotBlank(passwrod))
            jedis.auth(passwrod);
        return jedis;
    }
    
    //*************************************分片*************************************
    /**
     * <p>Discription:[创建分片对象]</p>
     * Created on 2017年5月5日
     * @param args
     * @author:[zuorenzhi]
     */
    public static ShardedJedis createShardJedis() {
        //建立服务器列表
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        //添加第一台服务器信息
        JedisShardInfo si = new JedisShardInfo(IP, PORT);
//        si.setPassword("123");
        shards.add(si);
        //添加第二台服务器信息
        si = new JedisShardInfo(IP, 6389);
//        si.setPassword("123");
//        shards.add(si);
        //建立分片连接对象
        ShardedJedis jedis = new ShardedJedis(shards);        
        //建立分片连接对象,并指定Hash算法
        //ShardedJedis jedis = new ShardedJedis(shards,selfHash);
        return jedis;
    }
    /**
     * <p>Discription:[分片也可以支持连接池]</p>
     * Created on 2017年5月5日
     * @author:[zuorenzhi]
     */
    private static ShardedJedis createShardPool() {
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        JedisShardInfo si = new JedisShardInfo(IP, PORT);
//        si.setPassword("123");
        shards.add(si);
        si = new JedisShardInfo("localhost", 6399);
//        si.setPassword("123");
        shards.add(si);
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(new JedisPoolConfig(), shards);
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        return shardedJedis;
    }
    
    public static void main(String[] args) {
		Jedis jedis = createJedis(IP, PORT);
		System.out.println(jedis.ping());
        jedis.set("hello","world");
        System.out.println(jedis.get("hello"));
//        Jedis jedis2 = getJedis();
//		jedis2.hset("key", "name", "zhangsan");
//		System.out.println(jedis2.hget("key", "name"));
//
		createShardJedis().set("skey", "svalue");
		System.out.println(createShardJedis().get("skey"));
//
//		createShardPool().set("spoolkey", "spoolvalue");
//		System.out.println(createShardPool().get("spoolkey"));
	}
}
