package com.jpassion.rest.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String s) {
		super(s);
	}

}