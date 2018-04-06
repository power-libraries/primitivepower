package com.github.powerlibraries.primitive.collections;

import java.util.List;

import com.github.powerlibraries.primitive.common.ObjectPointer;

public interface ObjectList<E> extends List<E>, ObjectCollection<E> {

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
	
	E getObject(int index);

	E setObject(int index, E element);

	void addObject(int index, E element);

	int indexOfObject(E o);

	int lastIndexOfObject(E o);

	@Override
	ObjectList<E> subList(int fromIndex, int toIndex);
	
	

	default Iterable<ObjectPointer<E>> primitiveIterable() {
		return primitiveIterable(0);
	}
	
	Iterable<ObjectPointer<E>> primitiveIterable(int index);
}