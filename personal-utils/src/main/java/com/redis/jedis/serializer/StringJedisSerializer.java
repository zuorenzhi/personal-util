package com.redis.jedis.serializer;

import com.utils.file.GzipCompressorUtils;



public class StringJedisSerializer extends AbstractJedisSerializer<String> implements JedisSerializer<String> {

	//20170414
//	public StringJedisSerializer(){
//		this(true);
//	}
//	
//	public StringJedisSerializer(boolean isCompress){
//		this.isCompress = isCompress;
//	}
	public StringJedisSerializer() {
		super();
	}
	public StringJedisSerializer(boolean isCompress) {
		super(isCompress);
	}
	
	@Override
	public byte[] serialize(String t) {
		try {
			return (t == null ? null : 
				this.isCompress?GzipCompressorUtils.compress( t.getBytes(this.DEFAULT_CHARSET) ):
					t.getBytes(this.DEFAULT_CHARSET));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T deserialize(byte[] bytes) {
		try {
			return (T) (bytes == null ? null : new String(this.isCompress? GzipCompressorUtils.uncompress(bytes) : bytes, this.DEFAULT_CHARSET));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
