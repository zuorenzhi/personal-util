package com.redis.jedis.client;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.TypeReference;

/**
 * 
 * @author zuorz
 *
 */
public interface IJedisClient {
	
	/**Set the string value as value of the key**/
	<V> void set(String key,  V value);
	
	<V> void set(int redisDB,String key,  V value);
	
	 /**
     * Set the string value as value of the key. 
     * 
     * @param key
     * @param value
     * @param nxxx
     *            NX|XX, NX -- Only set the key if it does not already exist. 
     *            		 XX -- Only set the key if it already exist.
     *            NX -- 当且仅当key不存在时，存入; XX -- 当且仅当key存在时，覆盖存入
     * @param expx
     *            EX|PX, expire time units: EX = seconds; PX = milliseconds
     * @param time
     *            expire time in the units of {@param #expx}
     * @return Status code reply
     */
	<V> void set(String key, V value, String nxxx, String expx, long time);
	
	<V> void set(int redisDB,String key, V value, String nxxx, String expx, long time);
	
	/**Get the value of the specified key. If the key does not exist the special value 'nil' is returned. **/
	<V> V get(final String key);
	
	<V> V get(final int redisDB,final String key);
	
	/**返回value并进行反序列**/
	<V> V get(String key, TypeReference<V> typeReference);
	
	 <V> V get(final int redisDB,final String key, final TypeReference<V> typeReference) ;
	 
	/**根据listKey获取List集合**/
	<V> List<V> mget(final List<String> keys);
	
	/**根据listKey获取List集合(复杂类型如 ..)**/
	<V> List<V> mget(final List<String> keys, TypeReference<V> typeReference);
	
	/**Test if the specified key exists. The command returns "1" if the key exists, otherwise "0" is returned**/
	Boolean exists(String key);
	
	/**Undo a expire at turning the expire key into a normal key**/
	Long persist(String key);

	/**Return the type of the value stored at key in form of a string. The type can be one of "none", "string", "list", "set".
	 *  "none" is returned if the key does not exist**/
    String type(String key);

    Long expire(String key, int seconds);

    Long expireAt(String key, long unixTime);

    Long ttl(String key);

    <V> Long setnx(String key, V value);
    
    <V> void setex(String key, int seconds, final V value);
    
    <V> void setex(int redisDB, String key, int seconds,  V value);
    
    <V> V getSet(String key, V value);
    
    <V> V getSet(String key, V value, TypeReference<V> typeReference);
    
    /**Set the specified hash field to the specified value. **/
    <V> void hset(final String key, final String field, final V value);

    <V> V hget(String key, String field);
    
    /**If key holds a hash, retrieve the value associated to the specified field**/
    <V> V hget(String key, String field, TypeReference<V> typeReference);
    
    <V> void hsetnx(String key, String field, V value);
    
    <V> void hmset(String key, Map<String, V> hash);
    
    <V> List<V> hmget(String key, List<String> fields);
    
    /****/
    <V> List<V> hmget(String key, List<String> fields, TypeReference<V> typeReference);
    
    Boolean hexists(String key, String field);

    Long hdel(String key, List<String> fields);

    Long hlen(String key);

    Set<String> hkeys(String key);

    <V> List<V> hvals(String key);
    /****/
    <V> List<V> hvals(String key, TypeReference<V> typeReference);
    
    <V> Map<String, V> hgetAll(String key);
    
    /****/
    <V> Map<String, V> hgetAll(String key, TypeReference<V> typeReference);
    
    <V> Long rpush(String key, List<V> values);

    <V> Long lpush(String key, List<V> values);

    Long llen(String key);
    
    <V> List<V> lrange(String key, long start, long end);
    
    /****/
    <V> List<V> lrange(String key, long start, long end, TypeReference<V> typeReference);

    String ltrim(String key, long start, long end);

    <V> V lindex(String key, long index);
    
    /****/
    <V> V lindex(String key, long index, TypeReference<V> typeReference);
    
    <V> String lset(String key, long index, V value);

    <V> Long lrem(String key, long count, V value);

    <V> V lpop(String key);
    
    /****/
    <V> V lpop(String key, TypeReference<V> typeReference);

    <V> V rpop(String key);
    
    /****/
    <V> V rpop(String key, TypeReference<V> typeReference);
    
    Long del(String key);
    
    Long del(List<String> keys);
    
    /**==================================================选库=============================================**/

	Boolean exists(int redisDB, String key);

	Long persist( int redisDB, String key) ;

	<V> V getSet( int redisDB, String key,  V value,  TypeReference<V> typeReference) ;

	<V> void hset(int redisDB, String key, String field, V value);

	<V> V hget(int redisDB, String key, String field);

	<V> V hget(int redisDB, String key, String field, TypeReference<V> typeReference);

	<V> void hsetnx(int redisDB, String key, String field, V value);

	<V> void hmset(int redisDB, String key, Map<String, V> hash);

	<V> List<V> hmget(int redisDB, String key, List<String> fields);

	<V> List<V> hmget(int redisDB, String key, List<String> fields,
			TypeReference<V> typeReference);

	Boolean hexists(int redisDB, String key, String field);

	Long hdel(int redisDB, String key, List<String> fields);

	String type(int redisDB, String key);

	Long expire(int redisDB, String key, int seconds);

	Long expireAt(int redisDB, String key, long unixTime);

	Long ttl(int redisDB, String key);

	<V> Long setnx(int redisDB, String key, V value);

	<V> V getSet(int redisDB, String key, V value);

	Long hlen(int redisDB, String key);

	Set<String> hkeys(int redisDB, String key);

	<V> List<V> hvals(int redisDB, String key);

	<V> Map<String, V> hgetAll(int redisDB, String key);

	<V> Map<String, V> hgetAll(int redisDB, String key,
			TypeReference<V> typeReference);

	<V> List<V> hvals(int redisDB, String key, TypeReference<V> typeReference);

	<V> Long rpush(int redisDB, String key, List<V> values);

	<V> Long lpush(int redisDB, String key, List<V> values);

	Long llen(int redisDB, String key);

	<V> List<V> lrange(int redisDB, String key, long start, long end);

	<V> List<V> lrange(int redisDB, String key, long start, long end,
			TypeReference<V> typeReference);

	String ltrim(int redisDB, String key, long start, long end);

	<V> V lindex(int redisDB, String key, long index);

	<V> V lindex(int redisDB, String key, long index, TypeReference<V> typeReference);

	<V> String lset(int redisDB, String key, long index, V value);

	<V> Long lrem(int redisDB, String key, long count, V value);

	<V> V lpop(int redisDB, String key);

	<V> V lpop(int redisDB, String key, TypeReference<V> typeReference);

	<V> V rpop(int redisDB, String key);

	<V> V rpop(int redisDB, String key, TypeReference<V> typeReference);

	Long del(int redisDB, String key);

	Long del(int redisDB, List<String> keys);

	<V> List<V> mget(int redisDB, List<String> keys);

	<V> List<V> mget(int redisDB, List<String> keys, TypeReference<V> typeReference);

}
