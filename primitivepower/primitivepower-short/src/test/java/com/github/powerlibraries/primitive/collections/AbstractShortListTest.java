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

public class AbstractShortListTest {

	@Test
	public void guardConditionsTest() {
		SimpleShortList list = new SimpleShortList();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
		assertThat(list.indexOf(null)).isEqualTo(-1);
		assertThat(list.lastIndexOf(null)).isEqualTo(-1);
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Short> expected = new ArrayList<>();
		SimpleShortList list = new SimpleShortList();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				short v = ((short)r.nextInt(9));
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				short v;
				switch(r.nextInt(4)) {
					case 0:
						v = ((short)r.nextInt(9));
						assertThat(list.remove((Short)v))
							.isEqualTo(expected.remove((Short)v));
						break;
					case 1:
						v = ((short)r.nextInt(9));
						assertThat(list.indexOf((Short)v))
							.isEqualTo(expected.indexOf((Short)v));
						break;
					case 2:
						v = ((short)r.nextInt(9));
						assertThat(list.lastIndexOf((Short)v))
							.isEqualTo(expected.lastIndexOf((Short)v));
						break;
					case 3:
						v = ((short)r.nextInt(9));
						assertThat(list.contains((Short)v))
							.isEqualTo(expected.contains((Short)v));
						break;
				}
			}
			
			readOnlyTests(list, expected);
		}
	}
	
	public static  Stream<Arguments> generateLists() {
		return LongStream
			.of(7,24829,98417242323L)
			.mapToObj(Random::new)
			.map(r -> {
				List<Short> expected = new ArrayList<>();
				SimpleShortList list = new SimpleShortList();
				
				for(int i=0; i<100; i++) {
					//adding a value
					short v = ((short)r.nextInt(9));
					list.add(v);
					expected.add(v);
				}
				readOnlyTests(list, expected);
				return Arguments.of(list, expected);
			});
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void replaceAll(SimpleShortList list, List<Short> expected) {
		list.replaceAll(v -> ((short)0));
		expected.replaceAll(v -> ((short)0));

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void equalsShortList(SimpleShortList list, List<Short> expected) {
		assertThat(list).isEqualTo(list);
		assertThat(list.equals(list)).isTrue();
		
		SimpleShortList copy = new SimpleShortList();
		copy.addAll(list);
		assertThat(copy).isEqualTo(list);
		
		assertThat(list.equals((SimpleShortList)null)).isFalse();
		assertThat(list.equals((List<Short>)null)).isFalse();
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void testShortCollectionFunctions(SimpleShortList list, List<Short> expected) {
		assertThat(list.containsAll(list)).isTrue();
		
		SimpleShortList copy = new SimpleShortList();
		copy.addAll(list);
		copy.removeAllShorts(list);
		assertThat(copy).isEmpty();
		
		copy = new SimpleShortList();
		copy.addAll(list);
		copy.retainAllShorts(list);
		assertThat(copy.containsAll(list)).isTrue();
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void removeIf(SimpleShortList list, List<Short> expected) {
		Random r1 = new Random(9);
		list.removeIf(v -> r1.nextBoolean());
		Random r2 = new Random(9);
		expected.removeIf(v -> r2.nextBoolean());

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void get(SimpleShortList list, List<Short> expected) {
		for(int i = 0; i < expected.size(); i++) {
			assertThat(list.get(i)).isEqualTo(expected.get(i));
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void set(SimpleShortList list, List<Short> expected) {
		Random r = new Random(9);
		for(int i = 0; i < expected.size(); i++) {
			short v = ((short)r.nextInt(9));
			assertThat(list.set(i, v)).isEqualTo(expected.set(i, v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void add(SimpleShortList list, List<Short> expected) {
		Random r = new Random(9);
		for(int i = 0; i < 50; i++) {
			short v = ((short)r.nextInt(9));
			assertThat(list.add(v)).isEqualTo(expected.add(v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void forEach(SimpleShortList list, List<Short> expected) {
		List<Short> collected = new ArrayList<>();
		list.forEach(v->collected.add(v));
		assertThat(collected).containsExactlyInAnyOrderElementsOf(expected);
	}
	
	
	
	private static  void readOnlyTests(SimpleShortList list, List<Short> expected) {
		List<Object> unexpected = new ArrayList<>(expected);
		unexpected.add(new Object());
	
	
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		
		assertThat(list.toArray(Short[]::new)).isEqualTo(expected.toArray(new Short[expected.size()]));
		
		assertThat(list).containsExactlyElementsOf(expected);
		
		//contains all and negative test
		assertThat(expected.containsAll(list)).isTrue();
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