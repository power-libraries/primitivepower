package com.github.powerlibraries.primitive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Spliterators;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class AbstractShortListTest {

	@Test
	public void guardConditionsTest() {
		SimpleShortList list = new SimpleShortList();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Short> expected = new ArrayList<>();
		SimpleShortList list = new SimpleShortList();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				short v = ((short)r.nextInt(100));
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				short v;
				switch(r.nextInt(3)) {
					case 0:
						v = ((short)r.nextInt(100));
						assertThat(list.remove((Short)v))
							.isEqualTo(expected.remove((Short)v));
						break;
					case 1:
						v = ((short)r.nextInt(100));
						assertThat(list.indexOf((Short)v))
							.isEqualTo(expected.indexOf((Short)v));
						break;
					case 2:
						v = ((short)r.nextInt(100));
						assertThat(list.contains((Short)v))
							.isEqualTo(expected.contains((Short)v));
						break;
				}
			}
			
			readOnlyTests(list, expected);
		}
		
		//replace all elements with ((short)0)
		list.replaceAll(v -> ((short)0));
		expected.replaceAll(v -> ((short)0));
		readOnlyTests(list, expected);
		
		list.replaceAllShorts(v -> ((short)0));
		readOnlyTests(list, expected);
	}
	
	private void readOnlyTests(SimpleShortList list, List<Short> expected) {
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		assertThat(list.toArray(Short[]::new)).isEqualTo(expected.toArray(new Short[expected.size()]));
		
		assertThat(expected).containsAll(list);
		assertThat(list).containsAll(expected);
		
		assertThat(list.spliterator().characteristics()).isEqualTo(expected.spliterator().characteristics());
		assertThat(Spliterators.iterator(list.spliterator())).containsExactlyElementsOf(()->Spliterators.iterator(expected.spliterator()));
	}
}