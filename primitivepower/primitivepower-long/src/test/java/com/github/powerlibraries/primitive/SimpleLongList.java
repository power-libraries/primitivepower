package com.github.powerlibraries.primitive;


import java.nio.LongBuffer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.github.powerlibraries.primitive.collections.AbstractLongList;
import com.github.powerlibraries.primitive.collections.LongCollection;
import com.github.powerlibraries.primitive.collections.LongList;
import com.github.powerlibraries.primitive.collections.LongListIterator;
import com.github.powerlibraries.primitive.common.LongPointer;

public class SimpleLongList extends AbstractLongList {

	private List<Long> l = new ArrayList<>();

	@Override
	public long getLong(int index) {
		return l.get(index);
	}

	@Override
	public long setLong(int index, long element) {
		return l.set(index, element);
	}

	@Override
	public void addLong(int index, long element) {
		l.add(index, element);
	}

	@Override
	public int lastIndexOfLong(long o) {
		return l.lastIndexOf(o);
	}

	@Override
	public LongList subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public LongListIterator listIterator(int index) {
		ListIterator<Long> it = l.listIterator(index);
		return new LongListIterator() {
			
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
			public void setLong(long e) {
				it.set(e);
			}
			
			@Override
			public long previousLong() {
				return it.previous();
			}
			
			@Override
			public long nextLong() {
				return it.next();
			}
			
			@Override
			public void addLong(long e) {
				it.add(e);
			}
		};
	}
	
	@Override
	public int indexOfLong(long e) {
		return l.indexOf(e);
	}

	@Override
	public Iterable<LongPointer> primitiveIterable(int index) {
		ListIterator<Long> it = l.listIterator(index);
		return new Iterable<LongPointer>() {
			public Iterator<LongPointer> iterator() {
				return new Iterator<LongPointer>() {
					
					@Override
					public void remove() {
						it.remove();
					}
					
					@Override
					public boolean hasNext() {
						return it.hasNext();
					}
					
					@Override
					public LongPointer next() {
						return new LongPointer() {
							public long get() {
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
	public long removeAt(int index) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public LongBuffer asBuffer() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int size() {
		return l.size();
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
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
	public boolean addAll(Collection<? extends Long> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends Long> c) {
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
	public boolean containsLong(long o) {
		return l.contains(o);
	}

	@Override
	public long[] toLongArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addLong(long e) {
		return l.add(e);
	}

	@Override
	public boolean removeLong(long o) {
		return l.remove((Long)o);
	}

	@Override
	public boolean addAllLongs(LongCollection c) {
		throw new UnsupportedOperationException();
	}
	
	
	@Override
	public void sort() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void parallelSort() {
		throw new UnsupportedOperationException();
	}
	
}