package com.github.powerlibraries.primitive.collections;

{% if t.bufferSupport %}import java.nio.{{t.label}}Buffer;{% endif %}
import java.util.List;
{% if t.streamSupport %}
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.{{t.label}}UnaryOperator;
{% endif %}
import com.github.powerlibraries.primitive.common.{{t.label}}Pointer;

public interface {{t.label}}List{{t.generic}} extends List<{{t.boxed}}>, {{t.label}}Collection{{t.generic}} {

	{{t.type}} get{{t.label}}(int index);

	{{t.type}} set{{t.label}}(int index, {{t.type}} element);

	void add{{t.label}}(int index, {{t.type}} element);

	int indexOf{{t.label}}({{t.type}} o);

	int lastIndexOf{{t.label}}({{t.type}} o);

	@Override
	{{t.label}}List{{t.generic}} subList(int fromIndex, int toIndex);
	
	@Override
	{{t.label}}ListIterator{{t.generic}} listIterator();
	
	@Override
	{{t.label}}ListIterator{{t.generic}} listIterator(int index);
	{% if t.numeric %}
	void sort();
	
	void parallelSort();
	{% endif %}
	@Override
	default Iterable<{{t.label}}Pointer{{t.generic}}> primitiveIterable() {
		return primitiveIterable(0);
	}
	
	Iterable<{{t.label}}Pointer{{t.generic}}> primitiveIterable(int index);
	{% if t.streamSupport %}
	default void replaceAll{{t.label}}s({{t.label}}UnaryOperator operator) {
		Objects.requireNonNull(operator);
		final {{t.label}}ListIterator li = this.listIterator();
		while (li.hasNext()) {
			li.set{{t.label}}(operator.applyAs{{t.label}}(li.next{{t.label}}()));
		}
	}
	
	@Override
	default Spliterator.Of{{t.label}} spliterator() {
		return Spliterators.spliterator(iterator(), size(), Spliterator.ORDERED);
	}
	{% endif %}
	void reverse();
	
	@Override @Deprecated
	{{t.boxed}} get(int index);

	@Override @Deprecated
	{{t.boxed}} set(int index, {{t.boxed}} element);

	@Override @Deprecated
	void add(int index, {{t.boxed}} element);

	@Override @Deprecated
	int indexOf(Object o);

	@Override @Deprecated
	int lastIndexOf(Object o);
	
	{{t.type}} removeAt(int index);
	
	@Override @Deprecated
	{{t.boxed}} remove(int index);
	{% if t.bufferSupport %}
	{{t.label}}Buffer asBuffer();
	{% endif %}
}