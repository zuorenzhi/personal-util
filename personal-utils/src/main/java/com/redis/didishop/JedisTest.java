package com.redis.didishop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.domain.User;
import com.redis.jedis.client.JedisClient;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: [测试自己的工具类]</p>
 * Created on 2017年3月7日
 * @author  <a href="mailto: zuorenzhi@camelotchina.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class JedisTest{

	@Resource
	private JedisPool jedisPool;
	@Resource
	private RedisService redisService;
	@Resource
	private JedisClient jedisClient;
	
	@Test
	public void testJedisClientListMap(){
		List<HashMap<String, User>> list = new ArrayList<HashMap<String,User>>();
		for (int j = 0; j < 4; j++) {
			HashMap<String,User> map = new HashMap<String,User>();
			for (int i = 0; i < 3; i++) {
				User user = new User();
				user.setUserName("name"+i);
				map.put("userkey"+i, user);
			}
			list.add(map);
		}
		jedisClient.set("testListMap", list);
		List<HashMap<String, User>> resultList=  (ArrayList<HashMap<String, User>>)jedisClient.get("testListMap",new TypeReference<ArrayList<HashMap<String, User>>>(){});
		System.out.println(resultList);
		System.out.println(JSON.toJSONString(resultList));
	}
	@Test
	public void testJedisClientList(){
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 3; i++) {
			User user = new User();
			user.setUserName("name"+i);
			list.add(user);
		}
		jedisClient.set("testList", list);
		List<User> resultList=  (ArrayList<User>)jedisClient.get("testList",new TypeReference<ArrayList<User>>(){});
		System.out.println(resultList);
		System.out.println(JSON.toJSONString(resultList));
	}
	@Test
	public void testJedisClientMap(){
		Map<String,User> map = new HashMap<String,User>();
		for (int i = 0; i < 3; i++) {
			User user = new User();
			user.setUserName("name"+i);
			map.put("userkey"+i, user);
		}
		jedisClient.set("vv", map);
		Map<String,User> resultMap=  (Map<String,User>)jedisClient.get("vv",new TypeReference<HashMap<String,User>>(){});
		System.out.println(resultMap);
		System.out.println(JSON.toJSONString(resultMap));
	}
	@Test
	public void testJedisClient(){
		jedisClient.set("hh", "5");
		System.out.println(jedisClient.get("hh",new TypeReference<String>(){}));
		User user = new User();
		user.setUserName("zzz");
		jedisClient.set("vv", user);
		User u=  (User)jedisClient.get("vv",new TypeReference<User>(){});
		System.out.println(JSON.toJSONString(u));
	}
	
	@Test
	public void testRedisService(){
		redisService.set("wo", "ri");
		String value= redisService.get("wo");
		System.out.println("value--"+value);
	}
	@Test
	public void testJedisPool(){
		Jedis jedis = jedisPool.getResource();
		jedis.set("wo1", "nnnn");
		String value= jedis.get("wo1");
		System.out.println("value--"+value);
	}
}
