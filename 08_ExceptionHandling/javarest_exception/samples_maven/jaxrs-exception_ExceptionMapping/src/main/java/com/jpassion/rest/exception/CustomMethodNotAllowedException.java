package com.jpassion.rest.exception;

public class CustomMethodNotAllowedException extends RuntimeException {
	public CustomMethodNotAllowedException(String s) {
		super(s);
	}
}