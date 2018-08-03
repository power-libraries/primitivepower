package com.github.powerlibraries.primitive;

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

public class AbstractIntListTest {

	@Test
	public void guardConditionsTest() {
		SimpleIntList list = new SimpleIntList();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
		assertThat(list.indexOf(null)).isEqualTo(-1);
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Integer> expected = new ArrayList<>();
		SimpleIntList list = new SimpleIntList();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				int v = r.nextInt(9);
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				int v;
				switch(r.nextInt(4)) {
					case 0:
						v = r.nextInt(9);
						assertThat(list.remove((Integer)v))
							.isEqualTo(expected.remove((Integer)v));
						break;
					case 1:
						v = r.nextInt(9);
						assertThat(list.indexOf((Integer)v))
							.isEqualTo(expected.indexOf((Integer)v));
						break;
					case 2:
						v = r.nextInt(9);
						assertThat(list.lastIndexOf((Integer)v))
							.isEqualTo(expected.lastIndexOf((Integer)v));
						break;
					case 3:
						v = r.nextInt(9);
						assertThat(list.contains((Integer)v))
							.isEqualTo(expected.contains((Integer)v));
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
				List<Integer> expected = new ArrayList<>();
				SimpleIntList list = new SimpleIntList();
				
				for(int i=0; i<100; i++) {
					//adding a value
					int v = r.nextInt(9);
					list.add(v);
					expected.add(v);
				}
				readOnlyTests(list, expected);
				return Arguments.of(list, expected);
			});
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void replaceAll(SimpleIntList list, List<Integer> expected) {
		list.replaceAll(v -> 0);
		expected.replaceAll(v -> 0);

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void removeIf(SimpleIntList list, List<Integer> expected) {
		Random r1 = new Random(9);
		list.removeIf(v -> r1.nextBoolean());
		Random r2 = new Random(9);
		expected.removeIf(v -> r2.nextBoolean());

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void get(SimpleIntList list, List<Integer> expected) {
		for(int i = 0; i < expected.size(); i++) {
			assertThat(list.get(i)).isEqualTo(expected.get(i));
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void set(SimpleIntList list, List<Integer> expected) {
		Random r = new Random(9);
		for(int i = 0; i < expected.size(); i++) {
			int v = r.nextInt(9);
			assertThat(list.set(i, v)).isEqualTo(expected.set(i, v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void add(SimpleIntList list, List<Integer> expected) {
		Random r = new Random(9);
		for(int i = 0; i < 50; i++) {
			int v = r.nextInt(9);
			assertThat(list.add(v)).isEqualTo(expected.add(v));
			readOnlyTests(list, expected);
		}
	}
	
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void replaceAllInts(SimpleIntList list, List<Integer> expected) {
		list.replaceAllInts(v -> 0);
		expected.replaceAll(v -> 0);
		
		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void removeIntIf(SimpleIntList list, List<Integer> expected) {
		Random r1 = new Random(9);
		list.removeIntIf(v -> r1.nextBoolean());
		Random r2 = new Random(9);
		expected.removeIf(v -> r2.nextBoolean());

		readOnlyTests(list, expected);
	}
	
	
	private static  void readOnlyTests(SimpleIntList list, List<Integer> expected) {
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		assertThat(list.toArray(Integer[]::new)).isEqualTo(expected.toArray(new Integer[expected.size()]));
		
		assertThat(list).containsExactlyElementsOf(expected);
		
		assertThat(expected.containsAll(list)).isTrue();
		assertThat(list.containsAll(expected)).isTrue();
		assertThat(list.stream()).containsExactlyElementsOf(expected);
		assertThat(list.parallelStream()).containsExactlyInAnyOrderElementsOf(expected);
		
		assertThat(list.streamInts()).containsExactlyElementsOf(expected);
		assertThat(list.parallelStreamInts()).containsExactlyInAnyOrderElementsOf(expected);
		
		
		assertThat(list.spliterator().characteristics()).isEqualTo(expected.spliterator().characteristics());
		assertThat(Spliterators.iterator(list.spliterator())).containsExactlyElementsOf(()->Spliterators.iterator(expected.spliterator()));
	}
}