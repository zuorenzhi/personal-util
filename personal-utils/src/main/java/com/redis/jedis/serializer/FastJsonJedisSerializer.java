package com.redis.jedis.serializer;


import com.redis.jedis.client.JedisClient;
import com.utils.file.GzipCompressorUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class FastJsonJedisSerializer extends AbstractJedisSerializer<Object> implements JedisSerializer<Object> {
	private static final Logger logger = Logger.getLogger(FastJsonJedisSerializer.class);
	
	public FastJsonJedisSerializer(){
		this(true);
	}
	
	public FastJsonJedisSerializer(boolean isCompress){
		this.isCompress = isCompress;
	}
	
	@Override
	public byte[] serialize(Object v) {
		try {
			String json = JSON.toJSONString(v);
			byte[] bytes = json.getBytes(DEFAULT_CHARSET);
			if(this.isCompress){
				return GzipCompressorUtils.compress(bytes);
			}
			return bytes;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public <T> T deserialize(byte[] bytes, TypeReference<T> typeReference) {
		if(bytes == null || bytes.length < 1){
			return null;
		}
		
		long start = System.currentTimeMillis();
		try {
			String json = this.isCompress?new String(GzipCompressorUtils.uncompress(bytes), DEFAULT_CHARSET):new String(bytes, DEFAULT_CHARSET);
			if (StringUtils.isBlank(json)){
				return null;
			}else{
				return JSON.parseObject(json, typeReference);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}finally{
			if(JedisClient.isDebug){
				logger.info("FastJsonJedisSerializer cost time:" + (System.currentTimeMillis() - start) + "ms");
			}
		}
	}

}
