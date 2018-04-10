package com.github.powerlibraries.primitive.collections;

import java.util.List;

import com.github.powerlibraries.primitive.common.ShortPointer;

public interface ShortList extends List<Short>, ShortCollection {

	short getShort(int index);

	short setShort(int index, short element);

	void addShort(int index, short element);

	int indexOfShort(short o);

	int lastIndexOfShort(short o);

	@Override
	ShortList subList(int fromIndex, int toIndex);
	
	
	void sort();
	
	void parallelSort();
	

	@Override
	default Iterable<ShortPointer> primitiveIterable() {
		return primitiveIterable(0);
	}
	
	Iterable<ShortPointer> primitiveIterable(int index);
		
}