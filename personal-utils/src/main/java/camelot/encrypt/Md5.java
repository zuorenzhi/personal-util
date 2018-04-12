package camelot.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * <p>Description: [MD5加密工具类]</p>
 * Created on 2016-3-14
 * @author  <a href="mailto: wuchaoqiang@clt.com">武超强</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
public class Md5 {
	private static Log logger = LogFactory.getLog(Md5.class);// logger日志

	public Md5() {
	}

	/**
	 * <p>Discription:[加密方法]</p>
	 * Created on 2016-3-14
	 * @param s 要加密的字符串 
	 * @return 返回加密后的字符串
	 * @author:武超强
	 */
	public static final String getEncryptedPwd(String s) {
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
		char[] str = null;
		byte[] strTemp = s.getBytes();
		MessageDigest mdTemp;
		try {
			mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			str = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}

		} catch (NoSuchAlgorithmException e) {
			logger.error("MD5加密失败", e);
		}
		if(str == null){
			return null;
		}
		return new String(str);
	}
	
	
}
