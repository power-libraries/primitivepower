package com.github.powerlibraries.primitive.collections;

import java.util.Iterator;
import java.util.function.IntFunction;

import com.github.powerlibraries.primitive.common.IntPointer;

public abstract class AbstractIntCollection implements IntCollection {
	
	@Override
	public boolean contains(Object o) {
		if(!(o instanceof Integer)) {
			return false;
		}
		return containsInt((Integer)o);
	}

	@Override
	public boolean add(Integer e) {
		return addInt(e);
	}

	@Override
	public boolean remove(Object o) {
		if(!(o instanceof Integer)) {
			return false;
		}
		return removeInt((Integer)o);
	}

	@Override
	public Integer[] toArray(IntFunction<Integer[]> p) {
		return toArray(p.apply(this.size()));
	}
	
	/**
     * Returns a string representation of this collection.  The string
     * representation consists of a list of the collection's elements in the
     * order they are returned by its iterator, enclosed in square brackets
     * (<tt>"[]"</tt>).  Adjacent elements are separated by the characters
     * <tt>", "</tt> (comma and space).  Elements are converted to strings as
     * by {@link String#valueOf(Object)}.
     *
     * @return a string representation of this collection
     */
    public String toString() {
        Iterator<IntPointer> it = primitiveIterable().iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            IntPointer e = it.next();
            sb.append(e.get());
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
}