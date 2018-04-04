
package com.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDTO implements Serializable{

	private static final long serialVersionUID = 8578341329736214045L;
	private Integer id;
	private String name;

	public PersonDTO() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(2);
		personDTO.setName("人");
		System.out.println(personDTO.toString());
	}
}
