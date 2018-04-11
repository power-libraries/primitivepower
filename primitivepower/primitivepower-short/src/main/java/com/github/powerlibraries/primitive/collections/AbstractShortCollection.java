package com.github.powerlibraries.primitive.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.IntFunction;

import com.github.powerlibraries.primitive.common.ShortPointer;

public abstract class AbstractShortCollection implements ShortCollection {
	
	protected AbstractShortCollection() {}
	
	@Override @Deprecated
	public boolean contains(Object o) {
		if(!(o instanceof Short)) {
			return false;
		}
		return containsShort((Short)o);
	}

	@Override @Deprecated
	public boolean add(Short e) {
		return addShort(e);
	}

	@Override @Deprecated
	public boolean remove(Object o) {
		if(!(o instanceof Short)) {
			return false;
		}
		return removeShort((Short)o);
	}

	@Override @Deprecated
	public Short[] toArray(IntFunction<Short[]> p) {
		return toArray(p.apply(this.size()));
	}
	
	@Override @Deprecated
	public boolean containsAll(Collection<?> c) {
		for(Object o:c) {
			if(!this.contains(o)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean containsAllShorts(ShortCollection c) {
		for(ShortPointer o:c.primitiveIterable()) {
			if(!this.containsShort(o.get())) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean removeAllShorts(ShortCollection c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<ShortPointer> iterator = primitiveIterable().iterator();
		while (iterator.hasNext()) {
			if (c.containsShort(iterator.next().get())) {
				iterator.remove();
				modified = true;
			}
		}
		return modified;
	}

	@Override
	public boolean retainAllShorts(ShortCollection c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<ShortPointer> iterator = primitiveIterable().iterator();
		while (iterator.hasNext()) {
			if (!c.containsShort(iterator.next().get())) {
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
		Iterator<ShortPointer> iterator = primitiveIterable().iterator();
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