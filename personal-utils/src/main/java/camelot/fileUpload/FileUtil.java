package camelot.fileUpload;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * <p>Description: [文件工具类]</p>
 * Created on 2016-4-5
 * @author  <a href="mailto: wuchaoqiang@clt.com">武超强</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
public class FileUtil {
	/**
	 * 图片
	 */
	private Image img;
	/**
	 * 宽度
	 */
    private int width;
    /**
     * 高度
     */
	private int height;
	/**
	 * 大小
	 */
	private long size;
	/**
	 * 文件名
	 */
	private String fileName;

	public FileUtil(){}
	
    public FileUtil(String fileName) throws IOException {
        File file = new File(fileName);// 读入文件
        this.img = ImageIO.read(file);      // 构造Image对象
        this.width = img.getWidth(null);    // 得到源图宽
        this.height = img.getHeight(null);  // 得到源图长
        this.size = file.length();
        this.fileName = fileName;
    }
    
    public FileUtil(File file) throws IOException {
        this.img = ImageIO.read(file);      // 构造Image对象
        this.width = img.getWidth(null);    // 得到源图宽
        this.height = img.getHeight(null);  // 得到源图长
        this.size = file.length();
        this.fileName = file.getName();
    }
    
    /**
     * <p>Discription:[获取文件宽度]</p>
     * Created on 2016-4-5
     * @return
     * @author:武超强
     */
    public int getWidth() {
		return width;
	}

    /**
     * <p>Discription:[获取文件高度]</p>
     * Created on 2016-4-5
     * @return
     * @author:武超强
     */
	public int getHeight() {
		return height;
	}

	/**
	 * <p>Discription:[获取文件大小]</p>
	 * Created on 2016-4-5
	 * @return
	 * @author:武超强
	 */
	public long getSize() {
		return size;
	}
	
	/**
	 * <p>Discription:[获取文件后缀名]</p>
	 * Created on 2016-4-5
	 * @return
	 * @author:武超强
	 */
	public String getSuffix() {
		String suffix = this.fileName.substring(this.fileName.lastIndexOf("."), this.fileName.length());
		return suffix;
	}
	
	/**
	 * 
	 * <p>Discription:[验证上传文件类型是否属于图片格式]</p>
	 * Created on 2016-4-5
	 * @param file
	 * @return
	 * @author:武超强
	 */
    public static final boolean isImage(File file){  
        boolean flag = false;  
        try  
        {  
            BufferedImage bufreader = ImageIO.read(file);  
            int width = bufreader.getWidth();  
            int height = bufreader.getHeight();  
            if(width==0 || height==0){  
                flag = false;  
            }else {  
                flag = true;  
            }  
        }  
        catch (IOException e)  
        {  
            flag = false;  
        }catch (Exception e) {  
            flag = false;  
        }  
        return flag;  
    }
    
    /**
     * <p>Discription:[判断图片后缀名是否在指定的后缀名数组中]</p>
     * Created on 2016-4-5
     * @param file
     * @param exts
     * @return
     * @author:武超强
     */
    public static final boolean rightSuffix(File file, String[] exts){
    	boolean flag = false;
    	if(null == file){
    		return flag;
    	}
    	String fileName = file.getName();
    	String suffix = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
    	List<String> extList = Arrays.asList(exts);
    	
    	return extList.contains(suffix.toLowerCase());
    }
}
