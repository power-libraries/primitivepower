package com.github.powerlibraries.primitive.collections;

import java.util.List;

public interface FloatList extends List<Float>, FloatCollection {

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
	
	float getFloat(int index);

	float setFloat(int index, float element);

	void addFloat(int index, float element);

	int indexOfFloat(float o);

	int lastIndexOfFloat(float o);

	@Override
	FloatList subList(int fromIndex, int toIndex);
	
	
	public void sort();
	
	public void parallelSort();
	
}