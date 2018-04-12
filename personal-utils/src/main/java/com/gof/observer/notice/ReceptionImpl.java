package com.gof.observer.notice;

import java.util.ArrayList;
import java.util.List;

import com.gof.observer.observer.Observer;

/**
 * <p>Description: [通知者员工实现]</p>
 * Created on 2017年1月16日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class ReceptionImpl implements SubjectService{
	
	private List<Observer> list = new ArrayList<Observer>();
	
	private String action;
	
	@Override
	public void add(Observer observer) {
		list.add(observer);
	}

	@Override
	public void remove(Observer observer) {
		list.remove(observer);
	}

	@Override
	public void notifyMes() {
		for (Observer observer : list) {
			observer.update();
		}
	}

	@Override
	public String get() {
		return action;
	}

	@Override
	public void set(String subsjectState) {
		action = subsjectState;
	}

}
