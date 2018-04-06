package com.github.powerlibraries.primitive.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.IntFunction;

import com.github.powerlibraries.primitive.common.IntPointer;

public abstract class AbstractIntCollection implements IntCollection {
	
	protected AbstractIntCollection() {}
	
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
	
	@Override
	public boolean containsAll(Collection<?> c) {
		for(Object o:c) {
			if(!this.contains(o)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean containsAllInts(IntCollection c) {
		for(IntPointer o:c.primitiveIterable()) {
			if(!this.containsInt(o.get())) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean removeAllInts(IntCollection c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<IntPointer> iterator = primitiveIterable().iterator();
		while (iterator.hasNext()) {
			if (c.containsInt(iterator.next().get())) {
				iterator.remove();
				modified = true;
			}
		}
		return modified;
	}

	@Override
	public boolean retainAllInts(IntCollection c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<IntPointer> iterator = primitiveIterable().iterator();
		while (iterator.hasNext()) {
			if (!c.containsInt(iterator.next().get())) {
				iterator.remove();
				modified = true;
			}
		}
		return modified;
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
		Iterator<IntPointer> iterator = primitiveIterable().iterator();
		if (! iterator.hasNext()) {
			return "[]";
		}

		StringBuilder result = new StringBuilder();
		result.append('[');
		for (;;) {
			result.append(iterator.next().get());
			if (! iterator.hasNext()) {
				return result.append(']').toString();
			}
			result.append(", ");
		}
	}
}