package com.github.powerlibraries.primitive.common;

public class DefaultBooleanPointer implements BooleanPointer {

	private boolean value;
	
	public void set(boolean value) {
		this.value = value;
	}

	public boolean get() {
		return value;
	}
}
