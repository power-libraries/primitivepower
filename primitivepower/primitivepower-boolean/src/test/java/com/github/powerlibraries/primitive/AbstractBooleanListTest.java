package com.github.powerlibraries.primitive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Spliterators;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class AbstractBooleanListTest {

	@Test
	public void guardConditionsTest() {
		SimpleBooleanList list = new SimpleBooleanList();
		
		assertThat(list.contains(null)).isFalse();
		assertThat(list.remove(null)).isFalse();
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
				switch(r.nextInt(3)) {
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
						assertThat(list.contains((Boolean)v))
							.isEqualTo(expected.contains((Boolean)v));
						break;
				}
			}
			
			readOnlyTests(list, expected);
		}
		
		//replace all elements with false
		list.replaceAll(v -> false);
		expected.replaceAll(v -> false);
		readOnlyTests(list, expected);
		
		list.replaceAllBooleans(v -> false);
		readOnlyTests(list, expected);
	}
	
	private void readOnlyTests(SimpleBooleanList list, List<Boolean> expected) {
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		assertThat(list.toArray(Boolean[]::new)).isEqualTo(expected.toArray(new Boolean[expected.size()]));
		
		assertThat(expected).containsAll(list);
		assertThat(list).containsAll(expected);
		
		assertThat(list.spliterator().characteristics()).isEqualTo(expected.spliterator().characteristics());
		assertThat(Spliterators.iterator(list.spliterator())).containsExactlyElementsOf(()->Spliterators.iterator(expected.spliterator()));
	}
}