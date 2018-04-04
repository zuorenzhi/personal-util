package com.gof.simplefactory.base;


public class DevideOpearation extends Opearation {

	@Override
	public double getResult(){
		double result = 0;
		if(this.getSecondNum() == 0){
			throw new RuntimeException("除数不能为0");
		}
		result = this.getFirstNum() / this.getSecondNum();
		return result;
	}
}
