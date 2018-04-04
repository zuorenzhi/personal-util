package com.JDK.constructor;

public class Constructor {

}
//�ص�������
class Person{
	private final int id;
	private final Integer age;
	private final String name;
	private final boolean isAdult;
	private final Boolean isMarraied;
	
	//��С��λ�Ǳ�Ҫ����Ĺ�����
	public Person(int id, Integer age) {
		this(id,age,null);
	}
	public Person(int id, Integer age, String name) {
		this(id,age,name,false);
	}
	public Person(int id, Integer age, String name, boolean isAdult) {
		this(id,age,name,isAdult,false);
	}
	public Person(int id, Integer age, String name, boolean isAdult,Boolean isMarraied) {
		this.id = id;
		this.age = age;
		this.name = name;
		this.isAdult = isAdult;
		this.isMarraied = isMarraied;
	}
	
	public static Person getBeanInstance(int id, Integer age){
		return new Person(id,age);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[id="+id+",age="+age+"]";
	}
}


