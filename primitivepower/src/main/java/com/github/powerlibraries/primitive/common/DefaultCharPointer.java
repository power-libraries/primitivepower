package com.github.powerlibraries.primitive.common;

public class DefaultCharPointer implements CharPointer {

	private char value;
	
	public void set(char value) {
		this.value = value;
	}

	public char get() {
		return value;
	}
}
