package com.github.powerlibraries.primitive.collections;

import java.util.Collection;
import java.util.function.IntFunction;
import java.util.stream.DoubleStream;

public interface DoubleCollection extends Collection<Double>, DoubleIterable {

	boolean containsDouble(double o);
	
	Double[] toArray(IntFunction<Double[]> p);

	double[] toDoubleArray();

	boolean addDouble(double e);

	boolean removeDouble(double o);

	boolean containsAllDoubles(DoubleCollection c);

	boolean addAllDoubles(DoubleCollection c);

	boolean removeAllDoubles(DoubleCollection c);

	boolean retainAllDoubles(DoubleCollection c);
	
	DoubleStream streamDoubles();

	DoubleStream parallelStreamDoubles();
	
}