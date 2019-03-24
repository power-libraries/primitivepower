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

public class AbstractCharListTest {

	@Test
	public void guardConditionsTest() {
		SimpleCharList list = new SimpleCharList();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
		assertThat(list.indexOf(null)).isEqualTo(-1);
		assertThat(list.lastIndexOf(null)).isEqualTo(-1);
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Character> expected = new ArrayList<>();
		SimpleCharList list = new SimpleCharList();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				char v = ((char)r.nextInt(9));
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				char v;
				switch(r.nextInt(4)) {
					case 0:
						v = ((char)r.nextInt(9));
						assertThat(list.remove((Character)v))
							.isEqualTo(expected.remove((Character)v));
						break;
					case 1:
						v = ((char)r.nextInt(9));
						assertThat(list.indexOf((Character)v))
							.isEqualTo(expected.indexOf((Character)v));
						break;
					case 2:
						v = ((char)r.nextInt(9));
						assertThat(list.lastIndexOf((Character)v))
							.isEqualTo(expected.lastIndexOf((Character)v));
						break;
					case 3:
						v = ((char)r.nextInt(9));
						assertThat(list.contains((Character)v))
							.isEqualTo(expected.contains((Character)v));
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
				List<Character> expected = new ArrayList<>();
				SimpleCharList list = new SimpleCharList();
				
				for(int i=0; i<100; i++) {
					//adding a value
					char v = ((char)r.nextInt(9));
					list.add(v);
					expected.add(v);
				}
				readOnlyTests(list, expected);
				return Arguments.of(list, expected);
			});
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void replaceAll(SimpleCharList list, List<Character> expected) {
		list.replaceAll(v -> '\u0000');
		expected.replaceAll(v -> '\u0000');

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void equalsCharList(SimpleCharList list, List<Character> expected) {
		assertThat(list).isEqualTo(list);
		assertThat(list.equals(list)).isTrue();
		
		SimpleCharList copy = new SimpleCharList();
		copy.addAll(list);
		assertThat(copy).isEqualTo(list);
		
		assertThat(list.equals((SimpleCharList)null)).isFalse();
		assertThat(list.equals((List<Character>)null)).isFalse();
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void testCharCollectionFunctions(SimpleCharList list, List<Character> expected) {
		assertThat(list.containsAll(list)).isTrue();
		
		SimpleCharList copy = new SimpleCharList();
		copy.addAll(list);
		copy.removeAllChars(list);
		assertThat(copy).isEmpty();
		
		copy = new SimpleCharList();
		copy.addAll(list);
		copy.retainAllChars(list);
		assertThat(copy.containsAll(list)).isTrue();
		
		list.removeAt(0);
		copy.retainAllChars(list);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void removeIf(SimpleCharList list, List<Character> expected) {
		Random r1 = new Random(9);
		list.removeIf(v -> r1.nextBoolean());
		Random r2 = new Random(9);
		expected.removeIf(v -> r2.nextBoolean());

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void get(SimpleCharList list, List<Character> expected) {
		for(int i = 0; i < expected.size(); i++) {
			assertThat(list.get(i)).isEqualTo(expected.get(i));
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void set(SimpleCharList list, List<Character> expected) {
		Random r = new Random(9);
		for(int i = 0; i < expected.size(); i++) {
			char v = ((char)r.nextInt(9));
			assertThat(list.set(i, v)).isEqualTo(expected.set(i, v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void add(SimpleCharList list, List<Character> expected) {
		Random r = new Random(9);
		for(int i = 0; i < 50; i++) {
			char v = ((char)r.nextInt(9));
			assertThat(list.add(v)).isEqualTo(expected.add(v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void forEach(SimpleCharList list, List<Character> expected) {
		List<Character> collected = new ArrayList<>();
		list.forEach(v->collected.add(v));
		assertThat(collected).containsExactlyInAnyOrderElementsOf(expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void listIterator(SimpleCharList list, List<Character> expected) {
		SimpleCharList copy = new SimpleCharList();
		copy.addAll(list);
		Random r = new Random(9);
		char v = ((char)r.nextInt(9));
		list.listIterator().add(v);
		assertThat(list.get(0)).isEqualTo(v);
		assertThat(list.subList(1, list.size())).containsExactlyInAnyOrderElementsOf(copy);
	}
	
	
	
	private static  void readOnlyTests(SimpleCharList list, List<Character> expected) {
		List<Object> unexpected = new ArrayList<>(expected);
		unexpected.add(new Object());
	
	
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		
		assertThat(list.toArray(Character[]::new)).isEqualTo(expected.toArray(new Character[expected.size()]));
		
		assertThat(list).containsExactlyElementsOf(expected);
		
		//contains all and negative test
		assertThat(expected.containsAll(list)).isTrue();
		assertThat(list.containsAllChars(list)).isTrue();
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