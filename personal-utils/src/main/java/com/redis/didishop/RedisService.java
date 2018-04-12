package com.redis.didishop;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;

import com.utils.SerializeUtils;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;


/**
 * <p>Description: [redis工具类]</p>
 * Created on 2016年4月27日
 * @author  <a href="mailto: chendong61@clt.com">chendong</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
@Service("redisServiceDB")
public class RedisService {

	@Resource
	private JedisPool jedisPool;

	/**
	 * <p>Discription:[判断存在]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @return
	 * @author:[chendong]
	 */
	public boolean exists(String key) {
		Jedis jedis = getJedis();
		boolean btn = false;
		try {			
			btn = jedis.exists(key);
			release(jedis);
		} catch (Exception e) {
			release(jedis);
		}
		return btn;
	}

	/**
	 * <p>Discription:[满足pattern的keySet]</p>
	 * Created on 2016年4月27日
	 * @param pattern
	 * @return
	 * @author:[chendong]
	 */
	public Set<String> keys(String pattern){
		Jedis jedis = getJedis();
		Set<String> keySet = null;
		try {			
			keySet = jedis.keys(pattern);
			release(jedis);
		} catch (Exception e) {
			release(jedis);
		}
		return keySet;
	};
	
	/**
	 * <p>Discription:[取value]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @return
	 * @author:[chendong]
	 */
	public String get(String key) {
		Jedis jedis = getJedis();
		String value = null;
		try {			
			value = jedis.get(key);
			release(jedis);
		} catch (Exception e) {
			release(jedis);
		}
		return value;
	}

	/**
	 * <p>Discription:[存入key/value]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @param value
	 * @author:[chendong]
	 */
	public void set(String key, String value) {
		Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
			release(jedis);
		} catch (Exception e) {
			release(jedis);
		}
	}

	/**
	 * <p>Discription:[存入带生命周期的key/value]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @param value
	 * @param seconds
	 * @author:[chendong]
	 */
	public void setAndExpire(String key, String value, int seconds) {
		Jedis jedis = getJedis();
		try {
			Pipeline p = jedis.pipelined();
			p.set(key, value);
			p.expire(key, seconds);
			p.sync();
		} catch (Exception e) {
			release(jedis);
		}
	}

	/**
	 * <p>Discription:[存入对象]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @param object
	 * @author:[chendong]
	 */
	public void addObject(String key,Object object){
		Jedis jedis = getJedis();
		try {
			jedis.set(key.getBytes(), SerializeUtils.serialize(object));
			release(jedis);
		} catch (Exception e) {
			release(jedis);
		}
	}

	/**
	 * <p>Discription:[存入带生命周期的对象]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @param object
	 * @param seconds
	 * @author:[chendong]
	 */
	public void addObject(String key,Object object,int seconds){
		Jedis jedis = getJedis();
		try {
			Pipeline p = jedis.pipelined();
			p.set(key.getBytes(), SerializeUtils.serialize(object));
			p.expire(key, seconds);
			p.sync();
			release(jedis);
		} catch (Exception e) {
			release(jedis);
		}
		
	}

	/**
	 * <p>Discription:[获取对象]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @return
	 * @author:[chendong]
	 */
	public Object getObject(String key){
		Jedis jedis = getJedis();
		Object object = null;
		try {
			byte[] dataBytes = jedis.get(key.getBytes());
			if(dataBytes==null){
				return null;
			}
			object = SerializeUtils.unserialize(dataBytes);
			release(jedis);
		} catch (Exception e) {
			release(jedis);
		}
		return object;
	}

	/**
	 * <p>Discription:[删除]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @author:[chendong]
	 */
	public void del(String key) {
		Jedis jedis = getJedis();
		try {
			jedis.del(key);
			release(jedis);
		} catch (Exception e) {
			release(jedis);
		}
	}

	/**
	 * <p>Discription:[清理内存]</p>
	 * Created on 2016年4月27日
	 * @author:[chendong]
	 */
	public void flushDB(){
		Jedis jedis = getJedis();
		try {
			jedis.flushAll();
			release(jedis);
		} catch (Exception e) {
			release(jedis);
		}
	}

	/**
	 * <p>Discription:[在redis消息队列队尾插入数据]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @param object
	 * @author:[chendong]
	 */
	public void tailPush(String key,Object object){
		Jedis jedis = getJedis();
		try {
			jedis.rpush(key.getBytes(),  SerializeUtils.serialize(object));
			release(jedis);
		} catch (Exception e) {
			release(jedis);
		}
	}

	/**
	 * <p>Discription:[在redis消息队列队头插入数据]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @param object
	 * @author:[chendong]
	 */
	public void headPush(String key,Object object){
		Jedis jedis = getJedis();
		try {
			jedis.lpush(key.getBytes(), SerializeUtils.serialize(object));
			release(jedis);
		} catch (Exception e) {
			release(jedis);
		}
	}

	/**
	 * <p>Discription:[在redis消息队列队尾删除数据]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @return
	 * @author:[chendong]
	 */
	public Object tailPop(String key){
		Jedis jedis = getJedis();
		try {
			byte[] result = jedis.rpop(key.getBytes());
			release(jedis);
			if(result != null && result.length > 0){
				return null;
			}
			return SerializeUtils.unserialize(result);
		} catch (Exception e) {
			release(jedis);
			return null;
		}
	}

	/**
	 * <p>Discription:[在redis消息队列队头删除数据]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @return
	 * @author:[chendong]
	 */
	public Object headPop(String key){
		Jedis jedis = getJedis();
		try {
			byte[] result = jedis.lpop(key.getBytes());
			release(jedis);
			if(result != null && result.length > 0){
				return null;
			}
			return SerializeUtils.unserialize(result);
		} catch (Exception e) {
			release(jedis);
			return null;
		}
	}

	/**
	 * <p>Discription:[存Hash]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @param field
	 * @param value
	 * @author:[chendong]
	 */
	public void setHash(String key ,String field ,String value){
		Jedis jedis = getJedis();
		try {
			jedis.hset(key, field, value);
			release(jedis);
		} catch (Exception e) {
			release(jedis);
		}
	}

	/**
	 * <p>Discription:[方法功能中文描述]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @param field
	 * @return
	 * @author:[chendong]
	 */
	public String getHash(String key ,String field){
		Jedis jedis = getJedis();
		String value = null;
		try {
			value = jedis.hget(key, field);
			release(jedis);
		} catch (Exception e) {
			release(jedis);
		}
		return value;
	}

	/**
	 * <p>Discription:[设置key的过期时间点]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @param endTime
	 * @author:[chendong]
	 */
	public void setExpire(String key,Date endTime){
		Jedis jedis = getJedis();
		try {
			long seconds =endTime.getTime()-new Date().getTime();
			jedis.expire(key, (int) (seconds/1000));
			release(jedis);
		} catch (Exception e) {
			release(jedis);
		}
	}

	/**
	 * <p>Discription:[释放jedis客户端]</p>
	 * Created on 2016年4月27日
	 * @param jedis
	 * @author:[chendong]
	 */
	private void release(Jedis jedis) {
		jedisPool.returnResource(jedis);
	}

	/**
	 * <p>Discription:[得到jedis客户端]</p>
	 * Created on 2016年4月27日
	 * @return
	 * @author:[chendong]
	 */
	private Jedis getJedis() {
		Jedis jedis = jedisPool.getResource();
		return jedis;
	}

	/**
	 * <p>Discription:[获取key剩余的到期时间]</p>
	 * Created on 2016年4月27日
	 * @param key
	 * @return TTL以毫秒为单位。-1, 如果key没有到期超时。-2, 如果键不存在。
	 * @author:[chendong]
	 */
	public Long getKeyTtl(String key){
		Jedis jedis = getJedis();
		Long seconds = null;
		try {
			seconds = jedis.ttl(key);
			release(jedis);
		} catch (Exception e) {
			release(jedis);
		}
		return seconds;
	}

    /**
     * <p>Discription:[获取一个指定key的自增整数]</p>
     * Created on 2016年4月27日
     * @param key
     * @return 返回增长后整数值。
     * @author:[chendong]
     */
    public Long incr(String key){
        Jedis jedis = getJedis();
        Long increment = null;
        try {
            increment = jedis.incr(key);
            release(jedis);
        } catch (Exception e) {
            release(jedis);
        }
        return increment;
    }

    /**
     * <p>Discription:[获取一个指定key的增长指定长度整数值]</p>
     * Created on 2016年4月27日
     * @param key
     * @param integer 增长的值
     * @return 返回增长后整数值。
     * @author:[chendong]
     */
    public Long incrBy(String key,Long integer){
        Jedis jedis = getJedis();
        Long increment = null;
        try {
            increment = jedis.incrBy(key,integer);
            release(jedis);
        } catch (Exception e) {
            release(jedis);
        }
        return increment;
    }

}
