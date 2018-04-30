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
	
	@Override
	ShortListIterator listIterator();
	
	@Override
	ShortListIterator listIterator(int index);
	
	void sort();
	
	void parallelSort();
	
	@Override
	default Iterable<ShortPointer> primitiveIterable() {
		return primitiveIterable(0);
	}
	
	Iterable<ShortPointer> primitiveIterable(int index);
	
	void reverse();
	
	@Override @Deprecated
	Short get(int index);

	@Override @Deprecated
	Short set(int index, Short element);

	@Override @Deprecated
	void add(int index, Short element);

	@Override @Deprecated
	int indexOf(Object o);

	@Override @Deprecated
	int lastIndexOf(Object o);
}