package com.apache;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.domain.Student;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;


/**
 * <p>Description: [org.apache.commons.beanutils.BeanUtils 方法测试]</p>
 * Created on 2017年5月2日
 * @author  <a href="mailto: zuorenzhi@camelotchina.com">zuorenzhi</a>
 * @version 1.0 
 * Copyright (c) 2017 camelot jiaofubu
 */
public class ABeanUtils {

	/**
	 * Discription: [类属性转Map]
	 * Created on: 2017-10-31 21:09
	 * author : [左仁智]
	 */
	@Test
	public void describe() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		//若无 对应属性的getter方法，那么获取的值仅有 -- class =...
		//class=class com.camelot.mymaven.test.domain.Student
		Student student= new Student(2L, 22, "nice", "shanghai");
		Map<String, String> paramMap = org.apache.commons.beanutils.BeanUtils.describe(student);
		System.out.println(paramMap);
		Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, String> entry = iterator.next();
//			System.out.println(entry);
		}
		Map<String, Object> proMap = PropertyUtils.describe(student);
		proMap.remove("class");
		System.out.println(proMap);
	}
	
}
