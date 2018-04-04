package com.camelot.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>Description: [DataGrid模型  存放查询的数量和list]</p>
 * Created on 2016-3-24
 * @author  <a href="mailto: wuchaoqiang@camelotchina.com">武超强</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
public class DataGrid<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long total = 0L;
	private List<T> rows = new ArrayList<T>();

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
