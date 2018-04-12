package com.utils.svn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @see http://www.oschina.net/question/158170_34997/
 * 
 * <p>Description: [svn的安装工具类:生成需要追加的配置文件代码]</p>
 * Created on 2017年4月16日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class SvnConfigUtils {

	public SvnConfigUtils() {
	}

	public void print(String path) {
		List<String> list = getFileList(path);
		if (list == null) {
			return;
		}
		int length = list.size();
		for (int i = 0; i < length; i++) {
			String result = "";
			String thePath = getFormatPath(getString(list.get(i)));
			File file = new File(thePath);
			if (file.isDirectory()) {
				String fileName = file.getName();
				if (fileName.indexOf("_") < 0) {
					print(thePath);
					continue;
				}
				String[] filenames = fileName.split("_");
				String filename1 = filenames[0];
				String filename2 = filenames[1];
				result = filename1 + "," + filename2 + ",file:/" + path + "/"
						+ fileName + "//,4,false";
				System.out.println(result);
			} else if (file.isFile()) {
				String fileName = file.getName();
				if (fileName.indexOf("_") < 0) {
					continue;
				}
				int last = fileName.lastIndexOf("_");// 最后一个下划线的位置
				String filename1 = fileName.substring(0, last);
				String filename2 = fileName.substring(last + 1,
						fileName.length() - 4);
				result = filename1 + "," + filename2 + ",file:/" + path + "/"
						+ fileName + ",4,false";
				System.out.println(result);
			}
		}
	}

	public List<String> getFileList(String path) {
		path = getFormatPath(path);
		path = path + "/";
		File filePath = new File(path);
		if (!filePath.isDirectory()) {
			return null;
		}
		String[] filelist = filePath.list();
		List<String> filelistFilter = new ArrayList<String>();
		for (int i = 0; i < filelist.length; i++) {
			String tempfilename = getFormatPath(path + filelist[i]);
			filelistFilter.add(tempfilename);
		}
		return filelistFilter;
	}

	public String getString(Object object) {
		if (object == null) {
			return "";
		}
		return String.valueOf(object);
	}

	public String getFormatPath(String path) {
		path = path.replaceAll("////", "/");
		path = path.replaceAll("//", "/");
		return path;
	}

	public static void main(String[] args) {
		/* 你的插件的安装目录 */
//		String plugin = "改成安装目录//Genuitec//svn";
		String plugin = "D:/myplugins/svn/";
		new SvnConfigUtils().print(plugin);
	}

}
