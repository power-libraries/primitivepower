package com.github.powerlibraries.primitive.collections;

import java.util.List;

import com.github.powerlibraries.primitive.common.CharPointer;

public interface CharList extends List<Character>, CharCollection {

	char getChar(int index);

	char setChar(int index, char element);

	void addChar(int index, char element);

	int indexOfChar(char o);

	int lastIndexOfChar(char o);

	@Override
	CharList subList(int fromIndex, int toIndex);
	
	
	void sort();
	
	void parallelSort();
	

	@Override
	default Iterable<CharPointer> primitiveIterable() {
		return primitiveIterable(0);
	}
	
	Iterable<CharPointer> primitiveIterable(int index);
		
}