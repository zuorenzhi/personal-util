package com.camelot.fileUpload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;

/**
 * <p>Description: [FTP文件上传工具类]</p>
 * Created on 2017年3月10日
 * @author  <a href="mailto: zuorenzhi@camelotchina.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class FTPUtils {
    private static Log LOG = LogFactory.getLog(FTPUtils.class);
    
	private String url;
	private int port = 21;
	private String username;
	private String password;
	
	private FTPClient ftpClient = null;

	/**
	 * <p>Discription:[构造器]</p>
	 * @coustructor 方法.
	 */
	public FTPUtils(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public FTPUtils(String url, int port, String username, String password) {
		this.url = url;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * <p>Discription:[上传文件]</p>
	 * Created on 2017年3月10日
	 * @param path FTP服务器工作目录下的保存目录,如果是根目录则为"/"；如果目录不存在，会自动创建,
	 * 如：/home/image_server/imgs/ecm, 其中image_server是ftp的工作目录
	 * @param inputStream
	 * @param fileSuffix,文件后缀 如:jpg,png
	 * @return
	 * @author:[左仁智]
	 */
	public String upload(String path, InputStream inputStream, String fileSuffix) {
        LOG.info("FTP上传文件开始...");
		path += getDirs();
		String remoteName = UUID.randomUUID().toString() + fileSuffix;
        boolean isFinish = false;
		try {
			this.ftpClient = new FTPClient();
            LOG.info("开始连接FTP服务器："+this.url+":"+this.port);
			this.ftpClient.connect(this.url, this.port);
            LOG.info("FTP服务器连接成功，开始登录FTP服务器...");
			this.ftpClient.login(this.username, this.password);
            LOG.info("FTP服务器登录成功，开始更改操作的目录...");
			// 转移工作目录至指定目录下 
			this.changeMakeWorkingDir(path);
			this.ftpClient.setBufferSize(1024);
			this.ftpClient.setControlEncoding("GBK");
            ftpClient.enterLocalPassiveMode();
			//设置文件类型（二进制）
			this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            LOG.info("开始往FTP服务器上传文件...");
            isFinish = this.ftpClient.storeFile(new String(remoteName.getBytes("GBK"),"iso-8859-1"), inputStream);
            if (isFinish){
                LOG.info("FTP服务器文件上传成功");
            }else {
                LOG.info("FTP服务器文件上传失败");
                return null;
            }
		} catch (Exception e) {
            LOG.info("往FTP服务器上传文件失败："+e);
			return null;
		} finally {
			try {
                if (isFinish){
                    inputStream.close();
                }
			} catch (IOException e1) {
				LOG.error("关闭文件流出错",e1);
			}
			if (this.ftpClient.isConnected()) {
				try {
					this.ftpClient.disconnect();
				} catch (IOException e) {
                    LOG.error("关闭FTP客户端出错");
				}
			}
		}
		return getDirs() + remoteName;
	}

	/**
	 * <p>Discription:[获取系统时间组成的文件夹路径"/年/月/日/"]</p>
	 * Created on 2017年3月10日
	 * @return dirsPath
	 * @author:[左仁智]
	 */
	private String getDirs() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return "/" + year + "/" + month + "/" + day + "/";//图片存储分年月日文件夹存储
	}

	/**
	 * 
	 * <p>Discription:[切换目录，如果目录不存在会自动创建]</p>
	 * Created on 2015年3月2日
	 * @param path 要切换的工作区路径
	 * @throws IOException
	 * @author:[Goma 郭茂茂]
	 */
	private void changeMakeWorkingDir(String path) throws IOException {
		String[] dirs = path.split("/");
		for (String dir : dirs) {
			dir = new String(dir.getBytes("GBK"), "iso-8859-1");
			if (dir != null && !"".equals(dir)) {
				this.ftpClient.makeDirectory(dir);
			}
			this.ftpClient.changeWorkingDirectory(dir);
		}
	}

	public String getUrl() {
		return url;
	}

	public int getPort() {
		return port;
	}
	
	
	
	public String uploadFile(String path, InputStream is,String fileName, String contentType) {
        LOG.info("FTP上传文件开始...");
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		long time = new Date().getTime();
		//图片存储分年月日 时间搓 文件夹存储
		String dir = "/" + year + "/" + month + "/" + day + "/" + time + "/";
		path += dir;
		//文件名
		String remoteName = fileName;
        boolean isFinish = false;
		try {

			this.ftpClient = new FTPClient();
            LOG.info("开始连接FTP服务器："+this.url+":"+this.port);
			this.ftpClient.connect(this.url, this.port);
            LOG.info("FTP服务器连接成功，开始登录FTP服务器...");
			this.ftpClient.login(this.username, this.password);
            LOG.info("FTP服务器登录成功，开始更改操作的目录...");
			// 转移工作目录至指定目录下 
			this.changeMakeWorkingDir(path);
			this.ftpClient.setBufferSize(1024);
			this.ftpClient.setControlEncoding("GBK");
            ftpClient.enterLocalPassiveMode();
			//设置文件类型（二进制）
			this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            LOG.info("开始往FTP服务器上传文件...");
            isFinish = this.ftpClient.storeFile(new String(remoteName.getBytes("GBK"),"iso-8859-1"), is);
            if (isFinish){
                LOG.info("FTP服务器文件上传成功");
            }else {
                LOG.info("FTP服务器文件上传失败");
                return null;
            }
		} catch (Exception e) {
            LOG.info("往FTP服务器上传文件失败："+e);
			return null;
		} finally {
			try {
                if (isFinish){
                    is.close();
                }
			} catch (IOException e1) {
				LOG.error("关闭文件流出错",e1);
			}
			if (this.ftpClient.isConnected()) {
				try {
					this.ftpClient.disconnect();
				} catch (IOException e) {
                    LOG.error("关闭FTP客户端出错");
				}
			}
		}
		return dir + remoteName;
	}
}
