package camelot;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 
 * <p>Description: [字符串工具类,继承org.apache.commons.lang3.StringUtils类]</p>
 * Created on 2016-3-14
 * @author  <a href="mailto: wuchaoqiang@clt.com">武超强</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
public class VerifyUtils extends org.apache.commons.lang3.StringUtils {
	
	/**
	 * <p>Discription:字符串首字母小写</p>
	 * Created on 2016-3-14
	 * @param str 目标字符串
	 * @return String
	 * @author:武超强
	 */
	public static String lowerFirst(String str){
		if(VerifyUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toLowerCase() + str.substring(1);
		}
	}
	
	/**
	 * <p>Discription:字符串首字母大写</p>
	 * Created on 2016-3-14
	 * @param str 目标字符串
	 * @return String
	 * @author:武超强
	 */
	public static String upperFirst(String str){
		if(VerifyUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toUpperCase() + str.substring(1);
		}
	}

	/**
	 * <p>Discription:替换掉HTML标签方法</p>
	 * Created on 2016-3-14
	 * @param html 目标字符串
	 * @return String
	 * @author:武超强
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 
	 * <p>Discription:[缩略字符串（不区分中英文字符）]</p>
	 * Created on 2016-3-14
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 * @author:武超强
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * <p>Discription:[缩略字符串（替换html）]</p>
	 * Created on 2016-3-14
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 * @author:武超强
	 */
	public static String rabbr(String str, int length) {
        return abbr(replaceHtml(str), length);
	}
	

	/**
	 * 
	 * <p>Discription:[获得用户远程地址]</p>
	 * Created on 2016-3-14
	 * @param request request对象
	 * @return String
	 * @author:武超强
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}
	
	/**
	 * 
	 * <p>Discription:[去除字符串中特定字符]</p>
	 * Created on 2016-3-14
	 * @param str 目标字符串
	 * @param symbol 特定字符
	 * @return String
	 * @author:武超强
	 */
	public static String trimString(String str, char symbol) {
		if (str == null) {
			return null;
		}
		StringBuilder result=new StringBuilder();
		str = str.trim();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != symbol) {
				result.append(str.charAt(i));
			}
		}
		return result.toString();
	}
	
	/**
	 * 
	 * <p>Discription:[判断字符串是否为空]</p>
	 * Created on 2016-3-14
	 * @param str 目标字符串
	 * @return 为空返回true，反之则返回false
	 * @author:武超强
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 
	 * <p>Discription:[判断字符串是否不为空]</p>
	 * Created on 2016-3-14
	 * @param str 目标字符串
	 * @return 不为空返回true，反之则返回false
	 * @author:武超强
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && str.trim().length()>0;
	}

	/**
	 * <p>Discription:[ 隐藏手机号中间四位]</p>
	 * Created on 2016-5-20
	 * @param cellPhone
	 * @return
	 * @author:武超强
	 */
	public static String hideCellPhone(String cellPhone)
	{
		if(isNotBlank(cellPhone) && cellPhone.trim().length() > 7){
			//隐藏手机号码中间四位
			StringBuilder temp = new StringBuilder().append(cellPhone.substring(0, 3)).append("****").append(cellPhone.substring(7));
			cellPhone = temp.toString();
		}
		return cellPhone;
	}
	
	/**
	 * <p>Discription:[隐藏邮箱中间四位]</p>
	 * Created on 2016-5-20
	 * @param email
	 * @return
	 * @author:武超强
	 */
	public static String hideEmail(String email)
	{
		if(isNotBlank(email)){
			//Email去掉@后，剩下的字符串
			String emailTemp = email.substring(0, email.indexOf("@"));
			if(emailTemp.length() > 6){
				//拼接隐藏用户中间四位的邮箱，格式：邮箱前（一半 -2）个字符 + "****"(4个*) + 邮箱（一半 + 2）后的字符。
				//邮箱前（一半 -2）个字符
				StringBuilder temp = new StringBuilder(emailTemp.substring(0, (emailTemp.length()-4) / 2));
				//"****"(4个*)
				temp.append("****");
				//邮箱（一半 + 2）后的字符
				temp.append(email.substring((emailTemp.length()-4) / 2 + 4));
				email = temp.toString();
			}else{
				StringBuilder temp = new StringBuilder(email.charAt(0) + "");
				for(int i =0; i < emailTemp.length() -2; i ++)
				{
					temp.append("*");
				}
				temp.append(email.substring(emailTemp.length() - 1)).toString();
				email = temp.toString();
			}
		}
		return email;
	}
	
}
