package com.JDK.constructor;

public class PersonFactory {

	public PersonBean getBeanInstance(){
		PersonBean personBean = new PersonBean();
		return personBean;
	}
}
