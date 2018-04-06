package com.github.powerlibraries.primitive.collections;

import java.util.List;

import com.github.powerlibraries.primitive.common.CharPointer;

public interface CharList extends List<Character>, CharCollection {

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
	
	char getChar(int index);

	char setChar(int index, char element);

	void addChar(int index, char element);

	int indexOfChar(char o);

	int lastIndexOfChar(char o);

	@Override
	CharList subList(int fromIndex, int toIndex);
	
	
	void sort();
	
	void parallelSort();
	

	@Override
	default Iterable<CharPointer> primitiveIterable() {
		return primitiveIterable(0);
	}
	
	Iterable<CharPointer> primitiveIterable(int index);
}