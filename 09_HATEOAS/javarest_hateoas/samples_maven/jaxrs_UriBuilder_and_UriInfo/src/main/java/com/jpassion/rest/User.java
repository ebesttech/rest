package com.jpassion.rest;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "id", "name", "age" })
public class User {
	
	private int id;
	private String name;
	private int age;
	
	public User() {
	}
	
	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String Name) {
		this.name = Name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int Age) {
		this.age = Age;
	}
}
