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

public class AbstractObjectListTest<E> {

	@Test
	public void guardConditionsTest() {
		SimpleObjectList<E> list = new SimpleObjectList<E>();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
		assertThat(list.indexOf(null)).isEqualTo(-1);
		assertThat(list.lastIndexOf(null)).isEqualTo(-1);
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<E> expected = new ArrayList<>();
		SimpleObjectList<E> list = new SimpleObjectList<E>();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				E v = (E)TimeUnit.values()[r.nextInt(7)];
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				E v;
				switch(r.nextInt(4)) {
					case 0:
						v = (E)TimeUnit.values()[r.nextInt(7)];
						assertThat(list.remove((E)v))
							.isEqualTo(expected.remove((E)v));
						break;
					case 1:
						v = (E)TimeUnit.values()[r.nextInt(7)];
						assertThat(list.indexOf((E)v))
							.isEqualTo(expected.indexOf((E)v));
						break;
					case 2:
						v = (E)TimeUnit.values()[r.nextInt(7)];
						assertThat(list.lastIndexOf((E)v))
							.isEqualTo(expected.lastIndexOf((E)v));
						break;
					case 3:
						v = (E)TimeUnit.values()[r.nextInt(7)];
						assertThat(list.contains((E)v))
							.isEqualTo(expected.contains((E)v));
						break;
				}
			}
			
			readOnlyTests(list, expected);
		}
	}
	
	public static <E> Stream<Arguments> generateLists() {
		return LongStream
			.of(7,24829,98417242323L)
			.mapToObj(Random::new)
			.map(r -> {
				List<E> expected = new ArrayList<>();
				SimpleObjectList<E> list = new SimpleObjectList<E>();
				
				for(int i=0; i<100; i++) {
					//adding a value
					E v = (E)TimeUnit.values()[r.nextInt(7)];
					list.add(v);
					expected.add(v);
				}
				readOnlyTests(list, expected);
				return Arguments.of(list, expected);
			});
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void replaceAll(SimpleObjectList<E> list, List<E> expected) {
		list.replaceAll(v -> null);
		expected.replaceAll(v -> null);

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void equalsObjectList(SimpleObjectList<E> list, List<E> expected) {
		assertThat(list).isEqualTo(list);
		assertThat(list.equals(list)).isTrue();
		
		SimpleObjectList<E> copy = new SimpleObjectList<E>();
		copy.addAll(list);
		assertThat(copy).isEqualTo(list);
		
		assertThat(list.equals((SimpleObjectList<E>)null)).isFalse();
		assertThat(list.equals((List<E>)null)).isFalse();
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void testObjectCollectionFunctions(SimpleObjectList<E> list, List<E> expected) {
		assertThat(list.containsAll(list)).isTrue();
		
		SimpleObjectList<E> copy = new SimpleObjectList<E>();
		copy.addAll(list);
		copy.removeAllObjects(list);
		assertThat(copy).isEmpty();
		
		copy = new SimpleObjectList<E>();
		copy.addAll(list);
		copy.retainAllObjects(list);
		assertThat(copy.containsAll(list)).isTrue();
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void removeIf(SimpleObjectList<E> list, List<E> expected) {
		Random r1 = new Random(9);
		list.removeIf(v -> r1.nextBoolean());
		Random r2 = new Random(9);
		expected.removeIf(v -> r2.nextBoolean());

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void get(SimpleObjectList<E> list, List<E> expected) {
		for(int i = 0; i < expected.size(); i++) {
			assertThat(list.get(i)).isEqualTo(expected.get(i));
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void set(SimpleObjectList<E> list, List<E> expected) {
		Random r = new Random(9);
		for(int i = 0; i < expected.size(); i++) {
			E v = (E)TimeUnit.values()[r.nextInt(7)];
			assertThat(list.set(i, v)).isEqualTo(expected.set(i, v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void add(SimpleObjectList<E> list, List<E> expected) {
		Random r = new Random(9);
		for(int i = 0; i < 50; i++) {
			E v = (E)TimeUnit.values()[r.nextInt(7)];
			assertThat(list.add(v)).isEqualTo(expected.add(v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void forEach(SimpleObjectList<E> list, List<E> expected) {
		List<E> collected = new ArrayList<>();
		list.forEach(v->collected.add(v));
		assertThat(collected).containsExactlyInAnyOrderElementsOf(expected);
	}
	
	
	
	private static <E> void readOnlyTests(SimpleObjectList<E> list, List<E> expected) {
		List<Object> unexpected = new ArrayList<>(expected);
		unexpected.add(new Object());
	
	
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		
		assertThat(list).containsExactlyElementsOf(expected);
		
		//contains all and negative test
		assertThat(expected.containsAll(list)).isTrue();
		assertThat(list.containsAllObjects(list)).isTrue();
		assertThat(list.containsAll(expected)).isTrue();
		assertThat(list.containsAll(unexpected)).isFalse();
		
		assertThat(list.stream()).containsExactlyElementsOf(expected);
		assertThat(list.parallelStream()).containsExactlyInAnyOrderElementsOf(expected);
		
		
		assertThat(list.spliterator().characteristics()).isEqualTo(expected.spliterator().characteristics());
		assertThat(Spliterators.iterator(list.spliterator())).toIterable().containsExactlyElementsOf(()->Spliterators.iterator(expected.spliterator()));
		
		assertThat(list.hashCode()).isEqualTo(expected.hashCode());
		assertThat(list.equals(expected));
	}
}