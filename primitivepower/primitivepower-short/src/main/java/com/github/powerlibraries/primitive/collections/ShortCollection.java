package com.github.powerlibraries.primitive.collections;

import java.util.Collection;

import java.util.function.IntFunction;

public interface ShortCollection extends Collection<Short>, ShortIterable {

	boolean containsShort(short o);
	
	Short[] toArray(IntFunction<Short[]> p);

	short[] toShortArray();

	boolean addShort(short e);

	boolean removeShort(short o);

	boolean containsAllShorts(ShortCollection c);

	boolean addAllShorts(ShortCollection c);

	boolean removeAllShorts(ShortCollection c);

	boolean retainAllShorts(ShortCollection c);
	
}