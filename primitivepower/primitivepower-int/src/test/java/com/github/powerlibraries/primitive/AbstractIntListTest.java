package com.github.powerlibraries.primitive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Spliterators;

import org.junit.jupiter.api.Test;

public class AbstractIntListTest {

	@Test
	public void guardConditionsTest() {
		SimpleIntList list = new SimpleIntList();
		
		assertThat(list.contains(null)).isFalse();
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Integer> expected = new ArrayList<>();
		SimpleIntList list = new SimpleIntList();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				int v = r.nextInt();
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				int v;
				switch(r.nextInt(3)) {
					case 0:
						v = r.nextInt();
						assertThat(list.remove((Integer)v))
							.isEqualTo(expected.remove((Integer)v));
						break;
					case 1:
						v = r.nextInt();
						assertThat(list.indexOf((Integer)v))
							.isEqualTo(expected.indexOf((Integer)v));
						break;
					case 2:
						v = r.nextInt();
						assertThat(list.contains((Integer)v))
							.isEqualTo(expected.contains((Integer)v));
						break;
				}
			}
			
			readOnlyTests(list, expected);
		}
		
		//replace all elements with 0
		list.replaceAll(v -> 0);
		expected.replaceAll(v -> 0);
		readOnlyTests(list, expected);
	}
	
	private void readOnlyTests(SimpleIntList list, List<Integer> expected) {
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		assertThat(list.toArray(Integer[]::new)).isEqualTo(expected.toArray(new Integer[expected.size()]));
		
		assertThat(expected).containsAll(list);
		assertThat(list).containsAll(expected);
		
		assertThat(list.spliterator().characteristics()).isEqualTo(expected.spliterator().characteristics());
		assertThat(Spliterators.iterator(list.spliterator())).containsExactlyElementsOf(()->Spliterators.iterator(expected.spliterator()));
	}
}