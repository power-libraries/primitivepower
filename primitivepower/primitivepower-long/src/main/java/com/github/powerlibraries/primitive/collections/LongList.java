package com.github.powerlibraries.primitive.collections;

import java.util.List;

import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.LongUnaryOperator;

import com.github.powerlibraries.primitive.common.LongPointer;

public interface LongList extends List<Long>, LongCollection {

	long getLong(int index);

	long setLong(int index, long element);

	void addLong(int index, long element);

	int indexOfLong(long o);

	int lastIndexOfLong(long o);

	@Override
	LongList subList(int fromIndex, int toIndex);
	
	
	void sort();
	
	void parallelSort();
	

	@Override
	default Iterable<LongPointer> primitiveIterable() {
		return primitiveIterable(0);
	}
	
	Iterable<LongPointer> primitiveIterable(int index);
	
	default void replaceAll(LongUnaryOperator operator) {
		Objects.requireNonNull(operator);
		final ListIterator<E> li = this.listIterator();
		while (li.hasNext()) {
			li.set(operator.apply(li.next()));
		}
	}
	
	@Override
	default Spliterator.OfLong spliterator() {
		return Spliterators.spliterator(iterator(), size(), Spliterator.ORDERED);
	}
		
}