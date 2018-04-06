package com.github.powerlibraries.primitive.collections;

import java.util.Collection;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public interface IntCollection extends Collection<Integer>, IntIterable {

	boolean containsInt(int o);
	
	Integer[] toArray(IntFunction<Integer[]> p);

	int[] toIntArray();

	boolean addInt(int e);

	boolean removeInt(int o);

	boolean containsAllInts(IntCollection c);

	boolean addAllInts(IntCollection c);

	boolean removeAllInts(IntCollection c);

	boolean retainAllInts(IntCollection c);
	
	IntStream streamInts();

	IntStream parallelStreamInts();
	
}