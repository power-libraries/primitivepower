package com.github.powerlibraries.primitive.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.IntFunction;

import com.github.powerlibraries.primitive.common.ObjectPointer;

public abstract class AbstractObjectCollection<E> implements ObjectCollection<E> {
	
	protected AbstractObjectCollection() {}
	
	@Override
	public boolean contains(Object o) {
		
		return containsObject((E)o);
	}

	@Override
	public boolean add(E e) {
		return addObject(e);
	}

	@Override
	public boolean remove(Object o) {
		
		return removeObject((E)o);
	}

	@Override
	public E[] toArray(IntFunction<E[]> p) {
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
	public boolean containsAllObjects(ObjectCollection<? extends E> c) {
		for(ObjectPointer<? extends E> o:c.primitiveIterable()) {
			if(!this.containsObject(o.get())) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean removeAllObjects(ObjectCollection<? extends E> c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<E> iterator = iterator();
		while (iterator.hasNext()) {
			if (c.contains(iterator.next())) {
				iterator.remove();
				modified = true;
			}
		}
		return modified;
	}

	@Override
	public boolean retainAllObjects(ObjectCollection<? extends E> c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<E> iterator = iterator();
		while (iterator.hasNext()) {
			if (!c.contains(iterator.next())) {
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
		Iterator<ObjectPointer<E>> iterator = primitiveIterable().iterator();
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