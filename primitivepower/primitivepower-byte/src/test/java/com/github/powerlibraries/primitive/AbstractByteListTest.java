package com.github.powerlibraries.primitive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Spliterators;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class AbstractByteListTest {

	@Test
	public void guardConditionsTest() {
		SimpleByteList list = new SimpleByteList();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Byte> expected = new ArrayList<>();
		SimpleByteList list = new SimpleByteList();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				byte v = ((byte)r.nextInt(100));
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				byte v;
				switch(r.nextInt(3)) {
					case 0:
						v = ((byte)r.nextInt(100));
						assertThat(list.remove((Byte)v))
							.isEqualTo(expected.remove((Byte)v));
						break;
					case 1:
						v = ((byte)r.nextInt(100));
						assertThat(list.indexOf((Byte)v))
							.isEqualTo(expected.indexOf((Byte)v));
						break;
					case 2:
						v = ((byte)r.nextInt(100));
						assertThat(list.contains((Byte)v))
							.isEqualTo(expected.contains((Byte)v));
						break;
				}
			}
			
			readOnlyTests(list, expected);
		}
		
		//replace all elements with 0
		list.replaceAll(v -> 0);
		expected.replaceAll(v -> 0);
		readOnlyTests(list, expected);
		
		
		readOnlyTests(list, expected);
	}
	
	private void readOnlyTests(SimpleByteList list, List<Byte> expected) {
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		assertThat(list.toArray(Byte[]::new)).isEqualTo(expected.toArray(new Byte[expected.size()]));
		
		assertThat(list).containsExactlyElementsOf(expected);
		
		assertThat(expected.containsAll(list)).isTrue();
		assertThat(list.containsAll(expected)).isTrue();
		assertThat(list.stream()).containsExactlyElementsOf(expected);
		
		
		assertThat(list.spliterator().characteristics()).isEqualTo(expected.spliterator().characteristics());
		assertThat(Spliterators.iterator(list.spliterator())).containsExactlyElementsOf(()->Spliterators.iterator(expected.spliterator()));
	}
}