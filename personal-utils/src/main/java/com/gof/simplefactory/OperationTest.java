package com.gof.simplefactory;

import com.gof.simplefactory.base.Opearation;

public class OperationTest {

	public static void main(String[] args) {
		
		Opearation opearation = OpearationFactory.createOpearation("+");
		opearation.setFirstNum(5);
		opearation.setSecondNum(9);
		System.out.println(opearation.getResult());
		Opearation devide = OpearationFactory.createOpearation("/");
		devide.setFirstNum(15);
		devide.setSecondNum(3);
		System.out.println(devide.getResult());
	}
	
}
