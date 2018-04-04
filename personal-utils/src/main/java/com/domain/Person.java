
package com.domain;

import lombok.Data;

@Data
public class Person {

	private Integer id;
	private String name;
	private Integer age;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}
	
	public Person(Integer id, String name) {
		this(id, name, null);
	}

	public Person(Integer id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public static void main(String[] args) {
		Person person = new Person(12,"张三",44);
		System.out.printf("%d-%s-%d%n",person.id,person.name,person.age);
		System.out.printf("%d-%s-%d",person.id,person.getName(),person.getAge());
		System.out.println(person.toString());
//		System.out.println(JSON.toJSONString(person));
	}
}
