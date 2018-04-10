package com.github.powerlibraries.primitive.collections;

import java.util.List;

import com.github.powerlibraries.primitive.common.FloatPointer;

public interface FloatList extends List<Float>, FloatCollection {

	float getFloat(int index);

	float setFloat(int index, float element);

	void addFloat(int index, float element);

	int indexOfFloat(float o);

	int lastIndexOfFloat(float o);

	@Override
	FloatList subList(int fromIndex, int toIndex);
	
	
	void sort();
	
	void parallelSort();
	

	@Override
	default Iterable<FloatPointer> primitiveIterable() {
		return primitiveIterable(0);
	}
	
	Iterable<FloatPointer> primitiveIterable(int index);
		
}