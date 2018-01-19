package com.github.powerlibraries.primitive.collections;

import java.util.Iterator;

import com.github.powerlibraries.primitive.common.FloatPointer;

public interface FloatIterable extends Iterable<Float> {

	@Override
    Iterator<Float> iterator();

	Iterable<FloatPointer> primitiveIterable();

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
