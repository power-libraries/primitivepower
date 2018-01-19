package com.github.powerlibraries.primitive.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.IntFunction;

import com.github.powerlibraries.primitive.common.DoublePointer;

public abstract class AbstractDoubleCollection implements DoubleCollection {
	
	@Override
	public boolean contains(Object o) {
		if(!(o instanceof Double)) {
			return false;
		}
		return containsDouble((Double)o);
	}

	@Override
	public boolean add(Double e) {
		return addDouble(e);
	}

	@Override
	public boolean remove(Object o) {
		if(!(o instanceof Double)) {
			return false;
		}
		return removeDouble((Double)o);
	}

	@Override
	public Double[] toArray(IntFunction<Double[]> p) {
		return toArray(p.apply(this.size()));
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		for(Object o:c) {
			if(!this.contains(o))
				return false;
		}
		return true;
	}
	
	@Override
	public boolean containsAllDoubles(DoubleCollection c) {
		for(DoublePointer o:c.primitiveIterable()) {
			if(!this.containsDouble(o.get()))
				return false;
		}
		return true;
	}
	
	@Override
	public boolean removeAllDoubles(DoubleCollection c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<DoublePointer> it = primitiveIterable().iterator();
		while (it.hasNext()) {
			if (c.containsDouble(it.next().get())) {
				it.remove();
				modified = true;
			}
		}
		return modified;
	}

	@Override
	public boolean retainAllDoubles(DoubleCollection c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<DoublePointer> it = primitiveIterable().iterator();
		while (it.hasNext()) {
			if (!c.containsDouble(it.next().get())) {
				it.remove();
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
		Iterator<DoublePointer> it = primitiveIterable().iterator();
		if (! it.hasNext())
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (;;) {
			DoublePointer e = it.next();
			sb.append(e.get());
			if (! it.hasNext())
				return sb.append(']').toString();
			sb.append(',').append(' ');
		}
	}
}