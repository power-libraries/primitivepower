package com.github.powerlibraries.primitive.collections;

import java.util.Collection;
import java.util.function.IntFunction;
import java.util.stream.LongStream;

public interface LongCollection extends Collection<Long>, LongIterable {

	boolean containsLong(long o);
	
	Long[] toArray(IntFunction<Long[]> p);

	long[] toLongArray();

	boolean addLong(long e);

	boolean removeLong(long o);

	boolean containsAllLongs(LongCollection c);

	boolean addAllLongs(LongCollection c);

	boolean removeAllLongs(LongCollection c);

	boolean retainAllLongs(LongCollection c);
	
	LongStream streamLongs();

	LongStream parallelStreamLongs();
	
}