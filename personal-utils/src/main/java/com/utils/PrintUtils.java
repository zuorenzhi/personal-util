package com.utils;

public class PrintUtils {
	
	/**
	 * <p>Discription:[System.out.printf]</p>
	 * Created on 2017年2月16日
	 * @param msg
	 * @param objects
	 * @author:[左仁智]
	 */
	public static void sysout(String msg,Object ...objects ){
		System.out.printf(msg,objects);
	}
	
	public static void main(String[] args) {
		sysout("你-%s-%s-%s%n", 1,23,"不错");
		String [] arr = {"2","3","6"};
		sysout("你-%s-%s-%s%n",arr);
		sysout("你-%s-%s-%s%n", new String[] {"2","3","6"});
	}

}
