package com.github.powerlibraries.primitive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Spliterators;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class AbstractObjectListTest<E> {

	@Test
	public void guardConditionsTest() {
		SimpleObjectList list = new SimpleObjectList();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<E> expected = new ArrayList<>();
		SimpleObjectList list = new SimpleObjectList();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				E v = (E)TimeUnit.values()[r.nextInt(7)];
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				E v;
				switch(r.nextInt(3)) {
					case 0:
						v = (E)TimeUnit.values()[r.nextInt(7)];
						assertThat(list.remove((E)v))
							.isEqualTo(expected.remove((E)v));
						break;
					case 1:
						v = (E)TimeUnit.values()[r.nextInt(7)];
						assertThat(list.indexOf((E)v))
							.isEqualTo(expected.indexOf((E)v));
						break;
					case 2:
						v = (E)TimeUnit.values()[r.nextInt(7)];
						assertThat(list.contains((E)v))
							.isEqualTo(expected.contains((E)v));
						break;
				}
			}
			
			readOnlyTests(list, expected);
		}
		
		//replace all elements with null
		list.replaceAll(v -> null);
		expected.replaceAll(v -> null);
		readOnlyTests(list, expected);
		
		
		readOnlyTests(list, expected);
	}
	
	private void readOnlyTests(SimpleObjectList list, List<E> expected) {
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		assertThat(list.toArray(Object[]::new)).isEqualTo(expected.toArray(new Object[expected.size()]));
		
		assertThat(list).containsExactlyElementsOf(expected);
		
		assertThat(expected.containsAll(list)).isTrue();
		assertThat(list.containsAll(expected)).isTrue();
		assertThat(list.stream()).containsExactlyElementsOf(expected);
		
		
		assertThat(list.spliterator().characteristics()).isEqualTo(expected.spliterator().characteristics());
		assertThat(Spliterators.iterator(list.spliterator())).containsExactlyElementsOf(()->Spliterators.iterator(expected.spliterator()));
	}
}