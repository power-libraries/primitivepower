package com.github.powerlibraries.primitive.collections;

import java.util.Collection;
import java.util.function.IntFunction;


public interface BooleanCollection extends Collection<Boolean>, BooleanIterable {

	boolean containsBoolean(boolean o);
	
	Boolean[] toArray(IntFunction<Boolean[]> p);

	boolean[] toBooleanArray();

	boolean addBoolean(boolean e);

	boolean removeBoolean(boolean o);

	boolean containsAllBooleans(BooleanCollection c);

	boolean addAllBooleans(BooleanCollection c);

	boolean removeAllBooleans(BooleanCollection c);

	boolean retainAllBooleans(BooleanCollection c);
	
}