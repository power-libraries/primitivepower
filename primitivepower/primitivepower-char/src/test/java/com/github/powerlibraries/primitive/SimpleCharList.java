package com.github.powerlibraries.primitive;


import java.nio.CharBuffer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.github.powerlibraries.primitive.collections.AbstractCharList;
import com.github.powerlibraries.primitive.collections.CharCollection;
import com.github.powerlibraries.primitive.collections.CharList;
import com.github.powerlibraries.primitive.collections.CharListIterator;
import com.github.powerlibraries.primitive.common.CharPointer;

public class SimpleCharList extends AbstractCharList {

	private List<Character> l = new ArrayList<>();

	@Override
	public char getChar(int index) {
		return l.get(index);
	}

	@Override
	public char setChar(int index, char element) {
		return l.set(index, element);
	}

	@Override
	public void addChar(int index, char element) {
		l.add(index, element);
	}

	@Override
	public int lastIndexOfChar(char o) {
		return l.lastIndexOf(o);
	}

	@Override
	public CharList subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public CharListIterator listIterator(int index) {
		ListIterator<Character> it = l.listIterator(index);
		return new CharListIterator() {
			
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
			public void setChar(char e) {
				it.set(e);
			}
			
			@Override
			public char previousChar() {
				return it.previous();
			}
			
			@Override
			public char nextChar() {
				return it.next();
			}
			
			@Override
			public void addChar(char e) {
				it.add(e);
			}
		};
	}
	
	@Override
	public int indexOfChar(char e) {
		return l.indexOf(e);
	}

	@Override
	public Iterable<CharPointer> primitiveIterable(int index) {
		ListIterator<Character> it = l.listIterator(index);
		return new Iterable<CharPointer>() {
			public Iterator<CharPointer> iterator() {
        		return new Iterator<CharPointer>() {
        			
        			@Override
        			public void remove() {
        				it.remove();
        			}
        			
        			@Override
        			public boolean hasNext() {
        				return it.hasNext();
        			}
        			
        			@Override
        			public CharPointer next() {
        				return new CharPointer() {
        					public char get() {
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
	public char removeAt(int index) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public CharBuffer asBuffer() {
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
	public boolean addAll(Collection<? extends Character> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends Character> c) {
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
	public boolean containsChar(char o) {
		return l.contains(o);
	}

	@Override
	public char[] toCharArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addChar(char e) {
		return l.add(e);
	}

	@Override
	public boolean removeChar(char o) {
		return l.remove((Character)o);
	}

	@Override
	public boolean addAllChars(CharCollection c) {
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