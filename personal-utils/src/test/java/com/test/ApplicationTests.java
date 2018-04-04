package com.test;

import com.WebApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
@Slf4j
public class ApplicationTests {

	@Resource
	private JedisPool jedisPool;

	@Test
	public void contextLoads() {
		log.info("【ApplicationTests-->contextLoads】 测试");
	}

	@Test
	public void jedisGet(){
//		Jedis jedis = jedisPool.getResource();
		Jedis jedis = getJedis();
		log.info("【ApplicationTests-->jedisGet】 jedis.ping() = {}",jedis.ping());
		jedis.set("name","zhangsan");
		log.info("【ApplicationTests-->jedisGet】 name = {}",jedis.get("name"));
	}

	/**
	 * Discription: [获取jedis]
	 * Created on: 2018-01-31 20:38
	 * author : [左仁智]
	 */
	private Jedis getJedis() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//返回的是引用对象对应的地址(内存地址的index=0,内存磁条是一格一格的)
			//引用还在手里，还是指向该地址位置，还可以使用，直到本次调用完毕，引用也生命周期结束。
			jedisPool.returnResource(jedis);
		}
		return jedis;
	}

}
