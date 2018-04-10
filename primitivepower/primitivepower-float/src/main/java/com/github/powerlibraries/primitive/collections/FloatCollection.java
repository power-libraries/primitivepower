package com.github.powerlibraries.primitive.collections;

import java.util.Collection;

import java.util.function.IntFunction;

public interface FloatCollection extends Collection<Float>, FloatIterable {

	boolean containsFloat(float o);
	
	Float[] toArray(IntFunction<Float[]> p);

	float[] toFloatArray();

	boolean addFloat(float e);

	boolean removeFloat(float o);

	boolean containsAllFloats(FloatCollection c);

	boolean addAllFloats(FloatCollection c);

	boolean removeAllFloats(FloatCollection c);

	boolean retainAllFloats(FloatCollection c);
	
}