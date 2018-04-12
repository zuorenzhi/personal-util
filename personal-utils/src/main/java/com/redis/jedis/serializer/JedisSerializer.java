package com.redis.jedis.serializer;

import com.alibaba.fastjson.TypeReference;


/**
 * <p>Description: [适配器]</p>
 * Created on 2017年4月14日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public interface JedisSerializer<V> {
	
	byte[] serialize(V v);
	
	<T> T deserialize(byte[] bytes);
	
	<T> T  deserialize(byte[] bytes, TypeReference<T> typeReference);
}
