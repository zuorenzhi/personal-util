package com.JDK.constructor;

/**
* Description : [builder pattern]
* Created on : 2017-11-24 16:20
* author : [左仁智]
* version : 1.0
* Copyright (c) 2017 国美金控-美借
*/
public class PersonBuilder {

	private final int id;
	private final Integer age;
	private final String name;
	private final boolean isAdult;
	private final Boolean isMarraied;
	
	public static class Builder{
		private final int id;
		private final Integer age;
		private String name = null;
		private boolean isAdult =false;
		private Boolean isMarraied = false;
		public Builder(int id, Integer age) {
			this.id = id;
			this.age = age;
		}
		public Builder name(String val){
			name = val;
			return this;
		}
		public Builder isAdult(boolean val){
			isAdult = val;
			return this;
		}
		public Builder isMarraied(Boolean val){
			isMarraied = val;
			return this;
		}
		
		public PersonBuilder builder(){
			return new PersonBuilder(this);
		}
	}
	
	private PersonBuilder(Builder builder){
		id = builder.id;
		age= builder.age;
		name = builder.name;
		isAdult = builder.isAdult;
		isMarraied = builder.isMarraied;
	}

	public int getId() {
		return id;
	}

	public Integer getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public boolean isAdult() {
		return isAdult;
	}

	public Boolean getIsMarraied() {
		return isMarraied;
	}

	public static void main(String[] args) {

		Builder builder = new Builder(2, 3).name("").isAdult(false).isMarraied(false);

		PersonBuilder personBuilder = new PersonBuilder(builder);

		System.out.println(personBuilder.getId());


		//2

		PersonBuilder personBuilderII = new Builder(2, 3).name("abc").builder();

	}
	
}
