package com.github.powerlibraries.primitive.collections;

import java.util.List;

public interface IntList extends List<Integer>, IntCollection {

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
	
	int getInt(int index);

	int setInt(int index, int element);

	void addInt(int index, int element);

	int indexOfInt(int o);

	int lastIndexOfInt(int o);

	@Override
	IntList subList(int fromIndex, int toIndex);
	
	
	public void sort();
	
	public void parallelSort();
	
}