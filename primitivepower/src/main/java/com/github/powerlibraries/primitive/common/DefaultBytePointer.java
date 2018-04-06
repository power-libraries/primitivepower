package com.github.powerlibraries.primitive.common;

public class DefaultBytePointer implements BytePointer {

	private byte value;
	
	public void set(byte value) {
		this.value = value;
	}

	public byte get() {
		return value;
	}
}
