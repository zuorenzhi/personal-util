package com.camelot.dao;

import java.util.List;
import java.util.Map;

import com.camelot.bean.Pager;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * <p>Description: [公共DAO父级接口]</p>
 * Created on 2017年5月12日
 * @author  <a href="mailto: zuorenzhi@camelotchina.com">zuorenzhi</a>
 * @version 1.0 
 * Copyright (c) 2017 camelot jiaofubu
 */
public interface BaseDAO<T> {

	/**
	 * <p>Discription:[新增]</p>
	 * Created on 2016年3月29日
	 * @param t 新增的实体对象
	 * @author:[刘香平]
	 */
	public void add(T t);

	/**
	 * <p>Discription:[根据ID查询]</p>
	 * Created on 2016年3月29日
	 * @param id 主键编号
	 * @return
	 * @author:[刘香平]
	 */
	public T queryById(Object id);

	/**
	 * <p>Discription:[修改]</p>
	 * Created on 2016年3月29日
	 * @param t 需要修改的实体Bean
	 * @return
	 * @author:[刘香平]
	 */
	public Integer update(T t);

	/**
	 * <p>Discription:[根据ID删除数据]</p>
	 * Created on 2016年3月29日
	 * @param id 主键ID
	 * @return
	 * @author:[刘香平]
	 */
	public Integer delete(Object id);
	
	/**
	 * <p>Discription:[根据条件查询总数]</p>
	 * Created on 2016年3月29日
	 * @param t Bean对象查询条件
	 * @return 总数
	 * @author:[刘香平]
	 */
	public Long queryCount(@Param("entity") T t, @Param("paramMap")Map<String, Object> paramMap);
	
	/**
	 * <p>Discription:[根据条件进行分页查询]</p>
	 * Created on 2016年3月29日
	 * @param t Bean对象查询条件
	 * @param page 分页对象
	 * @param paramMap Bean实体外的查询参数
	 * @return Bean实体list集合
	 * @author:[刘香平]
	 */
	public List<T> queryList(@Param("entity") T t, @SuppressWarnings("rawtypes") @Param("page") Pager page, @Param("paramMap")Map<String, Object> paramMap);

}
