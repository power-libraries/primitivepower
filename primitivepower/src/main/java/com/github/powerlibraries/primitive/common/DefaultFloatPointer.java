package com.github.powerlibraries.primitive.common;

public class DefaultFloatPointer implements FloatPointer {

	private float value;
	
	public void set(float value) {
		this.value = value;
	}

	public float get() {
		return value;
	}
}
