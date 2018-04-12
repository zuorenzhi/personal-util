package com.freemarker;

import java.util.Map;

/**
 * <p>Description: [静态化模板接口]</p>
 * Created on 2017年3月14日
 * @author  <a href="mailto: zuorenzhi@clt.com">zuorenzhi</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public interface HtmlBaseService {

	/**
	 * 
	 * <p>Discription:[根据模板生成html文件.当参数不合法，或静态化失败时，抛出RuntimeException]</p>
	 * Created on 2016年11月25日
	 * @param htmlEntity 生成配置:1.要求所有字段不能为null；2.要求所有字段赋值，符合字段描述
	 * @param dynamicParameters 动态参数，用于填充模板
	 * @return 返回html绝对路径
	 * @author[zuorenzhi]
	 */
	public String toHtml(HtmlEntity htmlEntity, Map<String, Object> dynamicParameters);

}