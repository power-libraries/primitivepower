package com.github.powerlibraries.primitive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Spliterators;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class AbstractDoubleListTest {

	@Test
	public void guardConditionsTest() {
		SimpleDoubleList list = new SimpleDoubleList();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Double> expected = new ArrayList<>();
		SimpleDoubleList list = new SimpleDoubleList();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				double v = r.nextDouble();
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				double v;
				switch(r.nextInt(3)) {
					case 0:
						v = r.nextDouble();
						assertThat(list.remove((Double)v))
							.isEqualTo(expected.remove((Double)v));
						break;
					case 1:
						v = r.nextDouble();
						assertThat(list.indexOf((Double)v))
							.isEqualTo(expected.indexOf((Double)v));
						break;
					case 2:
						v = r.nextDouble();
						assertThat(list.contains((Double)v))
							.isEqualTo(expected.contains((Double)v));
						break;
				}
			}
			
			readOnlyTests(list, expected);
		}
		
		//replace all elements with 0d
		list.replaceAll(v -> 0d);
		expected.replaceAll(v -> 0d);
		readOnlyTests(list, expected);
		
		list.replaceAllDoubles(v -> 0d);
		readOnlyTests(list, expected);
	}
	
	private void readOnlyTests(SimpleDoubleList list, List<Double> expected) {
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		assertThat(list.toArray(Double[]::new)).isEqualTo(expected.toArray(new Double[expected.size()]));
		
		assertThat(expected).containsAll(list);
		assertThat(list).containsAll(expected);
		
		assertThat(list.spliterator().characteristics()).isEqualTo(expected.spliterator().characteristics());
		assertThat(Spliterators.iterator(list.spliterator())).containsExactlyElementsOf(()->Spliterators.iterator(expected.spliterator()));
	}
}