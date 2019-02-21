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

public class AbstractFloatListTest {

	@Test
	public void guardConditionsTest() {
		SimpleFloatList list = new SimpleFloatList();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
		assertThat(list.indexOf(null)).isEqualTo(-1);
		assertThat(list.lastIndexOf(null)).isEqualTo(-1);
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Float> expected = new ArrayList<>();
		SimpleFloatList list = new SimpleFloatList();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				float v = ((float)r.nextInt(9));
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				float v;
				switch(r.nextInt(4)) {
					case 0:
						v = ((float)r.nextInt(9));
						assertThat(list.remove((Float)v))
							.isEqualTo(expected.remove((Float)v));
						break;
					case 1:
						v = ((float)r.nextInt(9));
						assertThat(list.indexOf((Float)v))
							.isEqualTo(expected.indexOf((Float)v));
						break;
					case 2:
						v = ((float)r.nextInt(9));
						assertThat(list.lastIndexOf((Float)v))
							.isEqualTo(expected.lastIndexOf((Float)v));
						break;
					case 3:
						v = ((float)r.nextInt(9));
						assertThat(list.contains((Float)v))
							.isEqualTo(expected.contains((Float)v));
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
				List<Float> expected = new ArrayList<>();
				SimpleFloatList list = new SimpleFloatList();
				
				for(int i=0; i<100; i++) {
					//adding a value
					float v = ((float)r.nextInt(9));
					list.add(v);
					expected.add(v);
				}
				readOnlyTests(list, expected);
				return Arguments.of(list, expected);
			});
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void replaceAll(SimpleFloatList list, List<Float> expected) {
		list.replaceAll(v -> 0f);
		expected.replaceAll(v -> 0f);

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void equalsFloatList(SimpleFloatList list, List<Float> expected) {
		assertThat(list).isEqualTo(list);
		assertThat(list.equals(list)).isTrue();
		
		SimpleFloatList copy = new SimpleFloatList();
		copy.addAll(list);
		assertThat(copy).isEqualTo(list);
		
		assertThat(list.equals((SimpleFloatList)null)).isFalse();
		assertThat(list.equals((List<Float>)null)).isFalse();
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void removeIf(SimpleFloatList list, List<Float> expected) {
		Random r1 = new Random(9);
		list.removeIf(v -> r1.nextBoolean());
		Random r2 = new Random(9);
		expected.removeIf(v -> r2.nextBoolean());

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void get(SimpleFloatList list, List<Float> expected) {
		for(int i = 0; i < expected.size(); i++) {
			assertThat(list.get(i)).isEqualTo(expected.get(i));
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void set(SimpleFloatList list, List<Float> expected) {
		Random r = new Random(9);
		for(int i = 0; i < expected.size(); i++) {
			float v = ((float)r.nextInt(9));
			assertThat(list.set(i, v)).isEqualTo(expected.set(i, v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void add(SimpleFloatList list, List<Float> expected) {
		Random r = new Random(9);
		for(int i = 0; i < 50; i++) {
			float v = ((float)r.nextInt(9));
			assertThat(list.add(v)).isEqualTo(expected.add(v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void forEach(SimpleFloatList list, List<Float> expected) {
		List collected = new ArrayList();
		list.forEach(v->collected.add(v));
		assertThat(collected).containsExactlyInAnyOrderElementsOf(expected);
	}
	
	
	
	private static  void readOnlyTests(SimpleFloatList list, List<Float> expected) {
		List unexpected = new ArrayList(expected);
		unexpected.add(new Object());
	
	
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		assertThat(list.toArray(Float[]::new)).isEqualTo(expected.toArray(new Float[expected.size()]));
		
		assertThat(list).containsExactlyElementsOf(expected);
		
		//contains all and negative test
		assertThat(expected.containsAll(list)).isTrue();
		assertThat(list.containsAll(expected)).isTrue();
		assertThat(list.containsAll(unexpected)).isFalse();
		
		assertThat(list.stream()).containsExactlyElementsOf(expected);
		assertThat(list.parallelStream()).containsExactlyInAnyOrderElementsOf(expected);
		
		
		assertThat(list.spliterator().characteristics()).isEqualTo(expected.spliterator().characteristics());
		assertThat(Spliterators.iterator(list.spliterator())).containsExactlyElementsOf(()->Spliterators.iterator(expected.spliterator()));
		
		assertThat(list.hashCode()).isEqualTo(expected.hashCode());
		assertThat(list.equals(expected));
	}
}