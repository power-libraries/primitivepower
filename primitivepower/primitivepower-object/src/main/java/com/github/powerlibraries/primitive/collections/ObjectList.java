package com.github.powerlibraries.primitive.collections;

import java.util.List;

import com.github.powerlibraries.primitive.common.ObjectPointer;

public interface ObjectList<E> extends List<E>, ObjectCollection<E> {

	E getObject(int index);

	E setObject(int index, E element);

	void addObject(int index, E element);

	int indexOfObject(E o);

	int lastIndexOfObject(E o);

	@Override
	ObjectList<E> subList(int fromIndex, int toIndex);
	
	

	@Override
	default Iterable<ObjectPointer<E>> primitiveIterable() {
		return primitiveIterable(0);
	}
	
	Iterable<ObjectPointer<E>> primitiveIterable(int index);
		
}