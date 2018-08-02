package com.github.powerlibraries.primitive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Spliterators;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class AbstractLongListTest {

	@Test
	public void guardConditionsTest() {
		SimpleLongList list = new SimpleLongList();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Long> expected = new ArrayList<>();
		SimpleLongList list = new SimpleLongList();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				long v = r.nextLong();
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				long v;
				switch(r.nextInt(3)) {
					case 0:
						v = r.nextLong();
						assertThat(list.remove((Long)v))
							.isEqualTo(expected.remove((Long)v));
						break;
					case 1:
						v = r.nextLong();
						assertThat(list.indexOf((Long)v))
							.isEqualTo(expected.indexOf((Long)v));
						break;
					case 2:
						v = r.nextLong();
						assertThat(list.contains((Long)v))
							.isEqualTo(expected.contains((Long)v));
						break;
				}
			}
			
			readOnlyTests(list, expected);
		}
		
		//replace all elements with 0L
		list.replaceAll(v -> 0L);
		expected.replaceAll(v -> 0L);
		readOnlyTests(list, expected);
		
		
		list.replaceAllLongs(v -> 0L);
		
		readOnlyTests(list, expected);
	}
	
	private void readOnlyTests(SimpleLongList list, List<Long> expected) {
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		assertThat(list.toArray(Long[]::new)).isEqualTo(expected.toArray(new Long[expected.size()]));
		
		assertThat(list).containsExactlyElementsOf(expected);
		
		assertThat(expected.containsAll(list)).isTrue();
		assertThat(list.containsAll(expected)).isTrue();
		assertThat(list.stream()).containsExactlyElementsOf(expected);
		
		assertThat(list.streamLongs()).containsExactlyElementsOf(expected);
		
		
		assertThat(list.spliterator().characteristics()).isEqualTo(expected.spliterator().characteristics());
		assertThat(Spliterators.iterator(list.spliterator())).containsExactlyElementsOf(()->Spliterators.iterator(expected.spliterator()));
	}
}