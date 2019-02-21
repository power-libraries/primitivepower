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

public class AbstractBooleanListTest {

	@Test
	public void guardConditionsTest() {
		SimpleBooleanList list = new SimpleBooleanList();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
		assertThat(list.indexOf(null)).isEqualTo(-1);
		assertThat(list.lastIndexOf(null)).isEqualTo(-1);
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Boolean> expected = new ArrayList<>();
		SimpleBooleanList list = new SimpleBooleanList();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				boolean v = r.nextBoolean();
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				boolean v;
				switch(r.nextInt(4)) {
					case 0:
						v = r.nextBoolean();
						assertThat(list.remove((Boolean)v))
							.isEqualTo(expected.remove((Boolean)v));
						break;
					case 1:
						v = r.nextBoolean();
						assertThat(list.indexOf((Boolean)v))
							.isEqualTo(expected.indexOf((Boolean)v));
						break;
					case 2:
						v = r.nextBoolean();
						assertThat(list.lastIndexOf((Boolean)v))
							.isEqualTo(expected.lastIndexOf((Boolean)v));
						break;
					case 3:
						v = r.nextBoolean();
						assertThat(list.contains((Boolean)v))
							.isEqualTo(expected.contains((Boolean)v));
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
				List<Boolean> expected = new ArrayList<>();
				SimpleBooleanList list = new SimpleBooleanList();
				
				for(int i=0; i<100; i++) {
					//adding a value
					boolean v = r.nextBoolean();
					list.add(v);
					expected.add(v);
				}
				readOnlyTests(list, expected);
				return Arguments.of(list, expected);
			});
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void replaceAll(SimpleBooleanList list, List<Boolean> expected) {
		list.replaceAll(v -> false);
		expected.replaceAll(v -> false);

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void removeIf(SimpleBooleanList list, List<Boolean> expected) {
		Random r1 = new Random(9);
		list.removeIf(v -> r1.nextBoolean());
		Random r2 = new Random(9);
		expected.removeIf(v -> r2.nextBoolean());

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void get(SimpleBooleanList list, List<Boolean> expected) {
		for(int i = 0; i < expected.size(); i++) {
			assertThat(list.get(i)).isEqualTo(expected.get(i));
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void set(SimpleBooleanList list, List<Boolean> expected) {
		Random r = new Random(9);
		for(int i = 0; i < expected.size(); i++) {
			boolean v = r.nextBoolean();
			assertThat(list.set(i, v)).isEqualTo(expected.set(i, v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void add(SimpleBooleanList list, List<Boolean> expected) {
		Random r = new Random(9);
		for(int i = 0; i < 50; i++) {
			boolean v = r.nextBoolean();
			assertThat(list.add(v)).isEqualTo(expected.add(v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void forEach(SimpleBooleanList list, List<Boolean> expected) {
		List collected = new ArrayList();
		list.forEach(v->collected.add(v));
		assertThat(collected).containsExactlyInAnyOrderElementsOf(expected);
	}
	
	
	
	private static  void readOnlyTests(SimpleBooleanList list, List<Boolean> expected) {
		List<Boolean> unexpected = new ArrayList<>(expected);
		unexpected.add(null);
	
	
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		assertThat(list.toArray(Boolean[]::new)).isEqualTo(expected.toArray(new Boolean[expected.size()]));
		
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