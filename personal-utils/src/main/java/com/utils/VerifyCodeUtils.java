package com.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
* Description: 验证码工具类
* Created on 2017/8/4 10:59
* @author  zuorenzhi
* Copyright (c) 2017年 国美融通科技
*/
public class VerifyCodeUtils {

    private int w = 80;
    private int h = 20;
    // 验证码上的文本
    private String text;

    /**
     * 调用这个方法得到验证码
     *
     * @return
     */
    public BufferedImage getImage() {
        // 创建字体，字体的大小应该根据图片的高度来定
        List<Font> fonts = new ArrayList<Font>();
        initFonts(fonts);

        // 创建具有可访问图像数据缓冲区的Image
        BufferedImage buffImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);

        // 画边框
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, w - 1, h - 1);

        // 产生随机的颜色分量来构造颜色值
        randomColor(g);

        //画干扰线
        drawLines(g);

        // randomCode 用于保存随机产生的验证码
        StringBuilder randomCode = new StringBuilder();
        // 设置备选验证码:包括"a-z"和数字"0-9"
        String base = "abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789";
        // 随机产生4位验证码
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            // 得到随机产生的验证码字符
            int start = random.nextInt(base.length());
            String strRand = base.substring(start, start + 1);
            // 产生随机的颜色分量来构造颜色值
            int red = random.nextInt(160);
            int green = random.nextInt(160);
            int blue = random.nextInt(160);
            // 随机设置字体
            g.setFont(fonts.get(random.nextInt(fonts.size())));
            // 用随机产生的颜色将验证码绘制到图像中
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, i * 1.0F * w / 4, h - 5);
            randomCode.append(strRand);
        }
        this.text = randomCode.toString(); //把生成的字符串赋给了this.text
        return buffImg;
    }

    /**
     * 生成随机的颜色
     *
     * @return
     */
    private void randomColor(Graphics2D g) {
        Random random = new Random();
        int red = 0, green = 0, blue = 0;
        red = random.nextInt(80) + 150;
        green = random.nextInt(80) + 150;
        blue = random.nextInt(80) + 150;
        g.setColor(new Color(red, green, blue));
    }

    /**
     * 画干扰线
     *
     * @param g
     */
    private void drawLines(Graphics2D g) {
        Random random = new Random();
        // 随机产生150条干扰线
        for (int i = 0; i < 150; i++) {
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }
    }

    /**
     * 初始化字体
     *
     * @param fonts
     */
    private void initFonts(List<Font> fonts) {
        fonts.add(new Font("Lucida Bright", Font.PLAIN | Font.ITALIC, 26));
        fonts.add(new Font("Lucida Bright", Font.PLAIN, 26));
        fonts.add(new Font("Lucida Bright", Font.PLAIN | Font.ITALIC, 24));
        fonts.add(new Font("Lucida Bright", Font.PLAIN, 24));
        fonts.add(new Font("Lucida Bright", Font.PLAIN | Font.ITALIC, 20));
        fonts.add(new Font("Lucida Bright", Font.PLAIN, 20));
        fonts.add(new Font("Lucida Bright", Font.PLAIN | Font.ITALIC, 18));
        fonts.add(new Font("Lucida Bright", Font.PLAIN, 18));
    }

    /**
     * 返回验证码图片上的文本
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * 保存图片到指定的输出流
     *
     * @param image
     * @return
     * @throws IOException
     */
    public static String output(BufferedImage image) throws IOException {
        ByteArrayOutputStream tmp = new ByteArrayOutputStream();
        ImageIO.write(image, "png", tmp);
        tmp.close();
        String result = "";
//	            result = com.gomefinance.mj.webapi.own.modules.utils.Base64.encode(tmp.toByteArray());// 将内存中的图片通过流动形式输出到客户端
        return result;
    }

    public static void main(String[] args) throws Exception {
        VerifyCodeUtils verifyCode = new VerifyCodeUtils();
        BufferedImage bi = verifyCode.getImage();
        System.out.println(verifyCode.getText());
        FileOutputStream tmp = new FileOutputStream(new File("d:/a.png"));
        ImageIO.write(bi, "png", tmp);
        tmp.close();
    }

}