package com.github.powerlibraries.primitive.collections;

import java.util.Iterator;

import com.github.powerlibraries.primitive.common.CharPointer;

public interface CharIterable extends Iterable<Character> {

	@Override
    Iterator<Character> iterator();

	Iterable<CharPointer> primitiveIterable();

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
