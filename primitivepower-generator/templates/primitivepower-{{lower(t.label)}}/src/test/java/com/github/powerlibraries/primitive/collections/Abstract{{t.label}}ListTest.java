package com.github.powerlibraries.primitive.collections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Spliterators;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Abstract{{t.label}}ListTest{{t.generic}} {

	@Test
	public void guardConditionsTest() {
		Simple{{t.label}}List{{t.generic}} list = new Simple{{t.label}}List{{t.generic}}();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
		assertThat(list.indexOf(null)).isEqualTo(-1);
		assertThat(list.lastIndexOf(null)).isEqualTo(-1);
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<{{t.boxed}}> expected = new ArrayList<>();
		Simple{{t.label}}List{{t.generic}} list = new Simple{{t.label}}List{{t.generic}}();
		
		for(int i=0; i<200; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				{{t.type}} v = {{t.randomValue('r')}};
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				{{t.type}} v;
				switch(r.nextInt(4)) {
					case 0:
						v = {{t.randomValue('r')}};
						assertThat(list.remove(({{t.boxed}})v))
							.isEqualTo(expected.remove(({{t.boxed}})v));
						break;
					case 1:
						v = {{t.randomValue('r')}};
						assertThat(list.indexOf(({{t.boxed}})v))
							.isEqualTo(expected.indexOf(({{t.boxed}})v));
						break;
					case 2:
						v = {{t.randomValue('r')}};
						assertThat(list.lastIndexOf(({{t.boxed}})v))
							.isEqualTo(expected.lastIndexOf(({{t.boxed}})v));
						break;
					case 3:
						v = {{t.randomValue('r')}};
						assertThat(list.contains(({{t.boxed}})v))
							.isEqualTo(expected.contains(({{t.boxed}})v));
						break;
				}
			}
			
			readOnlyTests(list, expected);
		}
	}
	
	public static {{t.generic}} Stream<Arguments> generateLists() {
		return LongStream
			.of(7,24829,98417242323L)
			.mapToObj(Random::new)
			.map(r -> {
				List<{{t.boxed}}> expected = new ArrayList<>();
				Simple{{t.label}}List{{t.generic}} list = new Simple{{t.label}}List{{t.generic}}();
				
				for(int i=0; i<100; i++) {
					//adding a value
					{{t.type}} v = {{t.randomValue('r')}};
					list.add(v);
					expected.add(v);
				}
				readOnlyTests(list, expected);
				return Arguments.of(list, expected);
			});
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void replaceAll(Simple{{t.label}}List{{t.generic}} list, List<{{t.boxed}}> expected) {
		list.replaceAll(v -> {{t.neutralElement}});
		expected.replaceAll(v -> {{t.neutralElement}});

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void equals{{t.label}}List(Simple{{t.label}}List{{t.generic}} list, List<{{t.boxed}}> expected) {
		assertThat(list).isEqualTo(list);
		assertThat(list.equals(list)).isTrue();
		
		Simple{{t.label}}List{{t.generic}} copy = new Simple{{t.label}}List{{t.generic}}();
		copy.addAll(list);
		assertThat(copy).isEqualTo(list);
		
		assertThat(list.equals((Simple{{t.label}}List{{t.generic}})null)).isFalse();
		assertThat(list.equals((List<{{t.boxed}}>)null)).isFalse();
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void test{{t.label}}CollectionFunctions(Simple{{t.label}}List{{t.generic}} list, List<{{t.boxed}}> expected) {
		assertThat(list.containsAll(list)).isTrue();
		
		Simple{{t.label}}List{{t.generic}} copy = new Simple{{t.label}}List{{t.generic}}();
		copy.addAll(list);
		copy.removeAll{{t.label}}s(list);
		assertThat(copy).isEmpty();
		
		copy = new Simple{{t.label}}List{{t.generic}}();
		copy.addAll(list);
		copy.retainAll{{t.label}}s(list);
		assertThat(copy.containsAll(list)).isTrue();
		
		list.removeAt(0);
		copy.retainAll{{t.label}}s(list);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void removeIf(Simple{{t.label}}List{{t.generic}} list, List<{{t.boxed}}> expected) {
		Random r1 = new Random(9);
		list.removeIf(v -> r1.nextBoolean());
		Random r2 = new Random(9);
		expected.removeIf(v -> r2.nextBoolean());

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void get(Simple{{t.label}}List{{t.generic}} list, List<{{t.boxed}}> expected) {
		for(int i = 0; i < expected.size(); i++) {
			assertThat(list.get(i)).isEqualTo(expected.get(i));
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void set(Simple{{t.label}}List{{t.generic}} list, List<{{t.boxed}}> expected) {
		Random r = new Random(9);
		for(int i = 0; i < expected.size(); i++) {
			{{t.type}} v = {{t.randomValue('r')}};
			assertThat(list.set(i, v)).isEqualTo(expected.set(i, v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void add(Simple{{t.label}}List{{t.generic}} list, List<{{t.boxed}}> expected) {
		Random r = new Random(9);
		for(int i = 0; i < 50; i++) {
			{{t.type}} v = {{t.randomValue('r')}};
			assertThat(list.add(v)).isEqualTo(expected.add(v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void forEach(Simple{{t.label}}List{{t.generic}} list, List<{{t.boxed}}> expected) {
		List<{{t.boxed}}> collected = new ArrayList<>();
		list.forEach(v->collected.add(v));
		assertThat(collected).containsExactlyInAnyOrderElementsOf(expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void listIterator(Simple{{t.label}}List{{t.generic}} list, List<{{t.boxed}}> expected) {
		Simple{{t.label}}List{{t.generic}} copy = new Simple{{t.label}}List{{t.generic}}();
		copy.addAll(list);
		Random r = new Random(9);
		{{t.type}} v = {{t.randomValue('r')}};
		list.listIterator().add(v);
		assertThat(list.get(0)).isEqualTo(v);
		assertThat(list.subList(1, list.size())).containsExactlyInAnyOrderElementsOf(copy);
	}
	
	{% if t.streamSupport %}
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void replaceAll{{t.label}}s(Simple{{t.label}}List{{t.generic}} list, List<{{t.boxed}}> expected) {
		list.replaceAll{{t.label}}s(v -> {{t.neutralElement}});
		expected.replaceAll(v -> {{t.neutralElement}});
		
		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void remove{{t.label}}If(Simple{{t.label}}List{{t.generic}} list, List<{{t.boxed}}> expected) {
		Random r1 = new Random(9);
		list.remove{{t.label}}If(v -> r1.nextBoolean());
		Random r2 = new Random(9);
		expected.removeIf(v -> r2.nextBoolean());

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void forEach{{t.label}}(Simple{{t.label}}List{{t.generic}} list, List<{{t.boxed}}> expected) {
		List<{{t.boxed}}> collected = new ArrayList<>();
		list.forEach{{t.label}}(v->collected.add(v));
		assertThat(collected).containsExactlyInAnyOrderElementsOf(expected);
	}
	{% endif %}
	
	private static {{t.generic}} void readOnlyTests(Simple{{t.label}}List{{t.generic}} list, List<{{t.boxed}}> expected) {
		List<Object> unexpected = new ArrayList<>(expected);
		unexpected.add(new Object());
	
	
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		{% if t.label != 'Object' %}
		assertThat(list.toArray({{t.boxedArrayType}}[]::new)).isEqualTo(expected.toArray(new {{t.boxedArrayType}}[expected.size()]));
		{% endif %}
		assertThat(list).containsExactlyElementsOf(expected);
		
		//contains all and negative test
		assertThat(expected.containsAll(list)).isTrue();
		assertThat(list.containsAll{{t.label}}s(list)).isTrue();
		assertThat(list.containsAll(expected)).isTrue();
		assertThat(list.containsAll(unexpected)).isFalse();
		
		assertThat(list.stream()).containsExactlyElementsOf(expected);
		assertThat(list.parallelStream()).containsExactlyInAnyOrderElementsOf(expected);
		{% if t.streamSupport %}
		assertThat(list.stream{{t.label}}s()).containsExactlyElementsOf(expected);
		assertThat(list.parallelStream{{t.label}}s()).containsExactlyInAnyOrderElementsOf(expected);
		{% endif %}
		
		assertThat(list.spliterator().characteristics()).isEqualTo(expected.spliterator().characteristics());
		assertThat(Spliterators.iterator(list.spliterator())).toIterable().containsExactlyElementsOf(()->Spliterators.iterator(expected.spliterator()));
		
		assertThat(list.hashCode()).isEqualTo(expected.hashCode());
		assertThat(list.equals(expected));
	}
}