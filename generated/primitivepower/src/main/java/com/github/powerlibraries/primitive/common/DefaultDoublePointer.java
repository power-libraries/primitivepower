package com.github.powerlibraries.primitive.common;

public class DefaultDoublePointer implements DoublePointer {

	private double value;
	
	public void set(double value) {
		this.value = value;
	}

	public double get() {
		return value;
	}
}
