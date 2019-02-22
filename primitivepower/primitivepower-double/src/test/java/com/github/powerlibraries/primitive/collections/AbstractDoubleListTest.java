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

public class AbstractDoubleListTest {

	@Test
	public void guardConditionsTest() {
		SimpleDoubleList list = new SimpleDoubleList();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
		assertThat(list.indexOf(null)).isEqualTo(-1);
		assertThat(list.lastIndexOf(null)).isEqualTo(-1);
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Double> expected = new ArrayList<>();
		SimpleDoubleList list = new SimpleDoubleList();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				double v = ((double)r.nextInt(9));
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				double v;
				switch(r.nextInt(4)) {
					case 0:
						v = ((double)r.nextInt(9));
						assertThat(list.remove((Double)v))
							.isEqualTo(expected.remove((Double)v));
						break;
					case 1:
						v = ((double)r.nextInt(9));
						assertThat(list.indexOf((Double)v))
							.isEqualTo(expected.indexOf((Double)v));
						break;
					case 2:
						v = ((double)r.nextInt(9));
						assertThat(list.lastIndexOf((Double)v))
							.isEqualTo(expected.lastIndexOf((Double)v));
						break;
					case 3:
						v = ((double)r.nextInt(9));
						assertThat(list.contains((Double)v))
							.isEqualTo(expected.contains((Double)v));
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
				List<Double> expected = new ArrayList<>();
				SimpleDoubleList list = new SimpleDoubleList();
				
				for(int i=0; i<100; i++) {
					//adding a value
					double v = ((double)r.nextInt(9));
					list.add(v);
					expected.add(v);
				}
				readOnlyTests(list, expected);
				return Arguments.of(list, expected);
			});
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void replaceAll(SimpleDoubleList list, List<Double> expected) {
		list.replaceAll(v -> 0d);
		expected.replaceAll(v -> 0d);

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void equalsDoubleList(SimpleDoubleList list, List<Double> expected) {
		assertThat(list).isEqualTo(list);
		assertThat(list.equals(list)).isTrue();
		
		SimpleDoubleList copy = new SimpleDoubleList();
		copy.addAll(list);
		assertThat(copy).isEqualTo(list);
		
		assertThat(list.equals((SimpleDoubleList)null)).isFalse();
		assertThat(list.equals((List<Double>)null)).isFalse();
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void removeIf(SimpleDoubleList list, List<Double> expected) {
		Random r1 = new Random(9);
		list.removeIf(v -> r1.nextBoolean());
		Random r2 = new Random(9);
		expected.removeIf(v -> r2.nextBoolean());

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void get(SimpleDoubleList list, List<Double> expected) {
		for(int i = 0; i < expected.size(); i++) {
			assertThat(list.get(i)).isEqualTo(expected.get(i));
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void set(SimpleDoubleList list, List<Double> expected) {
		Random r = new Random(9);
		for(int i = 0; i < expected.size(); i++) {
			double v = ((double)r.nextInt(9));
			assertThat(list.set(i, v)).isEqualTo(expected.set(i, v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void add(SimpleDoubleList list, List<Double> expected) {
		Random r = new Random(9);
		for(int i = 0; i < 50; i++) {
			double v = ((double)r.nextInt(9));
			assertThat(list.add(v)).isEqualTo(expected.add(v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void forEach(SimpleDoubleList list, List<Double> expected) {
		List collected = new ArrayList();
		list.forEach(v->collected.add(v));
		assertThat(collected).containsExactlyInAnyOrderElementsOf(expected);
	}
	
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void replaceAllDoubles(SimpleDoubleList list, List<Double> expected) {
		list.replaceAllDoubles(v -> 0d);
		expected.replaceAll(v -> 0d);
		
		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void removeDoubleIf(SimpleDoubleList list, List<Double> expected) {
		Random r1 = new Random(9);
		list.removeDoubleIf(v -> r1.nextBoolean());
		Random r2 = new Random(9);
		expected.removeIf(v -> r2.nextBoolean());

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void forEachDouble(SimpleDoubleList list, List<Double> expected) {
		List<Double> collected = new ArrayList<>();
		list.forEachDouble(v->collected.add(v));
		assertThat(collected).containsExactlyInAnyOrderElementsOf(expected);
	}
	
	
	private static  void readOnlyTests(SimpleDoubleList list, List<Double> expected) {
		List unexpected = new ArrayList(expected);
		unexpected.add(new Object());
	
	
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		assertThat(list.toArray(Double[]::new)).isEqualTo(expected.toArray(new Double[expected.size()]));
		
		assertThat(list).containsExactlyElementsOf(expected);
		
		//contains all and negative test
		assertThat(expected.containsAll(list)).isTrue();
		assertThat(list.containsAll(expected)).isTrue();
		assertThat(list.containsAll(unexpected)).isFalse();
		
		assertThat(list.stream()).containsExactlyElementsOf(expected);
		assertThat(list.parallelStream()).containsExactlyInAnyOrderElementsOf(expected);
		
		assertThat(list.streamDoubles()).containsExactlyElementsOf(expected);
		assertThat(list.parallelStreamDoubles()).containsExactlyInAnyOrderElementsOf(expected);
		
		
		assertThat(list.spliterator().characteristics()).isEqualTo(expected.spliterator().characteristics());
		assertThat(Spliterators.iterator(list.spliterator())).containsExactlyElementsOf(()->Spliterators.iterator(expected.spliterator()));
		
		assertThat(list.hashCode()).isEqualTo(expected.hashCode());
		assertThat(list.equals(expected));
	}
}