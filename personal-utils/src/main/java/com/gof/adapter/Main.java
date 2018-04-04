package com.gof.adapter;

/**
 * @see http://www.cnblogs.com/chenpi/p/5188384.html
 * <p>Description: [class description]</p>
 * Created on 2017年5月4日
 * @author  <a href="mailto: zuorenzhi@camelotchina.com">zuorenzhi</a>
 * @version 1.0 
 * Copyright (c) 2017 camelot jiaofubu
 */
public class Main {

	public static void main(String[] args) {
		IUSB usb = new HPUSB();
	    usb.charge();
	    
	    IUSB usb_psp = new PSPAdapter();//继承
	    usb_psp.charge();
	    
	    IUSB usb_psp2 = new PSPAdapter2(new PSP());//组合
	    usb_psp2.charge();
	}
}
