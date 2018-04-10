package com.github.powerlibraries.primitive.collections;

import java.util.Collection;

import java.util.function.IntFunction;

public interface ObjectCollection<E> extends Collection<E>, ObjectIterable<E> {

	boolean containsObject(E o);
	
	E[] toArray(IntFunction<E[]> p);

	Object[] toObjectArray();

	boolean addObject(E e);

	boolean removeObject(E o);

	boolean containsAllObjects(ObjectCollection<? extends E> c);

	boolean addAllObjects(ObjectCollection<? extends E> c);

	boolean removeAllObjects(ObjectCollection<? extends E> c);

	boolean retainAllObjects(ObjectCollection<? extends E> c);
	
}