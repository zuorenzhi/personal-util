package com.common.enums;


/**
 * 
 * <p>Description: [枚举实例]</p>
 * Created on 2016-8-22
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
public class UtilEnums {
	
	public enum sexEnum{
		
		MAN(1,"男"),
		WOMEN(2,"女");
		
		private int num;
		private String sex;
		
		private sexEnum(int num, String sex) {
			this.num = num;
			this.sex = sex;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		
		public static sexEnum getByNum(int num){
			for (sexEnum sexType2 : sexEnum.values()) {
				if(sexType2.getNum() == num){
					return sexType2;
				}
			}
			return null;
		}
		public static String getSexByNum(sexEnum senum){
			for (sexEnum sexType2 : sexEnum.values()) {
				if(sexType2 == senum){
					return sexType2.sex;
				}
			}
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(UtilEnums.sexEnum.MAN.sex);
		System.out.println(UtilEnums.sexEnum.MAN.getNum());
		System.out.println(UtilEnums.sexEnum.MAN.getSex());
		System.out.println(UtilEnums.sexEnum.getByNum(1));
		System.out.println(UtilEnums.sexEnum.getByNum(2).sex);
		
	}

}
