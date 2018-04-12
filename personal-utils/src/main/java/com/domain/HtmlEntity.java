package com.domain;

/**
 * <p>Description: [静态化实体]</p>
 * Created on 2017年3月14日
 * @author  <a href="mailto: zuorenzhi@clt.com">zuorenzhi</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class HtmlEntity {
	
	/** 模板文件名称(如，xxx.ftl) */
	private String templateName;
	/** html文件路径(如，demo.html、demo/demo.html) */
	private String htmlFilePath;
	
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getHtmlFilePath() {
		return htmlFilePath;
	}
	public void setHtmlFilePath(String htmlFilePath) {
		this.htmlFilePath = htmlFilePath;
	}
	
}
