package com.utils.file;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.io.File;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

public class ImageCompressUtils {

	/**
	 * <p>Discription:[图片压缩/放大]</p>
	 * Created on 2017年4月13日
	 * @param base
	 * @param imgPath
	 * @param width
	 * @param height
	 * @param response
	 * @return
	 * @author:[左仁智]
	 */
	public static String zoom(String base, String imgPath, int width,int height, HttpServletResponse response) {
		try {
			BufferedImage src = ImageIO.read(new File(base + imgPath));
	        AreaAveragingScaleFilter filter = new AreaAveragingScaleFilter(width, height);
	        FilteredImageSource source = new FilteredImageSource(src.getSource(), filter);
	        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
	        Graphics graphics = image.createGraphics();
	        graphics.drawImage(new Canvas().createImage(source), 0, 0, null);
	        String suffix = imgPath.substring(imgPath.lastIndexOf(".") + 1).toUpperCase();
	        String newFile = getNewPath(imgPath);
	        ImageIO.write(image, suffix, new File(base + newFile));
	        if (null != response) {
				ImageIO.write(image, suffix, response.getOutputStream());
			}
	        image.flush();
	        src.flush();
	        return newFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	// 根据原图片路径获得即将产生的路径
		public static String getNewPath(String imgPath) {
			String filePrex = imgPath.substring(0, imgPath.lastIndexOf('.'));
//			System.out.println(imgPath+"\n"+filePrex+"\n"+imgPath.substring(filePrex.length())+"\n"+filePrex + "tadu" + imgPath.substring(filePrex.length()));
			return filePrex + "tadu" + imgPath.substring(filePrex.length());
		}
		public static void main(String[] args) {
			zoom("e:/file2Bean/", "Image/kk.jpg", 200,250, null);// 放大或缩小图片
		}
}
