package com.JDK.constructor;

public class BuilderTest {
	
	public static void main(String[] args) {
		Person person = Person.getBeanInstance(2, 23);
		System.out.println(person.toString());
		
		
		//builder
		PersonBuilder.Builder builder = new PersonBuilder.Builder(2, 22);

		PersonBuilder personBuilderI = builder.builder();

		PersonBuilder personBuilderII = new PersonBuilder.Builder(2, 22).name("gaga").isAdult(true).isMarraied(false).builder();
		
	}

}
