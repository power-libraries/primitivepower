package com.github.powerlibraries.primitive.collections;

import java.util.List;

import com.github.powerlibraries.primitive.common.BooleanPointer;

public interface BooleanList extends List<Boolean>, BooleanCollection {

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
	
	boolean getBoolean(int index);

	boolean setBoolean(int index, boolean element);

	void addBoolean(int index, boolean element);

	int indexOfBoolean(boolean o);

	int lastIndexOfBoolean(boolean o);

	@Override
	BooleanList subList(int fromIndex, int toIndex);
	
	

	@Override
	default Iterable<BooleanPointer> primitiveIterable() {
		return primitiveIterable(0);
	}
	
	Iterable<BooleanPointer> primitiveIterable(int index);
}