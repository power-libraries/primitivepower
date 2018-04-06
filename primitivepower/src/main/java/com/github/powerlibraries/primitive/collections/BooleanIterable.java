package com.github.powerlibraries.primitive.collections;

import java.util.Iterator;

import com.github.powerlibraries.primitive.common.BooleanPointer;

public interface BooleanIterable extends Iterable<Boolean> {

	@Override
	Iterator<Boolean> iterator();

	Iterable<BooleanPointer> primitiveIterable();

	//TODO
	/*
	default void forEach(Consumer<? super T> action) {
		Objects.requireNonNull(action);
		for (T t : this) {
			action.accept(t);
		}
	}

	default Spliterator<T> spliterator() {
		return Spliterators.spliteratorUnknownSize(iterator(), 0);
	}
	*/
}
