package com.redis.jedis.client;



import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import com.redis.jedis.serializer.FastJsonJedisSerializer;
import com.redis.jedis.serializer.JedisSerializer;
import com.redis.jedis.serializer.StringJedisSerializer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.alibaba.fastjson.TypeReference;

/**
 * 
 * @author zuorz
 *
 */
@Service("jedisClient")
public class JedisClient implements IJedisClient, InitializingBean{
	public static boolean isDebug = true;
	private static final Logger logger = Logger.getLogger(JedisClient.class);
	@Resource
	private JedisPool jedisPool;
	private JedisSerializer<String> keyJedisSerializer = new StringJedisSerializer(false);
	private JedisSerializer<Object> valueJedisSerializer = new FastJsonJedisSerializer(true);
	

	public Jedis getJedis(){
		return jedisPool.getResource();
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public JedisSerializer<String> getKeyJedisSerializer() {
		return keyJedisSerializer;
	}

	public void setKeyJedisSerializer(JedisSerializer<String> keyJedisSerializer) {
		this.keyJedisSerializer = keyJedisSerializer;
	}

	public JedisSerializer<Object> getValueJedisSerializer() {
		return valueJedisSerializer;
	}

	public void setValueJedisSerializer(JedisSerializer<Object> valueJedisSerializer) {
		this.valueJedisSerializer = valueJedisSerializer;
	}

	@Override
	public <V> void set(final String key,final V value) {
		set(0, key, value);
	}
	
	@Override
	public <V> void set(final int redisDB, final String key,final V value) {
		execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				jedis.set(keyJedisSerializer.serialize(key), valueJedisSerializer.serialize(value));
				return null;
			}
			
		});
	}

	@Override
	public <V> V get(final String key, final TypeReference<V> typeReference) {
		return get(0, key,typeReference);
	}
	
	@Override
	public <V> V get(final int redisDB,final String key, final TypeReference<V> typeReference) {
		return execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return valueJedisSerializer.deserialize(
						jedis.get(keyJedisSerializer.serialize(key)), 
						typeReference);
			}
			
		});
	}
	
	private <V> V execute(String key, ExecuteCallBack<V> executeCallBack){
		Jedis jedis = null;
		try {
			
			long start = System.currentTimeMillis();
			
			jedis = this.getJedis();
			String server = jedis.getClient().getHost() + ":" + jedis.getClient().getPort();
			
			long getJedisCost = System.currentTimeMillis() - start;
			
			start = System.currentTimeMillis();
			V v = executeCallBack.execute(jedis);
			if(isDebug){
				logger.info("JedisClient execute cost:" + (System.currentTimeMillis() - start) + "ms get jedis cost: " + getJedisCost + "ms " + server + " " +key);
			}
			return v;
		} finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	static interface ExecuteCallBack<V>{
		public V execute(Jedis jedis);
	}

	@Override
	public <V> void setex(final String key, final int seconds, final V value) {
		setex(0, key, seconds, value);
	}
	
	@Override
	public <V> void setex(final int redisDB,final String key, final int seconds, final V value) {
		execute(key, new JedisClient.ExecuteCallBack<V>(){

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				jedis.setex(keyJedisSerializer.serialize(key), seconds, valueJedisSerializer.serialize(value));
				return null;
			}
			
		});
	}

	@Override
	public <V> void set(final String key, final V value, final String nxxx, final String expx, final long time) {
		set(0, key, value, nxxx, expx, time);
	}
	
	@Override
	public <V> void set(final int redisDB, final String key, final V value, final String nxxx,
			final String expx, final long time) {
		execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				try {
					if(redisDB > 0){
						jedis.select(redisDB);
					}
					jedis.set(keyJedisSerializer.serialize(key), valueJedisSerializer.serialize(value), 
							nxxx.getBytes("utf-8"), expx.getBytes("utf-8"), time);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return null;
			}
			
		});
		
	}

	@Override
	public Boolean exists(final String key) {
		return exists(0, key);
	}
	
	@Override
	public Boolean exists(final int redisDB,final String key) {
		return execute(key, new JedisClient.ExecuteCallBack<Boolean>() {

			@Override
			public Boolean execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.exists(keyJedisSerializer.serialize(key));
			}
			
		});
	}

	@Override
	public Long persist(final String key) {
		return persist(0, key);
	}
	
	@Override
	public Long persist(final int redisDB,final String key) {
		return execute(key, new JedisClient.ExecuteCallBack<Long>() {

			@Override
			public Long execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.persist(keyJedisSerializer.serialize(key));
			}
			
		});
	}


	@Override
	public String type(final String key) {
		return type(0, key);
	}
	

	@Override
	public String type(final int redisDB,final String key) {
		return execute(key, new JedisClient.ExecuteCallBack<String>() {

			@Override
			public String execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.type(keyJedisSerializer.serialize(key));
			}
			
		});
	}

	@Override
	public Long expire(final String key, final int seconds) {
		return expire(0, key, seconds);
	}
	
	@Override
	public Long expire(final int redisDB,final String key, final int seconds) {
		return execute(key, new JedisClient.ExecuteCallBack<Long>() {

			@Override
			public Long execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.expire(keyJedisSerializer.serialize(key), seconds);
			}
			
		});
	}

	@Override
	public Long expireAt(final String key, final long unixTime) {
		return expireAt(0, key, unixTime);
	}
	
	@Override
	public Long expireAt(final int redisDB,final String key, final long unixTime) {
		return execute(key, new JedisClient.ExecuteCallBack<Long>() {

			@Override
			public Long execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.expireAt(keyJedisSerializer.serialize(key), unixTime);
			}
			
		});
	}

	@Override
	public Long ttl(final String key) {
		return ttl(0, key);
	}
	
	@Override
	public Long ttl(final int redisDB,final String key) {
		return execute(key, new JedisClient.ExecuteCallBack<Long>() {

			@Override
			public Long execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.ttl(keyJedisSerializer.serialize(key));
			}
			
		});
	}

	@Override
	public <V> Long setnx(final String key, final V value) {
		return setnx(0, key, value);
	}
	
	@Override
	public <V> Long setnx(final int redisDB,final String key, final V value) {
		return execute(key, new JedisClient.ExecuteCallBack<Long>() {

			@Override
			public Long execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.setnx(keyJedisSerializer.serialize(key), valueJedisSerializer.serialize(value));
			}
			
		});
	}

	@Override
	public <V> V getSet(final String key, final V value) {
		return getSet(0, key, value);
	}
	
	@Override
	public <V> V getSet(final int redisDB,final String key, final V value) {
		return execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return valueJedisSerializer.deserialize(jedis.getSet(keyJedisSerializer.serialize(key), valueJedisSerializer.serialize(value)));
			}
			
		});
	}

	@Override
	public <V> V get(final String key) {
		return get(0, key);
	}
	
	@Override
	public <V> V get(final int redisDB,final String key) {
		return execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return valueJedisSerializer.deserialize( jedis.get( keyJedisSerializer.serialize(key) ) );
			}
			
		});
	}

	@Override
	public <V> V getSet(final String key, final V value, final TypeReference<V> typeReference) {
		return getSet(0, key, value, typeReference);
	}
	
	@Override
	public <V> V getSet(final int redisDB,final String key, final V value, final TypeReference<V> typeReference) {
		return execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return valueJedisSerializer.deserialize(
						jedis.getSet(keyJedisSerializer.serialize(key), valueJedisSerializer.serialize(value)),
						typeReference);
			}
			
		});
	}

	@Override
	public <V> void hset(final String key, final String field, final V value) {
		hset(0, key, field, value);
	}
	
	@Override
	public <V> void hset(final int redisDB,final String key, final String field, final V value) {
		execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				jedis.hset(keyJedisSerializer.serialize(key), keyJedisSerializer.serialize(field), valueJedisSerializer.serialize(value));
				return null;
			}
			
		});
	}

	@Override
	public <V> V hget(final String key, final String field) {
		return hget(0, key, field);
	}
	
	@Override
	public <V> V hget(final int redisDB,final String key, final String field) {
		return execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return valueJedisSerializer.deserialize(jedis.hget(keyJedisSerializer.serialize(key), keyJedisSerializer.serialize(field)));
			}
			
		});
	}

	@Override
	public <V> V hget(final String key, final String field, final TypeReference<V> typeReference) {
		return hget(0, key, field, typeReference);
	}
	
	@Override
	public <V> V hget(final int redisDB,final String key, final String field, final TypeReference<V> typeReference) {
		return execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return valueJedisSerializer.deserialize(
						jedis.hget(keyJedisSerializer.serialize(key), keyJedisSerializer.serialize(field)),
						typeReference);
			}
			
		});
	}

	@Override
	public <V> void hsetnx(final String key, final String field, final V value) {
		hsetnx(0, key, field, value);
	}
	
	@Override
	public <V> void hsetnx(final int redisDB,final String key, final String field, final V value) {
		execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				jedis.hsetnx(keyJedisSerializer.serialize(key), keyJedisSerializer.serialize(field),
						valueJedisSerializer.serialize(value));
				return null;
			}
			
		});
	}

	@Override
	public <V> void hmset(final String key, Map<String, V> hash) {
		hmset(0, key, hash);
	}
	
	@Override
	public <V> void hmset(final int redisDB,final String key, Map<String, V> hash) {
		final Map<byte[], byte[]> hashByteMap = new HashMap<byte[], byte[]>();
		for (Entry<String, V> entry : hash.entrySet()) {
			hashByteMap.put(keyJedisSerializer.serialize(entry.getKey()), valueJedisSerializer.serialize(entry.getValue()));
		}
		execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				jedis.hmset(keyJedisSerializer.serialize(key), hashByteMap);
				return null;
			}
			
		});
	}

	@Override
	public <V> List<V> hmget(final String key, List<String> fields) {
		
		return hmget(0,key, fields);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <V> List<V> hmget(final int redisDB,final String key, List<String> fields) {
		
		final List<byte[]> fieldByteList = new ArrayList<byte[]>();
		for (String field : fields) {
			fieldByteList.add(keyJedisSerializer.serialize(field));
		}
		
		List<byte[]> valueByteList = execute(key, new JedisClient.ExecuteCallBack<List<byte[]>>() {

			@Override
			public List<byte[]> execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.hmget(keyJedisSerializer.serialize(key), fieldByteList.toArray(new byte[fieldByteList.size()][]));
			}
			
		});
		
		if(valueByteList == null || valueByteList.size() == 0){
			return null;
		}
		
		List<V> valueList = new ArrayList<V>();
		for (byte[] bytes : valueByteList) {
			valueList.add((V)valueJedisSerializer.deserialize(bytes));
		}
		return valueList;
	}

	@Override
	public <V> List<V> hmget(final String key, List<String> fields,
			TypeReference<V> typeReference) {

		return hmget(0,key, fields, typeReference);
	}
	
	@Override
	public <V> List<V> hmget(final int redisDB,final String key, List<String> fields,
			TypeReference<V> typeReference) {

		final List<byte[]> fieldByteList = new ArrayList<byte[]>();
		for (String field : fields) {
			fieldByteList.add(keyJedisSerializer.serialize(field));
		}
		
		List<byte[]> valueByteList = execute(key, new JedisClient.ExecuteCallBack<List<byte[]>>() {

			@Override
			public List<byte[]> execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.hmget(keyJedisSerializer.serialize(key), fieldByteList.toArray(new byte[fieldByteList.size()][]));
			}
			
		});
		
		if(valueByteList == null || valueByteList.size() == 0){
			return null;
		}
		
		List<V> valueList = new ArrayList<V>();
		for (byte[] bytes : valueByteList) {
			V v = valueJedisSerializer.deserialize(bytes, typeReference);
			valueList.add(v);
		}
		return valueList;
	}

	@Override
	public Boolean hexists(final String key, final String field) {
		return hexists(0,key, field);
	}
	
	@Override
	public Boolean hexists(final int redisDB,final String key, final String field) {
		return execute(key, new JedisClient.ExecuteCallBack<Boolean>() {

			@Override
			public Boolean execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.hexists(keyJedisSerializer.serialize(key), keyJedisSerializer.serialize(field));
			}
			
		});
	}

	@Override
	public Long hdel(final String key, List<String> fields) {
		return hdel(0,key, fields);
	}
	
	@Override
	public Long hdel(final int redisDB,final String key, List<String> fields) {
		final List<byte[]> fieldByteList = new ArrayList<byte[]>();
		for (String field : fields) {
			fieldByteList.add(keyJedisSerializer.serialize(field));
		}
		return execute(key, new JedisClient.ExecuteCallBack<Long>() {

			@Override
			public Long execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.hdel(keyJedisSerializer.serialize(key), fieldByteList.toArray(new byte[fieldByteList.size()][]));
			}
			
		});
	}

	@Override
	public Long hlen(final String key) {
		return hlen(0,key);
	}
	
	@Override
	public Long hlen(final int redisDB,final String key) {
		return execute(key, new JedisClient.ExecuteCallBack<Long>() {

			@Override
			public Long execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.hlen(keyJedisSerializer.serialize(key));
			}
			
		});
	}

	@Override
	public Set<String> hkeys(final String key) {
		return hkeys(0,key);
	}
	
	@Override
	public Set<String> hkeys(final int redisDB,final String key) {
		Set<byte[]> fieldBytes = execute(key, new JedisClient.ExecuteCallBack<Set<byte[]>>() {

			@Override
			public Set<byte[]> execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.hkeys(keyJedisSerializer.serialize(key));
			}
			
		});
		if(fieldBytes == null || fieldBytes.size() == 0){
			return null;
		}
		Set<String> tmpSet = new HashSet<String>();
		for (byte[] bytes : fieldBytes) {
			String field = keyJedisSerializer.deserialize(bytes);
			tmpSet.add(field);
		}
		return tmpSet;
	}

	@Override
	public <V> List<V> hvals(final String key) {
		return hvals(0,key);
	}
	
	@Override
	public <V> List<V> hvals(final int redisDB,final String key) {
		List<byte[]> valueByteList = execute(key, new JedisClient.ExecuteCallBack<List<byte[]>>() {

			@Override
			public List<byte[]> execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.hvals(keyJedisSerializer.serialize(key));
			}
			
		});
		
		if(valueByteList == null || valueByteList.size() == 0){
			return null;
		}
		
		List<V> valueList = new ArrayList<V>();
		for (byte[] bytes : valueByteList) {
			V v = valueJedisSerializer.deserialize(bytes);
			valueList.add(v);
		}
		return valueList;
	}

	@Override
	public <V> Map<String, V> hgetAll(final String key) {
		return hgetAll(0,key);
	}

	@Override
	public <V> Map<String, V> hgetAll(final int redisDB,final String key) {
		Map<byte[], byte[]> map  = execute(key, new JedisClient.ExecuteCallBack<Map<byte[], byte[]>>() {

			@Override
			public Map<byte[], byte[]> execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.hgetAll(keyJedisSerializer.serialize(key));
			}
			
		});
		
		if(map == null || map.size() == 0){
			return null;
		}
		
		Map<String, V> returnMap = new HashMap<String, V>();
		for (Entry<byte[], byte[]> entry : map.entrySet()) {
			String k = keyJedisSerializer.deserialize(entry.getKey());
			V v = valueJedisSerializer.deserialize(entry.getValue());
			
			returnMap.put(k, v);
		}
		return returnMap;
	}
	
	@Override
	public <V> Map<String, V> hgetAll(final String key, TypeReference<V> typeReference) {
		return hgetAll(0,key, typeReference);
	}
	
	@Override
	public <V> Map<String, V> hgetAll(final int redisDB,final String key, TypeReference<V> typeReference) {
		Map<byte[], byte[]> map  = execute(key, new JedisClient.ExecuteCallBack<Map<byte[], byte[]>>() {

			@Override
			public Map<byte[], byte[]> execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.hgetAll(keyJedisSerializer.serialize(key));
			}
			
		});
		
		if(map == null || map.size() == 0){
			return null;
		}
		
		Map<String, V> returnMap = new HashMap<String, V>();
		for (Entry<byte[], byte[]> entry : map.entrySet()) {
			String k = keyJedisSerializer.deserialize(entry.getKey());
			V v = valueJedisSerializer.deserialize(entry.getValue(), typeReference);
			
			returnMap.put(k, v);
		}
		return returnMap;
	}

	@Override
	public <V> List<V> hvals(final String key, TypeReference<V> typeReference) {
		return hvals(0,key, typeReference);
	}
	
	@Override
	public <V> List<V> hvals(final int redisDB,final String key, TypeReference<V> typeReference) {
		List<byte[]> valueByteList = execute(key, new JedisClient.ExecuteCallBack<List<byte[]>>() {

			@Override
			public List<byte[]> execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.hvals(keyJedisSerializer.serialize(key));
			}
			
		});
		
		if(valueByteList == null || valueByteList.size() == 0){
			return null;
		}
		
		List<V> valueList = new ArrayList<V>();
		for (byte[] bytes : valueByteList) {
			V v = valueJedisSerializer.deserialize(bytes, typeReference);
			valueList.add(v);
		}
		return valueList;
	}

	@Override
	public <V> Long rpush(final String key, List<V> values) {
		return rpush(0,key, values);
	}
	
	@Override
	public <V> Long rpush(final int redisDB,final String key, List<V> values) {
		if(values == null || values.size() == 0){
			return 0L;
		}
		
		final List<byte[]> valueByteList = new ArrayList<byte[]>();
		for (V v : values) {
			valueByteList.add(valueJedisSerializer.serialize(v));
		}
		return execute(key, new JedisClient.ExecuteCallBack<Long>() {

			@Override
			public Long execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.rpush(keyJedisSerializer.serialize(key), valueByteList.toArray(new byte[valueByteList.size()][]));
			}
			
		});
	}

	@Override
	public <V> Long lpush(final String key, List<V> values) {
		return lpush(0,key, values);
	}
	
	@Override
	public <V> Long lpush(final int redisDB,final String key, List<V> values) {
		if(values == null || values.size() == 0){
			return 0L;
		}
		
		final List<byte[]> valueByteList = new ArrayList<byte[]>();
		for (V v : values) {
			valueByteList.add(valueJedisSerializer.serialize(v));
		}
		return execute(key, new JedisClient.ExecuteCallBack<Long>() {

			@Override
			public Long execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.lpush(keyJedisSerializer.serialize(key), valueByteList.toArray(new byte[valueByteList.size()][]));
			}
			
		});
	}

	@Override
	public Long llen(final String key) {
		return llen(0,key);
	}
	
	@Override
	public Long llen(final int redisDB,final String key) {
		return execute(key, new JedisClient.ExecuteCallBack<Long>() {

			@Override
			public Long execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.llen(keyJedisSerializer.serialize(key));
			}
			
		});
	}

	@Override
	public <V> List<V> lrange(final String key, final long start, final long end) {
		return lrange(0,key, start, end);
	}
	
	@Override
	public <V> List<V> lrange(final int redisDB,final String key, final long start, final long end) {
		List<byte[]> valueByteList = execute(key, new JedisClient.ExecuteCallBack<List<byte[]>>() {

			@Override
			public List<byte[]> execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.lrange(keyJedisSerializer.serialize(key), start, end);
			}
			
		});
		
		if(valueByteList == null || valueByteList.size() == 0){
			return null;
		}
		
		List<V> list = new ArrayList<V>();
		for (byte[] bytes : valueByteList) {
			V v = valueJedisSerializer.deserialize(bytes);
			list.add(v);
		}
		return list;
	}

	@Override
	public <V> List<V> lrange(final String key, final long start, final long end,
			TypeReference<V> typeReference) {
		return lrange(0, key, start, end, typeReference);
	}
	
	@Override
	public <V> List<V> lrange(final int redisDB,final String key, final long start, final long end,
			TypeReference<V> typeReference) {
		List<byte[]> valueByteList = execute(key, new JedisClient.ExecuteCallBack<List<byte[]>>() {

			@Override
			public List<byte[]> execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.lrange(keyJedisSerializer.serialize(key), start, end);
			}
			
		});
		
		if(valueByteList == null || valueByteList.size() == 0){
			return null;
		}
		
		List<V> list = new ArrayList<V>();
		for (byte[] bytes : valueByteList) {
			V v = valueJedisSerializer.deserialize(bytes, typeReference);
			list.add(v);
		}
		return list;
	}

	@Override
	public String ltrim(final String key, final long start, final long end) {
		return ltrim(0,key, start, end);
	}
	
	@Override
	public String ltrim(final int redisDB,final String key, final long start, final long end) {
		return execute(key, new JedisClient.ExecuteCallBack<String>() {

			@Override
			public String execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.ltrim(keyJedisSerializer.serialize(key), start, end);
			}
			
		});
	}

	@Override
	public <V> V lindex(final String key, final long index) {
		return lindex(0,key, index);
	}
	
	@Override
	public <V> V lindex(final int redisDB,final String key, final long index) {
		return execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return valueJedisSerializer.deserialize(jedis.lindex(keyJedisSerializer.serialize(key), index));
			}
			
		});
	}

	@Override
	public <V> V lindex(final String key, final long index, final TypeReference<V> typeReference) {
		return lindex(0,key, index, typeReference);
	}
	
	@Override
	public <V> V lindex(final int redisDB,final String key, final long index, final TypeReference<V> typeReference) {
		return execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return valueJedisSerializer.deserialize(jedis.lindex(keyJedisSerializer.serialize(key), index), typeReference);
			}
			
		});
	}

	@Override
	public <V> String lset(final String key, final long index, final V value) {
		return lset(0,key, index, value);
	}
	
	@Override
	public <V> String lset(final int redisDB,final String key, final long index, final V value) {
		return execute(key, new JedisClient.ExecuteCallBack<String>() {

			@Override
			public String execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.lset(keyJedisSerializer.serialize(key), index, valueJedisSerializer.serialize(value));
			}
			
		});
	}

	@Override
	public <V> Long lrem(final String key, final long count, final V value) {
		return lrem(0,key, count, value);
	}
	
	@Override
	public <V> Long lrem(final int redisDB,final String key, final long count, final V value) {
		return execute(key, new JedisClient.ExecuteCallBack<Long>() {

			@Override
			public Long execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.lrem(keyJedisSerializer.serialize(key), count, valueJedisSerializer.serialize(value));
			}
			
		});
	}

	@Override
	public <V> V lpop(final String key) {
		return lpop(0,key);
	}
	
	@Override
	public <V> V lpop(final int redisDB,final String key) {
		return execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return valueJedisSerializer.deserialize(jedis.lpop(keyJedisSerializer.serialize(key)));
			}
			
		});
	}

	@Override
	public <V> V lpop(final String key, final TypeReference<V> typeReference) {
		return lpop(0,key, typeReference);
	}

	@Override
	public <V> V lpop(final int redisDB,final String key, final TypeReference<V> typeReference) {
		return execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return valueJedisSerializer.deserialize(jedis.lpop(keyJedisSerializer.serialize(key)), typeReference);
			}
			
		});
	}

	
	@Override
	public <V> V rpop(final String key) {
		return rpop(0,key);
	}
	
	@Override
	public <V> V rpop(final int redisDB,final String key) {
		return execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return valueJedisSerializer.deserialize(jedis.rpop(keyJedisSerializer.serialize(key)));
			}
			
		});
	}

	@Override
	public <V> V rpop(final String key, final TypeReference<V> typeReference) {
		return rpop(0,key, typeReference);
	}
	
	@Override
	public <V> V rpop(final int redisDB,final String key, final TypeReference<V> typeReference) {
		return execute(key, new JedisClient.ExecuteCallBack<V>() {

			@Override
			public V execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return valueJedisSerializer.deserialize(jedis.rpop(keyJedisSerializer.serialize(key)), typeReference);
			}
			
		});
	}

	@Override
	public Long del(final String key) {
		return del(0,key);
	}
	
	@Override
	public Long del(final int redisDB,final String key) {
		return execute(key, new JedisClient.ExecuteCallBack<Long>() {

			@Override
			public Long execute(Jedis jedis) {
				if(redisDB > 0){
					jedis.select(redisDB);
				}
				return jedis.del(keyJedisSerializer.serialize(key));
			}
			
		});
	}

	@Override
	public Long del(List<String> keys) {
		return del(0,keys);
	}
	
	@Override
	public Long del(int redisDB,List<String> keys) {
		if(keys == null || keys.size() == 0){
			return null;
		}
		 
		long i = 0;
		for (String key : keys) {
			i += del(redisDB,key);
		}
		return i;
	}

	@Override
	public <V> List<V> mget(List<String> keys) {
		return mget(0, keys);
	}
	
	@Override
	public <V> List<V> mget(int redisDB,List<String> keys) {
		if(keys == null || keys.size() == 0){
			return null;
		}
		List<V> list = new ArrayList<V>();
		for (String key : keys) {
			V v = get(redisDB,key);
			list.add(v);
		}
		return list;
	}
	
	@Override
	public <V> List<V> mget(List<String> keys, TypeReference<V> typeReference) {
		return mget(0,keys, typeReference);
	}
	
	@Override
	public <V> List<V> mget(int redisDB,List<String> keys, TypeReference<V> typeReference) {
		if(keys == null || keys.size() == 0){
			return null;
		}
		List<V> list = new ArrayList<V>();
		for (String key : keys) {
			V v = get(redisDB,key, typeReference);
			list.add(v);
		}
		return list;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

	
}
