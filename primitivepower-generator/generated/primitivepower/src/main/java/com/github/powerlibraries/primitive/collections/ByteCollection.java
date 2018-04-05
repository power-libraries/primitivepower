package com.github.powerlibraries.primitive.collections;

import java.util.Collection;
import java.util.function.IntFunction;


public interface ByteCollection extends Collection<Byte>, ByteIterable {

	boolean containsByte(byte o);
	
	Byte[] toArray(IntFunction<Byte[]> p);

	byte[] toByteArray();

	boolean addByte(byte e);

	boolean removeByte(byte o);

	boolean containsAllBytes(ByteCollection c);

	boolean addAllBytes(ByteCollection c);

	boolean removeAllBytes(ByteCollection c);

	boolean retainAllBytes(ByteCollection c);
	
}