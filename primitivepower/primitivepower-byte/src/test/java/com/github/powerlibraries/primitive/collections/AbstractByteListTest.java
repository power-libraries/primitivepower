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

public class AbstractByteListTest {

	@Test
	public void guardConditionsTest() {
		SimpleByteList list = new SimpleByteList();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
		assertThat(list.indexOf(null)).isEqualTo(-1);
		assertThat(list.lastIndexOf(null)).isEqualTo(-1);
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Byte> expected = new ArrayList<>();
		SimpleByteList list = new SimpleByteList();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				byte v = ((byte)r.nextInt(9));
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				byte v;
				switch(r.nextInt(4)) {
					case 0:
						v = ((byte)r.nextInt(9));
						assertThat(list.remove((Byte)v))
							.isEqualTo(expected.remove((Byte)v));
						break;
					case 1:
						v = ((byte)r.nextInt(9));
						assertThat(list.indexOf((Byte)v))
							.isEqualTo(expected.indexOf((Byte)v));
						break;
					case 2:
						v = ((byte)r.nextInt(9));
						assertThat(list.lastIndexOf((Byte)v))
							.isEqualTo(expected.lastIndexOf((Byte)v));
						break;
					case 3:
						v = ((byte)r.nextInt(9));
						assertThat(list.contains((Byte)v))
							.isEqualTo(expected.contains((Byte)v));
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
				List<Byte> expected = new ArrayList<>();
				SimpleByteList list = new SimpleByteList();
				
				for(int i=0; i<100; i++) {
					//adding a value
					byte v = ((byte)r.nextInt(9));
					list.add(v);
					expected.add(v);
				}
				readOnlyTests(list, expected);
				return Arguments.of(list, expected);
			});
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void replaceAll(SimpleByteList list, List<Byte> expected) {
		list.replaceAll(v -> ((byte)0));
		expected.replaceAll(v -> ((byte)0));

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void equalsByteList(SimpleByteList list, List<Byte> expected) {
		assertThat(list).isEqualTo(list);
		assertThat(list.equals(list)).isTrue();
		
		SimpleByteList copy = new SimpleByteList();
		copy.addAll(list);
		assertThat(copy).isEqualTo(list);
		
		assertThat(list.equals((SimpleByteList)null)).isFalse();
		assertThat(list.equals((List<Byte>)null)).isFalse();
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void testByteCollectionFunctions(SimpleByteList list, List<Byte> expected) {
		assertThat(list.containsAll(list)).isTrue();
		
		SimpleByteList copy = new SimpleByteList();
		copy.addAll(list);
		copy.removeAllBytes(list);
		assertThat(copy).isEmpty();
		
		copy = new SimpleByteList();
		copy.addAll(list);
		copy.retainAllBytes(list);
		assertThat(copy.containsAll(list)).isTrue();
		
		list.removeAt(0);
		copy.retainAllBytes(list);
		assertThat(copy).containsExactlyElementsOf(list);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void removeIf(SimpleByteList list, List<Byte> expected) {
		Random r1 = new Random(9);
		list.removeIf(v -> r1.nextBoolean());
		Random r2 = new Random(9);
		expected.removeIf(v -> r2.nextBoolean());

		readOnlyTests(list, expected);
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void get(SimpleByteList list, List<Byte> expected) {
		for(int i = 0; i < expected.size(); i++) {
			assertThat(list.get(i)).isEqualTo(expected.get(i));
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void set(SimpleByteList list, List<Byte> expected) {
		Random r = new Random(9);
		for(int i = 0; i < expected.size(); i++) {
			byte v = ((byte)r.nextInt(9));
			assertThat(list.set(i, v)).isEqualTo(expected.set(i, v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void add(SimpleByteList list, List<Byte> expected) {
		Random r = new Random(9);
		for(int i = 0; i < 50; i++) {
			byte v = ((byte)r.nextInt(9));
			assertThat(list.add(v)).isEqualTo(expected.add(v));
			readOnlyTests(list, expected);
		}
	}
	
	@ParameterizedTest(name="{index}") @MethodSource("generateLists")
	public void forEach(SimpleByteList list, List<Byte> expected) {
		List<Byte> collected = new ArrayList<>();
		list.forEach(v->collected.add(v));
		assertThat(collected).containsExactlyInAnyOrderElementsOf(expected);
	}
	
	
	
	private static  void readOnlyTests(SimpleByteList list, List<Byte> expected) {
		List<Object> unexpected = new ArrayList<>(expected);
		unexpected.add(new Object());
	
	
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		
		assertThat(list.toArray(Byte[]::new)).isEqualTo(expected.toArray(new Byte[expected.size()]));
		
		assertThat(list).containsExactlyElementsOf(expected);
		
		//contains all and negative test
		assertThat(expected.containsAll(list)).isTrue();
		assertThat(list.containsAllBytes(list)).isTrue();
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