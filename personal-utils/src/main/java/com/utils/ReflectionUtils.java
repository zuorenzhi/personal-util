package com.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

/**
 * <p>Description: [反射工具类]<br>[操作对象属性getter-setter;调用对象setter-getter]</p>
 * Created on 2017年3月3日
 * @author  <a href="mailto: zuorenzhi@camelotchina.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class ReflectionUtils {

	/**
	 * <p>Discription:[获取对象obj的filedName值]</p>
	 * Created on 2017年3月8日
	 * @param obj
	 * @param filedName
	 * @return
	 * @author:[zuorenzhi]
	 */
	public static Object getFiledValue(Object obj,String filedName){
		if(null == obj || StringUtils.isBlank(filedName)){
			return null;
		}
		try {
			Field field = obj.getClass().getDeclaredField(filedName);
			if(filedName.equals(field.getName())){
				field.setAccessible(true);
				return field.get(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * <p>Discription:[给对象属性赋值]</p>
	 * Created on 2017年3月8日
	 * @param obj
	 * @param filedName
	 * @param objectValue
	 * @author:[zuorenzhi]
	 */
	public static void setFiledValue(Object obj,String filedName,Object objectValue){
		if(null == obj || StringUtils.isBlank(filedName)){
			return ;
		}
		try {
			Field field = obj.getClass().getDeclaredField(filedName);
			if(filedName.equals(field.getName())){
				field.setAccessible(true);
				field.set(obj, objectValue);;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * <p>Discription:[执行obj的methodName方法]</p>
	 * Created on 2017年3月8日
	 * @param obj
	 * @param methodName
	 * @return
	 * @author:[zuorenzhi]
	 */
	public static Object invokeGetMethod(Object obj,String methodName){
		if(null == obj || StringUtils.isBlank(methodName)){
			return null;
		}
		try {
			Method method = obj.getClass().getMethod(methodName);
			return method.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * <p>Discription:[执行obj的methodName方法]</p>
	 * Created on 2017年3月8日
	 * @notice execute setMethod  if has parameter,param is nessary
	 * @param obj
	 * @param methodName
	 * @param objValue
	 * @author:[zuorenzhi]
	 */
	public static void invokeSetMethod(Object obj,String methodName,Object objValue){
		if(null == obj || StringUtils.isBlank(methodName)){
			return ;
		}
		try {
			Method method = obj.getClass().getMethod(methodName,Integer.class);
			method.invoke(obj, objValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}




