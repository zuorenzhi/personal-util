package com.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Student {

	private long id;
	private int age;
	private String info;
	private String address;
	
	public Student(long id, int age, String info, String address) {
		this.id = id;
		this.age = age;
		this.info = info;
		this.address = address;
	}

	public static void main(String[] args) {
		Student student = new Student(2, 2, "lala", "beijing");
		System.out.println(student.toString());
		System.out.println(student.getAddress());
		System.out.println(student.hashCode());
		log.info("----------lombok   log -------------------haha");
	}

	
}
