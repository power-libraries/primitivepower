package com.github.powerlibraries.primitive.collections;

import java.util.List;

public interface ShortList extends List<Short>, ShortCollection {

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
	
	short getShort(int index);

	short setShort(int index, short element);

	void addShort(int index, short element);

	int indexOfShort(short o);

	int lastIndexOfShort(short o);

	@Override
	ShortList subList(int fromIndex, int toIndex);
	
	
	public void sort();
	
	public void parallelSort();
	
}