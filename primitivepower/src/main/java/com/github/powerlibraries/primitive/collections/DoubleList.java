package com.github.powerlibraries.primitive.collections;

import java.util.List;

import com.github.powerlibraries.primitive.common.DoublePointer;

public interface DoubleList extends List<Double>, DoubleCollection {

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
	
	double getDouble(int index);

	double setDouble(int index, double element);

	void addDouble(int index, double element);

	int indexOfDouble(double o);

	int lastIndexOfDouble(double o);

	@Override
	DoubleList subList(int fromIndex, int toIndex);
	
	
	public void sort();
	
	public void parallelSort();
	

	default Iterable<DoublePointer> primitiveIterable() {
		return primitiveIterable(0);
	}
	
	Iterable<DoublePointer> primitiveIterable(int index);
}