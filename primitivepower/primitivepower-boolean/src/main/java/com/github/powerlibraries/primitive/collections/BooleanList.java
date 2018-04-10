package com.github.powerlibraries.primitive.collections;

import java.util.List;

import com.github.powerlibraries.primitive.common.BooleanPointer;

public interface BooleanList extends List<Boolean>, BooleanCollection {

	boolean getBoolean(int index);

	boolean setBoolean(int index, boolean element);

	void addBoolean(int index, boolean element);

	int indexOfBoolean(boolean o);

	int lastIndexOfBoolean(boolean o);

	@Override
	BooleanList subList(int fromIndex, int toIndex);
	
	

	@Override
	default Iterable<BooleanPointer> primitiveIterable() {
		return primitiveIterable(0);
	}
	
	Iterable<BooleanPointer> primitiveIterable(int index);
		
}