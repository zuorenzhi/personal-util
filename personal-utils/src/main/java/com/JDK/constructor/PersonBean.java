package com.JDK.constructor;

public class PersonBean {

	private int id;
	private Integer age;
	private String name;
	private boolean isAdult;
	private Boolean isMarraied;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAdult() {
		return isAdult;
	}
	public void setAdult(boolean isAdult) {
		this.isAdult = isAdult;
	}
	public Boolean getIsMarraied() {
		return isMarraied;
	}
	public void setIsMarraied(Boolean isMarraied) {
		this.isMarraied = isMarraied;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[id="+id+",age="+age+"]";
	}
}
