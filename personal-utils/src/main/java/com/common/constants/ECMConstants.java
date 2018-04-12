package com.common.constants;

import camelot.props.PropertyUtils;

/**
 * <p>Description: [存放简易常量的类]</p>
 * Created on 2017年3月8日
 * @author  <a href="mailto: zuorenzhi@clt.com">zuorenzhi</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public interface ECMConstants {
	
	/**
	 * 排序字段
	 */
	public static final String SORTNAME = "sort";
	
	
	//**************************************************************************************
	
	/**
	 * ecm通用图片显示地址
	 */
	public static final String IMAGE_SHOW_URL = PropertyUtils.getProperty("ftp_server_dir") + PropertyUtils.getProperty("ftp_imgs_ecm");
	/**
	 * cdrf-web:官网访问地址ip
	 */
	public static final String CDRF_WEB_SERVER_IP = PropertyUtils.getProperty("cdrf_web_server_ip");
	
	
	/**
	 * 用户默认头像地址
	 */
	public static final String DEFAULT_USER_IMG_URL = PropertyUtils.getProperty("ftp_server_dir") + PropertyUtils.getProperty("ftp_user_default_img");
	/**
	 * 用户默认密码
	 */
	public static final String WEB_USER_DEFAULT_PASSWORD = PropertyUtils.getProperty("web_user_default_password");
    /** ecm静态网页存放根路径 */
    public static final String HTML_ROOT_PATH = PropertyUtils.getProperty("html_root_path");
    /**
     * 批量导入用户文件上传路径
     */
    public static final String FILEPATHFILE = "uploadFiles/file/"; // 文件上传路径
    //**************************************************************************************
	/**
	 * topic 图片封面路径
	 */
	public static final String TOPIC_PIC_SMTP_KEY = PropertyUtils.getProperty("ftp_server_dir") + PropertyUtils.getProperty("ftp_imgs_ecm_bk");
	
	/**
	 * PDF默认图片地址
	 */
	public static final String DEFAULT_COURSE_PDF_COVER_IMG_URL = PropertyUtils.getProperty("ftp_server_dir") + PropertyUtils.getProperty("course_pdf_cover_img");
}
