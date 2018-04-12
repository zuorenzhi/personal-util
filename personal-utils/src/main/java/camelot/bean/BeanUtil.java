package camelot.bean;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * <p>Description: [拷贝、复制bean属性--扩展spring BeanUtils功能，解决把空属性也拷贝进来的问题]</p>
 * Created on 2016-3-10
 * @author  <a href="mailto: liujun@clt.com">liujun</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
@Slf4j
public abstract class BeanUtil extends org.springframework.beans.BeanUtils {

	/**
	 * <p>Discription:[拷贝一个对象里非空属性值到另一个对象]</p>
	 * Created on 2016-3-14
	 * @param source 源对象
	 * @param target 目标对象
	 * @author:Liu Jun
	 */
	public static void copyProperties(Object source, Object target) {   
	    Assert.notNull(source, "Source must not be null");   
	    Assert.notNull(target, "Target must not be null");   
	    Class<?> actualEditable = target.getClass();   
	    PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);   
	    for (PropertyDescriptor targetPd : targetPds) { 
	    	if (targetPd.getWriteMethod() != null) {
	    		PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
	    		if (sourcePd != null && sourcePd.getReadMethod() != null) {
	    			try {
	    				Method readMethod = sourcePd.getReadMethod(); 
	    				if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
	    					readMethod.setAccessible(true);
	    				}
	    				Object value = readMethod.invoke(source);  
	    				// 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等  
	    				if (value != null) { 
	    					Method writeMethod = targetPd.getWriteMethod();
	    					if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) { 
	    						writeMethod.setAccessible(true);
	    					}
	    					writeMethod.invoke(target, value);
	    				}
	    			}catch (Throwable ex) {
	    				log.error(source.getClass().getName()+"属性拷贝到"+target.getClass().getName()+"中失败", ex);
	    	            throw new FatalBeanException("Could not copy properties from source to target", ex);  
	    			}
	    		}
	    	}
	    }
	}

	public static void main(String[] args) {
		A a = new A(2, "a", 100L);
		B b = new B();
		BeanUtil.copyProperties(a,b);
		log.info("【BeanUtil-->main】 b={}", JSON.toJSONString(b));
	}

	@Data
	@AllArgsConstructor
	static class A {
		private int no ;
		private String name;
		private long age;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	static class B {
		private int no ;
		private String name;
	}

}
