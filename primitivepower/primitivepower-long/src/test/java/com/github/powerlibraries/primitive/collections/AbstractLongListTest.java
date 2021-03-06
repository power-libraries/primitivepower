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

public class AbstractLongListTest {

	@Test
	public void guardConditionsTest() {
		SimpleLongList list = new SimpleLongList();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
		assertThat(list.indexOf(null)).isEqualTo(-1);
		assertThat(list.lastIndexOf(null)).isEqualTo(-1);
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Long> expected = new ArrayList<>();
		SimpleLongList list = new SimpleLongList();
		
		for(int i=0; i<200; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				long v = ((long)r.nextInt(9));
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				long v;
				switch(r.nextInt(4)) {
					case 0:
						v = ((long)r.nextInt(9));
						assertThat(list.remove((Long)v))
							.isEqualTo(expected.remove((Long)v));
						break;
					case 1:
						v = ((long)r.nextInt(9));
						assertThat(list.indexOf((Long)v))
							.isEqualTo(expected.indexOf((Long)v));
						break;
					case 2:
						v = ((long)r.nextInt(9));
						assertThat(list.lastIndexOf((Long)v))
							.isEqualTo(expected.lastIndexOf((Long)v));
						break;
					case 3:
						v = ((long)r.nextInt(9));
						assertThat(list.contains((Long)v))
							.isEqualTo(expected.contains((Long)v));
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
				List<Long> expected = new ArrayList<>();
				SimpleLongList list = new SimpleLongList();
				
				for(int i=0; i<100; i++) {
					//adding a value
					long v = ((long)r.nextInt(9));
					list.add(v);
					expected.add(v);
				}
				readOnlyTests(list, expected);
				return Arguments.of(list, expected);
			});
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void replaceAll(SimpleLongList list, List<Long> expected) {
		list.replaceAll(v -> 0L);
		expected.replaceAll(v -> 0L);

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void equalsLongList(SimpleLongList list, List<Long> expected) {
		assertThat(list).isEqualTo(list);
		assertThat(list.equals(list)).isTrue();
		
		SimpleLongList copy = new SimpleLongList();
		copy.addAll(list);
		assertThat(copy).isEqualTo(list);
		
		assertThat(list.equals((SimpleLongList)null)).isFalse();
		assertThat(list.equals((List<Long>)null)).isFalse();
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void testLongCollectionFunctions(SimpleLongList list, List<Long> expected) {
		assertThat(list.containsAll(list)).isTrue();
		
		SimpleLongList copy = new SimpleLongList();
		copy.addAll(list);
		copy.removeAllLongs(list);
		assertThat(copy).isEmpty();
		
		copy = new SimpleLongList();
		copy.addAll(list);
		copy.retainAllLongs(list);
		assertThat(copy.containsAll(list)).isTrue();
		
		list.removeAt(0);
		copy.retainAllLongs(list);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void removeIf(SimpleLongList list, List<Long> expected) {
		Random r1 = new Random(9);
		list.removeIf(v -> r1.nextBoolean());
		Random r2 = new Random(9);
		expected.removeIf(v -> r2.nextBoolean());

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void get(SimpleLongList list, List<Long> expected) {
		for(int i = 0; i < expected.size(); i++) {
			assertThat(list.get(i)).isEqualTo(expected.get(i));
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void set(SimpleLongList list, List<Long> expected) {
		Random r = new Random(9);
		for(int i = 0; i < expected.size(); i++) {
			long v = ((long)r.nextInt(9));
			assertThat(list.set(i, v)).isEqualTo(expected.set(i, v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void add(SimpleLongList list, List<Long> expected) {
		Random r = new Random(9);
		for(int i = 0; i < 50; i++) {
			long v = ((long)r.nextInt(9));
			assertThat(list.add(v)).isEqualTo(expected.add(v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void forEach(SimpleLongList list, List<Long> expected) {
		List<Long> collected = new ArrayList<>();
		list.forEach(v->collected.add(v));
		assertThat(collected).containsExactlyInAnyOrderElementsOf(expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void listIterator(SimpleLongList list, List<Long> expected) {
		SimpleLongList copy = new SimpleLongList();
		copy.addAll(list);
		Random r = new Random(9);
		long v = ((long)r.nextInt(9));
		list.listIterator().add(v);
		assertThat(list.get(0)).isEqualTo(v);
		assertThat(list.subList(1, list.size())).containsExactlyInAnyOrderElementsOf(copy);
	}
	
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void replaceAllLongs(SimpleLongList list, List<Long> expected) {
		list.replaceAllLongs(v -> 0L);
		expected.replaceAll(v -> 0L);
		
		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void removeLongIf(SimpleLongList list, List<Long> expected) {
		Random r1 = new Random(9);
		list.removeLongIf(v -> r1.nextBoolean());
		Random r2 = new Random(9);
		expected.removeIf(v -> r2.nextBoolean());

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void forEachLong(SimpleLongList list, List<Long> expected) {
		List<Long> collected = new ArrayList<>();
		list.forEachLong(v->collected.add(v));
		assertThat(collected).containsExactlyInAnyOrderElementsOf(expected);
	}
	
	
	private static  void readOnlyTests(SimpleLongList list, List<Long> expected) {
		List<Object> unexpected = new ArrayList<>(expected);
		unexpected.add(new Object());
	
	
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		
		assertThat(list.toArray(Long[]::new)).isEqualTo(expected.toArray(new Long[expected.size()]));
		
		assertThat(list).containsExactlyElementsOf(expected);
		
		//contains all and negative test
		assertThat(expected.containsAll(list)).isTrue();
		assertThat(list.containsAllLongs(list)).isTrue();
		assertThat(list.containsAll(expected)).isTrue();
		assertThat(list.containsAll(unexpected)).isFalse();
		
		assertThat(list.stream()).containsExactlyElementsOf(expected);
		assertThat(list.parallelStream()).containsExactlyInAnyOrderElementsOf(expected);
		
		assertThat(list.streamLongs()).containsExactlyElementsOf(expected);
		assertThat(list.parallelStreamLongs()).containsExactlyInAnyOrderElementsOf(expected);
		
		
		assertThat(list.spliterator().characteristics()).isEqualTo(expected.spliterator().characteristics());
		assertThat(Spliterators.iterator(list.spliterator())).toIterable().containsExactlyElementsOf(()->Spliterators.iterator(expected.spliterator()));
		
		assertThat(list.hashCode()).isEqualTo(expected.hashCode());
		assertThat(list.equals(expected));
	}
}