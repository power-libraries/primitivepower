package com.github.powerlibraries.primitive.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.IntFunction;

import com.github.powerlibraries.primitive.common.BooleanPointer;

public abstract class AbstractBooleanCollection implements BooleanCollection {
	
	@Override
	public boolean contains(Object o) {
		if(!(o instanceof Boolean)) {
			return false;
		}
		return containsBoolean((Boolean)o);
	}

	@Override
	public boolean add(Boolean e) {
		return addBoolean(e);
	}

	@Override
	public boolean remove(Object o) {
		if(!(o instanceof Boolean)) {
			return false;
		}
		return removeBoolean((Boolean)o);
	}

	@Override
	public Boolean[] toArray(IntFunction<Boolean[]> p) {
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
	public boolean containsAllBooleans(BooleanCollection c) {
		for(BooleanPointer o:c.primitiveIterable()) {
			if(!this.containsBoolean(o.get()))
				return false;
		}
		return true;
	}
	
	@Override
	public boolean removeAllBooleans(BooleanCollection c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<BooleanPointer> it = primitiveIterable().iterator();
		while (it.hasNext()) {
			if (c.containsBoolean(it.next().get())) {
				it.remove();
				modified = true;
			}
		}
		return modified;
	}

	@Override
	public boolean retainAllBooleans(BooleanCollection c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<BooleanPointer> it = primitiveIterable().iterator();
		while (it.hasNext()) {
			if (!c.containsBoolean(it.next().get())) {
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
		Iterator<BooleanPointer> it = primitiveIterable().iterator();
		if (! it.hasNext())
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (;;) {
			BooleanPointer e = it.next();
			sb.append(e.get());
			if (! it.hasNext())
				return sb.append(']').toString();
			sb.append(',').append(' ');
		}
	}
}