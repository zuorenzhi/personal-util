package com.gof.simplefactory.base;


public class SubOpearation extends Opearation {

	@Override
	public double getResult() {
		double result = 0;
		result = this.getFirstNum() - this.getSecondNum();
		return result;
	}
}
