package com.utils.movesort;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import camelot.dao.BaseDAO;

/**
 * 
 * <p>Description: [上下移动工具类]</p>
 * Created on 2016年12月9日
 * @author  <a href="mailto: hanshixiong@clt.com">韩士雄</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
public class MoveUpAndDownUtils {
	
	private BaseDAO dao;
	private MoveUpAndDownUtils(BaseDAO dao) {
		this.dao = dao;
	}
	
	/**
	 * 
	 * <p>Discription:[获取工具类实例]</p>
	 * Created on 2016年12月9日
	 * @param dao 要求dao必须实现queryById,queryList,update方法
	 * @return
	 * @author[hanshixiong]
	 */
	public static MoveUpAndDownUtils getInstance(BaseDAO dao) {
		return new MoveUpAndDownUtils(dao);
	}
	
	/**
	 * 
	 * <p>Discription:[移动位置]</p>
	 * Created on 2016年12月9日
	 * @param isUp 是否上移
	 * @param beanId
	 * @author[hanshixiong]
	 */
	@SuppressWarnings("unchecked")
	public void move(boolean isUp, Long beanId) {
		Object currentBean = dao.queryById(beanId);
		Object exchangeBean = this.getExchangeBean(isUp, currentBean, dao.queryList(null, null, null));
		// 未获取到交换bean说明已在第一位或最后一位，无需变化位置
		if ( exchangeBean ==null ) {
			return ;
		}
		
		int currentSort = getSort(currentBean);
		this.setSort(currentBean, getSort(exchangeBean));
		this.setSort(exchangeBean, currentSort);
		
		dao.update(currentBean);
		dao.update(exchangeBean);
	}
	
	public Object getExchangeBean(boolean isUp, Object currentBean, List<Object> list) {
		Object exchageBean = null;
		
		Collections.sort(list, new ComparatorBySort<Object>());
		
		int index = this.getCurrentBeanIndex(list, currentBean);
		
		if ( isUp ) {
			// 已是排序第一位
			if ( index != 0 ) {
				exchageBean = list.get(index-1);
			}
		} else {
			// 已是排序最后一位
			if ( index != (list.size()-1) ) {
				exchageBean = list.get(index+1);
			}
		}
		return exchageBean;
	}
	/**
	 * 
	 * <p>Description: [list排序比较器，通过sort对list进行排序]</p>
	 * Created on 2016年12月9日
	 * @author  <a href="mailto: hanshixiong@clt.com">韩士雄</a>
	 * @version 1.0 
	 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
	 */
	class ComparatorBySort<Object> implements Comparator<Object> {
		@Override
		public int compare(Object o1, Object o2) {
			Integer sort1;
			Integer sort2;
			try {
				Class clazz = o1.getClass();
				Method getSort = clazz.getMethod("getSort", null);
				sort1 = (Integer) getSort.invoke(o1, null);
				sort2 = (Integer) getSort.invoke(o2, null);
				return sort1-sort2;
			} catch (Exception e) {
				throw new RuntimeException("排序失败");
			}
		}
	}
	
	/**
	 * 
	 * <p>Discription:[获取当前bean在list中的脚标]</p>
	 * Created on 2016年12月9日
	 * @param list
	 * @param currentBean
	 * @return
	 * @author[hanshixiong]
	 */
	private int getCurrentBeanIndex(List<Object> list, Object currentBean) {
		int index = 0;
		for ( int i=0; i<list.size(); i++ ) {
			if ( this.getSort(list.get(i)) == this.getSort(currentBean) ) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	/**
	 * 
	 * <p>Discription:[通过反射获取sort]</p>
	 * Created on 2016年12月9日
	 * @param bean 要求必须有sort字段否则抛异常
	 * @return
	 * @author[hanshixiong]
	 */
	public int getSort(Object bean) {
		try {
			Class clazz = bean.getClass();
			int sort = -1;
			Method getSort = clazz.getMethod("getSort", null);
			sort = (Integer) getSort.invoke(bean, new Object[]{});
			return sort;
		} catch (Exception e) {
			throw new RuntimeException("获取排序失败");
		}
	}
	
	/**
	 * 
	 * <p>Discription:[通过反射为bean设置sort值]</p>
	 * Created on 2016年12月9日
	 * @param bean bean 要求必须有sort字段否则抛异常
	 * @param sort 新的sort值
	 * @author[hanshixiong]
	 */
	public void setSort(Object bean, int sort) {
		try {
			Class clazz = bean.getClass();
			Method setSort = clazz.getMethod("setSort", Integer.class);
			setSort.invoke(bean, sort);
		} catch (Exception e) {
			throw new RuntimeException("获取排序失败");
		}
	}
}
