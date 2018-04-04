package com.camelot.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: [ajax返回前台信息vo]</p>
 * Created on 2016年3月31日
 * @author  <a href="mailto: liuchao@camelotchina.com">刘超</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部 
 */
@Setter
@Getter
public class AjaxInfo {
	
	public AjaxInfo() {}
	{
		this.code = 0;//默认成功
		this.msg = "请求成功";//默认请求成功信息
	}
	/**
	 * <p>Discription:[0:成功，其他失败]</p>
	 */
	private Integer code;
	/**
	 * <p>Discription:[请求返回信息]</p>
	 */
	private String msg;
	/**
	 * <p>Discription:[请求返回数据]</p>
	 */
	private Object data;

	public AjaxInfo(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}


}
