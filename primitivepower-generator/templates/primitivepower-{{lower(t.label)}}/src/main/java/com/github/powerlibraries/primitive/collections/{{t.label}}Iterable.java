package com.github.powerlibraries.primitive.collections;
{% if t.streamSupport %}
import java.util.PrimitiveIterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.{{t.label}}Consumer;
{% else %}
import java.util.Iterator;
{% endif %}
import com.github.powerlibraries.primitive.common.{{t.label}}Pointer;

public interface {{t.label}}Iterable{{t.generic}} extends Iterable<{{t.boxed}}> {
	{% if t.streamSupport %}
	@Override
	PrimitiveIterator.Of{{t.label}} iterator();
	{% else %}
	@Override
	Iterator<{{t.boxed}}> iterator();
	{% endif %}
	Iterable<{{t.label}}Pointer{{t.generic}}> primitiveIterable();
	{% if t.streamSupport %}
	default void forEach{{t.label}}({{t.label}}Consumer{{t.generic}} action) {
		Objects.requireNonNull(action);
		for ({{t.label}}Pointer{{t.generic}} t : this.primitiveIterable()) {
			action.accept(t.get());
		}
	}

	default Spliterator.Of{{t.label}} spliterator() {
		return Spliterators.spliteratorUnknownSize(iterator(), 0);
	}
	{% endif %}
}
