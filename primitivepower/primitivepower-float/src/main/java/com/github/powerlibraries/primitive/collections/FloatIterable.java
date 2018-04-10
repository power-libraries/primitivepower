package com.github.powerlibraries.primitive.collections;

import java.util.Iterator;

import com.github.powerlibraries.primitive.common.FloatPointer;

public interface FloatIterable extends Iterable<Float> {
	
	Iterator<Float> iterator();
	
	Iterable<FloatPointer> primitiveIterable();
	
}
