package com.utils.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContextUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext){
		SpringApplicationContextUtils.applicationContext = applicationContext;
	}

	/**
	 * 获取applicationContext对象
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 根据beanid获取spring中的bean对象
	 * 
	 * @param beanId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanId) {
		return (T) applicationContext.getBean(beanId);
	}

}
