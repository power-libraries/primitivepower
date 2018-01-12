package com.github.powerlibraries.primitive.collections;

import java.util.Iterator;
import java.util.function.IntFunction;

import com.github.powerlibraries.primitive.common.LongPointer;

public abstract class AbstractLongCollection implements LongCollection {
	
	@Override
	public boolean contains(Object o) {
		if(!(o instanceof Long)) {
			return false;
		}
		return containsLong((Long)o);
	}

	@Override
	public boolean add(Long e) {
		return addLong(e);
	}

	@Override
	public boolean remove(Object o) {
		if(!(o instanceof Long)) {
			return false;
		}
		return removeLong((Long)o);
	}

	@Override
	public Long[] toArray(IntFunction<Long[]> p) {
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
        Iterator<LongPointer> it = primitiveIterable().iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            LongPointer e = it.next();
            sb.append(e.get());
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
}