package com.github.powerlibraries.primitive.collections;

{% if t.bufferSupport %}
import java.nio.{{t.label}}Buffer;
{% endif %}
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.github.powerlibraries.primitive.collections.Abstract{{t.label}}List;
import com.github.powerlibraries.primitive.collections.{{t.label}}Collection;
import com.github.powerlibraries.primitive.collections.{{t.label}}List;
import com.github.powerlibraries.primitive.collections.{{t.label}}ListIterator;
import com.github.powerlibraries.primitive.common.{{t.label}}Pointer;

public class Simple{{t.label}}List{{t.generic}} extends Abstract{{t.label}}List{{t.generic}} {

	private List<{{t.boxed}}> l = new ArrayList<>();

	@Override
	public {{t.type}} get{{t.label}}(int index) {
		return l.get(index);
	}

	@Override
	public {{t.type}} set{{t.label}}(int index, {{t.type}} element) {
		return l.set(index, element);
	}

	@Override
	public void add{{t.label}}(int index, {{t.type}} element) {
		l.add(index, element);
	}

	@Override
	public int lastIndexOf{{t.label}}({{t.type}} o) {
		return l.lastIndexOf(o);
	}

	@Override
	public {{t.label}}List subList(int fromIndex, int toIndex) {
		Simple{{t.label}}List{{t.generic}} result = new Simple{{t.label}}List{{t.generic}}();
		result.l = l.subList(fromIndex, toIndex);
		return result;
	}

	@Override
	public {{t.label}}ListIterator{{t.generic}} listIterator(int index) {
		ListIterator<{{t.boxed}}> it = l.listIterator(index);
		return new {{t.label}}ListIterator{{t.generic}}() {
			
			@Override
			public void remove() {
				it.remove();
			}
			
			@Override
			public int previousIndex() {
				return it.previousIndex();
			}
			
			@Override
			public int nextIndex() {
				return it.nextIndex();
			}
			
			@Override
			public boolean hasPrevious() {
				return it.hasPrevious();
			}
			
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}
			
			@Override
			public void set{{t.label}}({{t.type}} e) {
				it.set(e);
			}
			
			@Override
			public {{t.type}} previous{{t.label}}() {
				return it.previous();
			}
			
			@Override
			public {{t.type}} next{{t.label}}() {
				return it.next();
			}
			
			@Override
			public void add{{t.label}}({{t.type}} e) {
				it.add(e);
			}
		};
	}
	
	@Override
	public int indexOf{{t.label}}({{t.type}} e) {
		return l.indexOf(e);
	}

	@Override
	public Iterable<{{t.label}}Pointer{{t.generic}}> primitiveIterable(int index) {
		ListIterator<{{t.boxed}}> it = l.listIterator(index);
		return new Iterable<{{t.label}}Pointer{{t.generic}}>() {
			public Iterator<{{t.label}}Pointer{{t.generic}}> iterator() {
				return new Iterator<{{t.label}}Pointer{{t.generic}}>() {
					
					@Override
					public void remove() {
						it.remove();
					}
					
					@Override
					public boolean hasNext() {
						return it.hasNext();
					}
					
					@Override
					public {{t.label}}Pointer{{t.generic}} next() {
						return new {{t.label}}Pointer{{t.generic}}() {
							public {{t.type}} get() {
								return it.next();
							}
						};
					}
				};
			}
		};
	}

	@Override
	public void reverse() {
		throw new UnsupportedOperationException();
	}

	@Override
	public {{t.type}} removeAt(int index) {
		return l.remove(index);
	}
	{% if t.bufferSupport %}
	@Override
	public {{t.label}}Buffer asBuffer() {
		throw new UnsupportedOperationException();
	}
	{% endif %}
	@Override
	public int size() {
		return l.size();
	}

	@Override
	public boolean isEmpty() {
		return l.isEmpty();
	}

	@Override
	public Object[] toArray() {
		return l.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return l.toArray(a);
	}

	@Override
	public boolean addAll(Collection<? extends {{t.boxed}}> c) {
		return l.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends {{t.boxed}}> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains{{t.label}}({{t.type}} o) {
		return l.contains(o);
	}

	@Override
	public {{t.type}}[] to{{t.label}}Array() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add{{t.label}}({{t.type}} e) {
		return l.add(e);
	}

	@Override
	public boolean remove{{t.label}}({{t.type}} o) {
		return l.remove(({{t.boxed}})o);
	}

	@Override
	public boolean addAll{{t.label}}s({{t.label}}Collection c) {
		throw new UnsupportedOperationException();
	}
	
	{% if t.numeric %}
	@Override
	public void sort() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void parallelSort() {
		throw new UnsupportedOperationException();
	}
	{% endif %}
}