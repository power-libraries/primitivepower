package com.github.powerlibraries.primitive.collections;

import java.util.Collection;
import java.util.function.IntFunction;


public interface CharCollection extends Collection<Character>, CharIterable {

	boolean containsChar(char o);
	
	Character[] toArray(IntFunction<Character[]> p);

	char[] toCharArray();

	boolean addChar(char e);

	boolean removeChar(char o);

	boolean containsAllChars(CharCollection c);

	boolean addAllChars(CharCollection c);

	boolean removeAllChars(CharCollection c);

	boolean retainAllChars(CharCollection c);
	
}