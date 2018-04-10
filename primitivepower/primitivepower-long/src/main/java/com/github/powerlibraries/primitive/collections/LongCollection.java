package com.github.powerlibraries.primitive.collections;

import java.util.Collection;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.IntFunction;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

public interface LongCollection extends Collection<Long>, LongIterable {

	boolean containsLong(long o);
	
	Long[] toArray(IntFunction<Long[]> p);

	long[] toLongArray();

	boolean addLong(long e);

	boolean removeLong(long o);

	boolean containsAllLongs(LongCollection c);

	boolean addAllLongs(LongCollection c);

	boolean removeAllLongs(LongCollection c);

	boolean retainAllLongs(LongCollection c);
	
	default LongStream streamLongs() {
		return StreamSupport.longStream(spliterator(), false);
	}

	default LongStream parallelStreamLongs() {
		return StreamSupport.longStream(spliterator(), true);
	}
	
	@Override
	default Spliterator.OfLong spliterator() {
		return Spliterators.spliterator(this.iterator(), this.size(), 0);
	}
	
	default boolean removeIf(LongPredicate filter) {
		Objects.requireNonNull(filter);
		boolean removed = false;
		final PrimitiveIterator.OfLong each = iterator();
		while (each.hasNext()) {
			if (filter.test(each.nextLong())) {
				each.remove();
				removed = true;
			}
		}
		return removed;
	}
	
}