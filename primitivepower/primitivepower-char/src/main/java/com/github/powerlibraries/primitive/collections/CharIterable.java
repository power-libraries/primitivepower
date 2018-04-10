package com.github.powerlibraries.primitive.collections;

import java.util.Iterator;

import com.github.powerlibraries.primitive.common.CharPointer;

public interface CharIterable extends Iterable<Character> {
	
	Iterator<Character> iterator();
	
	Iterable<CharPointer> primitiveIterable();
	
}
