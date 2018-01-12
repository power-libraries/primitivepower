package com.github.powerlibraries.primitive.collections;

import java.util.List;

public interface ByteList extends List<Byte>, ByteCollection {

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
	
	byte getByte(int index);

	byte setByte(int index, byte element);

	void addByte(int index, byte element);

	int indexOfByte(byte o);

	int lastIndexOfByte(byte o);

	@Override
	ByteList subList(int fromIndex, int toIndex);
	
	
	public void sort();
	
	public void parallelSort();
	
}