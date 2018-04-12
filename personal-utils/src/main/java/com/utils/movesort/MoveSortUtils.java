package com.utils.movesort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import camelot.dao.BaseDAO;
import com.utils.ReflectionUtils;

/**
 * <p>Description: [排序工具类]</p>
 * Created on 2017年3月3日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class MoveSortUtils {
	
    private BaseDAO baseDAO;
	
	public MoveSortUtils(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public static MoveSortUtils getInstance(BaseDAO baseDAO){
		return new MoveSortUtils(baseDAO);
	}
	
	/**
	 * <p>Discription:[方法功能中文描述]</p>
	 * Created on 2017年3月3日
	 * @param id 
	 * @param isUp 上、下移
	 * @param sortFiledName 排序字段的名字
	 * @author:[左仁智]
	 */
	public void MoveBeanSort(Long id,Boolean isUp,final String sortFiledName){
		//current
		Object thisObj = baseDAO.queryById(id);
		
		List list = baseDAO.queryList(null, null,null);
		//exchageObj
		Object targetObj = getTargetObject(thisObj, list, isUp, sortFiledName);
		if(targetObj == null)
			return;
		//exchage sortValue
		Object thisSort = ReflectionUtils.getFiledValue(thisObj, sortFiledName);
		Object targetSort = ReflectionUtils.getFiledValue(targetObj, sortFiledName);
		
		ReflectionUtils.setFiledValue(thisObj, sortFiledName, targetSort);
		ReflectionUtils.setFiledValue(targetObj, sortFiledName, thisSort);
		//update
		baseDAO.update(thisObj);
		baseDAO.update(targetObj);
	}
	
	/**
	 * <p>Discription:[获取要处理的目标对象]</p>
	 * Created on 2017年3月8日
	 * @param currentObj
	 * @param list
	 * @param isUp
	 * @param sortFiledName
	 * @return
	 * @author:[左仁智]
	 */
	public static Object getTargetObject(Object currentObj,List list,Boolean isUp,final String sortFiledName){
		
		sortListByFieldName(list, sortFiledName);
		
		List<Integer> listSort = new ArrayList<Integer>();
		for (Object object : list) {
			listSort.add(Integer.valueOf(ReflectionUtils.getFiledValue(object, sortFiledName).toString()));
		}
		
		int thisSort = Integer.valueOf(ReflectionUtils.getFiledValue(currentObj, sortFiledName).toString());
		
		int thisIndex = listSort.indexOf(thisSort);
		if(thisIndex == -1){
			return null;
		}
		
		if(isUp){
			thisIndex = thisIndex - 1;
		}else {
			thisIndex = thisIndex + 1;
		}
		if(thisIndex < 0 || thisIndex > listSort.size()){
			return null;
		}else {
			return list.get(thisIndex);
		}
	}

	/**
	 * <p>Discription:[根据排序字段对List进行排序]</p>
	 * Created on 2017年3月8日
	 * @param list
	 * @param sortFiledName
	 * @author:[左仁智]
	 */
	private static void sortListByFieldName(List list, final String sortFiledName) {
		Collections.sort(list,new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				return Integer.valueOf(ReflectionUtils.getFiledValue(o1, sortFiledName).toString())
						.compareTo(Integer.valueOf(ReflectionUtils.getFiledValue(o2, sortFiledName).toString()));
			}
		});
	}
}
