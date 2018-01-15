package com.github.powerlibraries.primitive.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.IntFunction;

import com.github.powerlibraries.primitive.common.BytePointer;

public abstract class AbstractByteCollection implements ByteCollection {
	
	@Override
	public boolean contains(Object o) {
		if(!(o instanceof Byte)) {
			return false;
		}
		return containsByte((Byte)o);
	}

	@Override
	public boolean add(Byte e) {
		return addByte(e);
	}

	@Override
	public boolean remove(Object o) {
		if(!(o instanceof Byte)) {
			return false;
		}
		return removeByte((Byte)o);
	}

	@Override
	public Byte[] toArray(IntFunction<Byte[]> p) {
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
	public boolean containsAllBytes(ByteCollection c) {
		for(BytePointer o:c.primitiveIterable()) {
			if(!this.containsByte(o.get()))
				return false;
		}
		return true;
	}
	
	@Override
	public boolean removeAllBytes(ByteCollection c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<BytePointer> it = primitiveIterable().iterator();
		while (it.hasNext()) {
			if (c.containsByte(it.next().get())) {
				it.remove();
				modified = true;
			}
		}
		return modified;
	}

	@Override
	public boolean retainAllBytes(ByteCollection c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<BytePointer> it = primitiveIterable().iterator();
		while (it.hasNext()) {
			if (!c.containsByte(it.next().get())) {
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
		Iterator<BytePointer> it = primitiveIterable().iterator();
		if (! it.hasNext())
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (;;) {
			BytePointer e = it.next();
			sb.append(e.get());
			if (! it.hasNext())
				return sb.append(']').toString();
			sb.append(',').append(' ');
		}
	}
}