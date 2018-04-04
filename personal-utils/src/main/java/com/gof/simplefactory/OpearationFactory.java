package com.gof.simplefactory;

import com.gof.simplefactory.base.AddOpearation;
import com.gof.simplefactory.base.Opearation;
import com.gof.simplefactory.base.SubOpearation;
import com.gof.simplefactory.base.DevideOpearation;
import com.gof.simplefactory.base.MultiOpearation;

public class OpearationFactory {
	
	
	public static Opearation createOpearation(String oper){
		Opearation opearation = null;
		switch (oper) {
		case "+":
			opearation = new AddOpearation();
			break;
		case "-":
			opearation = new SubOpearation();
			break;
		case "*":
			opearation = new MultiOpearation();
			break;
		case "/":
			opearation = new DevideOpearation();
			break;
		default:
			break;
		}
		return opearation;
	}

}
