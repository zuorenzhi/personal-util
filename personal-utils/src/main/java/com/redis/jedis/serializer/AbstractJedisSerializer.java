package com.redis.jedis.serializer;

import java.nio.charset.Charset;

import com.alibaba.fastjson.TypeReference;

public abstract class AbstractJedisSerializer<V> implements JedisSerializer<V> {
	boolean isCompress;
	final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	public AbstractJedisSerializer(){
		this(true);
	}
	
	public AbstractJedisSerializer(boolean isCompress){
		this.isCompress = isCompress;
	}

	public boolean isCompress() {
		return isCompress;
	}

	public void setCompress(boolean isCompress) {
		this.isCompress = isCompress;
	}
	
	@Override
	public <T> T deserialize(byte[] bytes) {
		throw new RuntimeException("this method is not suppor fastjson Serialize");
	}

	@Override
	public <T> T deserialize(byte[] bytes, TypeReference<T> typeReference) {
		throw new RuntimeException("this method is only suppor fastjson Serialize");
	}
	
	
}
