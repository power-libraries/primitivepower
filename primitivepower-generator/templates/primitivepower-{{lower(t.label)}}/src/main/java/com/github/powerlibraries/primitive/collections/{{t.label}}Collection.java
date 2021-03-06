package com.github.powerlibraries.primitive.collections;

import java.util.Collection;
{% if t.streamSupport %}import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.{{t.label}}Predicate;
import java.util.stream.Stream;
import java.util.function.Predicate;{% endif %}
import java.util.function.IntFunction;
{% if t.streamSupport %}import java.util.stream.{{t.label}}Stream;
import java.util.stream.StreamSupport;
{% endif %}
public interface {{t.label}}Collection{{t.generic}} extends Collection<{{t.boxed}}>, {{t.label}}Iterable{{t.generic}} {

	boolean contains{{t.label}}({{t.type}} o);
	
	{{t.boxed}}[] toArray(IntFunction<{{t.boxed}}[]> p);

	{{t.arrayType}}[] to{{t.label}}Array();

	boolean add{{t.label}}({{t.type}} e);

	boolean remove{{t.label}}({{t.type}} o);

	boolean containsAll{{t.label}}s({{t.label}}Collection{{t.extendedGeneric}} c);

	boolean addAll{{t.label}}s({{t.label}}Collection{{t.extendedGeneric}} c);

	boolean removeAll{{t.label}}s({{t.label}}Collection{{t.extendedGeneric}} c);

	boolean retainAll{{t.label}}s({{t.label}}Collection{{t.extendedGeneric}} c);
	{% if t.streamSupport %}
	default {{t.label}}Stream stream{{t.label}}s() {
		return StreamSupport.{{lower(t.label)}}Stream(spliterator(), false);
	}

	default {{t.label}}Stream parallelStream{{t.label}}s() {
		return StreamSupport.{{lower(t.label)}}Stream(spliterator(), true);
	}
	
	@Override
	default Spliterator.Of{{t.label}} spliterator() {
		return Spliterators.spliterator(this.iterator(), this.size(), 0);
	}
	
	default boolean remove{{t.label}}If({{t.label}}Predicate{{t.generic}} filter) {
		Objects.requireNonNull(filter);
		boolean removed = false;
		final PrimitiveIterator.Of{{t.label}} each = iterator();
		while (each.hasNext()) {
			if (filter.test(each.next{{t.label}}())) {
				each.remove();
				removed = true;
			}
		}
		return removed;
	}
	{% endif %}{#
	
	
	-------override deprecated
	
	#}
	@Override @Deprecated
	boolean contains(Object o);
	
	@Override{% if t.primitive %} @Deprecated{% endif %}
	<T> T[] toArray(T[] p);
	
	@Override{% if t.primitive %} @Deprecated{% endif %}
	Object[] toArray();

	@Override @Deprecated
	boolean add({{t.boxed}} e);

	@Override @Deprecated
	boolean remove(Object o);

	@Override @Deprecated
	boolean containsAll(Collection<?> c);

	@Override @Deprecated
	boolean addAll(Collection<? extends {{t.boxed}}> c);

	@Override @Deprecated
	boolean removeAll(Collection<?> c);

	@Override @Deprecated
	boolean retainAll(Collection<?> c);
	{% if t.streamSupport %}
	@Override @Deprecated
	Stream<{{t.boxed}}> stream();

	@Override @Deprecated
	Stream<{{t.boxed}}> parallelStream();
	
	@Override @Deprecated	
	boolean removeIf(Predicate<? super {{t.boxed}}> filter);
	{% endif %}
}