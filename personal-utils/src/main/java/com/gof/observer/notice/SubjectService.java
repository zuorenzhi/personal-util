package com.gof.observer.notice;

import com.gof.observer.observer.Observer;

/**
 * <p>Description: [通知者接口]</p>
 * 一个通知者通知n个观察者，是1:n关系
 * Created on 2017年1月16日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public interface SubjectService {
	
	void add(Observer observer);
	void remove(Observer observer);
	void notifyMes();
	String get();
	void set(String subsjectState);

}
