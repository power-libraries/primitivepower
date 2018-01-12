package com.github.powerlibraries.primitive.collections;

import java.util.List;

public interface LongList extends List<Long>, LongCollection {

	//TODO
	/*
	default void replaceAll(UnaryOperator<E> operator) {
		Objects.requireNonNull(operator);
		final ListIterator<E> li = this.listIterator();
		while (li.hasNext()) {
			li.set(operator.apply(li.next()));
		}
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	default void sort(Comparator<? super E> c) {
		Object[] a = this.toArray();
		Arrays.sort(a, (Comparator) c);
		ListIterator<E> i = this.listIterator();
		for (Object e : a) {
			i.next();
			i.set((E) e);
		}
	}*/
	
	long getLong(int index);

	long setLong(int index, long element);

	void addLong(int index, long element);

	int indexOfLong(long o);

	int lastIndexOfLong(long o);

	@Override
	LongList subList(int fromIndex, int toIndex);
	
	
	public void sort();
	
	public void parallelSort();
	
}