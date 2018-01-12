package com.github.powerlibraries.primitive.collections;

import java.util.Iterator;
import java.util.function.IntFunction;

import com.github.powerlibraries.primitive.common.CharPointer;

public abstract class AbstractCharCollection implements CharCollection {
	
	@Override
	public boolean contains(Object o) {
		if(!(o instanceof Character)) {
			return false;
		}
		return containsChar((Character)o);
	}

	@Override
	public boolean add(Character e) {
		return addChar(e);
	}

	@Override
	public boolean remove(Object o) {
		if(!(o instanceof Character)) {
			return false;
		}
		return removeChar((Character)o);
	}

	@Override
	public Character[] toArray(IntFunction<Character[]> p) {
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
        Iterator<CharPointer> it = primitiveIterable().iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            CharPointer e = it.next();
            sb.append(e.get());
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
}