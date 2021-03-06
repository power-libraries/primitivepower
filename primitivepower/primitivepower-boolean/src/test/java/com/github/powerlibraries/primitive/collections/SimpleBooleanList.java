package com.github.powerlibraries.primitive.collections;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.github.powerlibraries.primitive.collections.AbstractBooleanList;
import com.github.powerlibraries.primitive.collections.BooleanCollection;
import com.github.powerlibraries.primitive.collections.BooleanList;
import com.github.powerlibraries.primitive.collections.BooleanListIterator;
import com.github.powerlibraries.primitive.common.BooleanPointer;

public class SimpleBooleanList extends AbstractBooleanList {

	private List<Boolean> l = new ArrayList<>();

	@Override
	public boolean getBoolean(int index) {
		return l.get(index);
	}

	@Override
	public boolean setBoolean(int index, boolean element) {
		return l.set(index, element);
	}

	@Override
	public void addBoolean(int index, boolean element) {
		l.add(index, element);
	}

	@Override
	public int lastIndexOfBoolean(boolean o) {
		return l.lastIndexOf(o);
	}

	@Override
	public BooleanList subList(int fromIndex, int toIndex) {
		SimpleBooleanList result = new SimpleBooleanList();
		result.l = l.subList(fromIndex, toIndex);
		return result;
	}

	@Override
	public BooleanListIterator listIterator(int index) {
		ListIterator<Boolean> it = l.listIterator(index);
		return new BooleanListIterator() {
			
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
			public void setBoolean(boolean e) {
				it.set(e);
			}
			
			@Override
			public boolean previousBoolean() {
				return it.previous();
			}
			
			@Override
			public boolean nextBoolean() {
				return it.next();
			}
			
			@Override
			public void addBoolean(boolean e) {
				it.add(e);
			}
		};
	}
	
	@Override
	public int indexOfBoolean(boolean e) {
		return l.indexOf(e);
	}

	@Override
	public Iterable<BooleanPointer> primitiveIterable(int index) {
		ListIterator<Boolean> it = l.listIterator(index);
		return new Iterable<BooleanPointer>() {
			public Iterator<BooleanPointer> iterator() {
				return new Iterator<BooleanPointer>() {
					
					@Override
					public void remove() {
						it.remove();
					}
					
					@Override
					public boolean hasNext() {
						return it.hasNext();
					}
					
					@Override
					public BooleanPointer next() {
						return new BooleanPointer() {
							public boolean get() {
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
	public boolean removeAt(int index) {
		return l.remove(index);
	}
	
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
	public boolean addAll(Collection<? extends Boolean> c) {
		return l.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Boolean> c) {
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
	public boolean containsBoolean(boolean o) {
		return l.contains(o);
	}

	@Override
	public boolean[] toBooleanArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addBoolean(boolean e) {
		return l.add(e);
	}

	@Override
	public boolean removeBoolean(boolean o) {
		return l.remove((Boolean)o);
	}

	@Override
	public boolean addAllBooleans(BooleanCollection c) {
		throw new UnsupportedOperationException();
	}
	
	
}