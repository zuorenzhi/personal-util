package com.utils.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 * <p>Description: [ishugui:getImageNames from website]</p>
 * Created on 2017年4月16日
 * @author  <a href="mailto: zuorenzhi@camelotchina.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class ImageGrabUtils {  
  
    private static final String URL = "http://www.sohu.com";  
//    private static final String URL = "http://www.baidu.com";  
//    private static final String URL = "http://www.camelotchina.com";  
    private static final String ECODING = "UTF-8";  
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";  
    private static final String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)";  
  
      
    public static void main(String[] args) throws Exception {  
        ImageGrabUtils cm = new ImageGrabUtils();  
        String HTML = cm.getHTML(URL);  
        List<String> imgUrl = cm.getImageUrl(HTML);  
        List<String> imgSrc = cm.getImageSrc(imgUrl);  
        cm.Download(imgSrc);  
    }


    /**
     * <p>Discription:[get form html]</p>
     * Created on 2017年5月5日
     * @param url
     * @return
     * @throws Exception
     * @author:[zuorenzhi]
     */
    private String getHTML(String url) throws Exception {
        URL uri = new URL(url);
        URLConnection connection = uri.openConnection();
        InputStream in = connection.getInputStream();
        byte[] buf = new byte[1024];
        int length = 0;
        StringBuffer sb = new StringBuffer();
        while ((length = in.read(buf, 0, buf.length)) > 0) {
            sb.append(new String(buf, ECODING));
        }
        in.close();
        return sb.toString();
    }

    /**
     * <p>Discription:[get img list]</p>
     * Created on 2017年5月5日
     * @param listImageUrl
     * @return
     * @author:[zuorenzhi]
     */
    private List<String> getImageSrc(List<String> listImageUrl) {
        List<String> listImgSrc = new ArrayList<String>();
        for (String image : listImageUrl) {
            Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()) {
                listImgSrc.add(matcher.group().substring(0, matcher.group().length() - 1));
            }
        }
        return listImgSrc;
    }

    /**
     * <p>Discription:[get img...dom]</p>
     * Created on 2017年5月5日
     * @param HTML
     * @return
     * @author:[zuorenzhi]
     */
    private List<String> getImageUrl(String HTML) {
        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(HTML);
        List<String> listImgUrl = new ArrayList<String>();
        while (matcher.find()) {
            listImgUrl.add(matcher.group());
        }
        return listImgUrl;
    }

    /**
     * <p>Discription:[单张图片抓取到本地]</p>
     * Created on 2017年4月16日
     * @param imgUrl
     * @param filePath
     * @return
     * @author:[左仁智]
     */
    public static boolean grabImgByUrl(String imgUrl, String filePath) {
        boolean flag = true;
        try {
            URL url = new URL(imgUrl);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            FileUtils.copyInputStreamToFile(is, new File(filePath));
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    /***
     * @param listImgSrc
     */
    private void Download(List<String> listImgSrc) {
        try {
            for (String url : listImgSrc) {
                String imageName = url.substring(url.lastIndexOf("/") + 1, url.length());
                URL uri = new URL(url);
                InputStream in = uri.openStream();
                FileOutputStream fo = new FileOutputStream(new File(imageName));
                byte[] buf = new byte[1024];
                int length = 0;
                System.out.println("full img url :" + url);
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    fo.write(buf, 0, length);
                }
                in.close();
                fo.close();
                System.out.println(imageName);
            }
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

	@Test
	public void test() throws IOException{
//		String s1="http://t10.baidu.com/it/u=638074416,619318464&fm=76";
		String s1="http://117.79.225.161/covers_dz/20130704001.jpg";
		String s2="E:/Test/wode12.jpg";
		grabImgByUrl(s1, s2);
	}

}  