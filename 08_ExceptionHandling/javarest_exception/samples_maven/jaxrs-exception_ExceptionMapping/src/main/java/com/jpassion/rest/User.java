package com.jpassion.rest;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {
    "name", "age"}
)
public class User {
	private String name;
	private int age;
	
	public User(){		
	}
        
        public User (String name, int age){
            this.name = name;
            this.age = age;
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

