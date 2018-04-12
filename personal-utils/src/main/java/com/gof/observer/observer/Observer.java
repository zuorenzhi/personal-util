package com.gof.observer.observer;

import com.gof.observer.notice.SubjectService;

/**
 * <p>Description: [观察者抽象类]</p>
 * 用抽象类，便于子类复用构造器代码
 * 每个观察者观察一个通知者
 * Created on 2017年1月16日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public abstract class Observer {

	protected String name;
	protected SubjectService subjectService;
	
	public Observer(String name, SubjectService subjectService) {
		this.name = name;
		this.subjectService = subjectService;
	}
	
	public abstract void update();
}
