package com.github.powerlibraries.primitive.collections;

import java.util.List;
import java.util.ListIterator;
{{ (t.primitive) ? '' : 'import java.util.Objects;' }}

import com.github.powerlibraries.primitive.common.{{t.label}}Pointer;

public abstract class Abstract{{t.label}}List{{t.generic}} extends Abstract{{t.label}}Collection{{t.generic}} implements {{t.label}}List{{t.generic}} {
	
	protected Abstract{{t.label}}List() {}
	
	@Override
	public {{t.boxed}} get(int index) {
		return get{{t.label}}(index);
	}

	@Override
	public {{t.boxed}} set(int index, {{t.boxed}} element) {
		return set{{t.label}}(index, element);
	}

	@Override
	public void add(int index, {{t.boxed}} element) {
		add{{t.label}}(index, element);
	}

	@Override
	public int indexOf(Object o) {
		{{t.returnOnInvalidValue('o','-1',2)}}
		return indexOf{{t.label}}(({{t.boxed}})o);
	}

	@Override
	public int lastIndexOf(Object o) {
		{{t.returnOnInvalidValue('o','-1',2)}}
		return lastIndexOf{{t.label}}(({{t.boxed}})o);
	}
	
	@Override
	public {{t.label}}ListIterator{{t.generic}} iterator() {
		return listIterator();
	}

	@Override
	public {{t.label}}ListIterator{{t.generic}} listIterator() {
		return listIterator(0);
	}
	
	/**
	 * Compares the specified object with this list for equality.  Returns
	 * {@code true} if and only if the specified object is also a list, both
	 * lists have the same size, and all corresponding pairs of elements in
	 * the two lists are <i>equal</i>.  (Two elements {@code e1} and
	 * {@code e2} are <i>equal</i> if {@code (e1==null ? e2==null :
	 * e1.equals(e2))}.)  In other words, two lists are defined to be
	 * equal if they contain the same elements in the same order.<p>
	 *
	 * This implementation first checks if the specified object is this
	 * list. If so, it returns {@code true}; if not, it checks if the
	 * specified object is a list. If not, it returns {@code false}; if so,
	 * it iterates over both lists, comparing corresponding pairs of elements.
	 * If any comparison returns {@code false}, this method returns
	 * {@code false}.  If either iterator runs out of elements before the
	 * other it returns {@code false} (as the lists are of unequal length);
	 * otherwise it returns {@code true} when the iterations complete.
	 *
	 * @param o the object to be compared for equality with this list
	 * @return {@code true} if the specified object is equal to this list
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof List)) {
			return false;
		}
		if (o instanceof {{t.label}}List) {
			return equals(({{t.label}}List) o);
		}

		{{t.label}}ListIterator{{t.generic}} e1 = listIterator();
		ListIterator<?> e2 = ((List<?>) o).listIterator();
		while (e1.hasNext() && e2.hasNext()) {
			Object o2 = e2.next();
			{{t.returnOnInvalidValue('o2','false',3)}}
			if (o2==null || {{t.unequal('e1.next'~t.label~'()', '('~t.boxed~')o2')}}) {
				return false;
			}
		}
		return !(e1.hasNext() || e2.hasNext());
	}
	
	public boolean equals({{t.label}}List o) {
		if (o == this) {
			return true;
		}
		if (o == null) {
			return false;
		}

		{{t.label}}ListIterator{{t.generic}} e1 = listIterator();
		{{t.label}}ListIterator{{t.generic}} e2 = o.listIterator();
		while (e1.hasNext() && e2.hasNext()) {
			{{t.type}} o1 = e1.next{{t.label}}();
			{{t.type}} o2 = e2.next{{t.label}}();
			if ({{t.unequal('o1', 'o2')}}) {
				return false;
			}
		}
		return !(e1.hasNext() || e2.hasNext());
	}

	/**
	 * Returns the hash code value for this list.
	 *
	 * <p>This implementation uses exactly the code that is used to define the
	 * list hash function in the documentation for the {@link List#hashCode}
	 * method.
	 *
	 * @return the hash code value for this list
	 */
	public int hashCode() {
		int hashCode = 1;
		for ({{t.label}}Pointer{{t.generic}} e : this.primitiveIterable()) {
			hashCode = 31*hashCode + (e==null ? 0 : {{t.hash('e.get()')}});
		}
			
		return hashCode;
	}
	
	@Override
	public {{t.boxed}} remove(int index) {
		return this.removeAt(index);
	}
}