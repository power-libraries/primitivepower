package com.github.powerlibraries.primitive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Spliterators;

import org.junit.jupiter.api.Test;

public class AbstractFloatListTest {

	@Test
	public void guardConditionsTest() {
		SimpleFloatList list = new SimpleFloatList();
		
		assertThat(list.contains(null)).isFalse();
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Float> expected = new ArrayList<>();
		SimpleFloatList list = new SimpleFloatList();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				float v = r.nextFloat();
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				float v;
				switch(r.nextInt(3)) {
					case 0:
						v = r.nextFloat();
						assertThat(list.remove((Float)v))
							.isEqualTo(expected.remove((Float)v));
						break;
					case 1:
						v = r.nextFloat();
						assertThat(list.indexOf((Float)v))
							.isEqualTo(expected.indexOf((Float)v));
						break;
					case 2:
						v = r.nextFloat();
						assertThat(list.contains((Float)v))
							.isEqualTo(expected.contains((Float)v));
						break;
				}
			}
			
			readOnlyTests(list, expected);
		}
		
		//replace all elements with 0f
		list.replaceAll(v -> 0f);
		expected.replaceAll(v -> 0f);
		readOnlyTests(list, expected);
	}
	
	private void readOnlyTests(SimpleFloatList list, List<Float> expected) {
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		assertThat(list.toArray(Float[]::new)).isEqualTo(expected.toArray(new Float[expected.size()]));
		
		assertThat(expected).containsAll(list);
		assertThat(list).containsAll(expected);
		
		assertThat(list.spliterator().characteristics()).isEqualTo(expected.spliterator().characteristics());
		assertThat(Spliterators.iterator(list.spliterator())).containsExactlyElementsOf(()->Spliterators.iterator(expected.spliterator()));
	}
}