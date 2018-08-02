package com.github.powerlibraries.primitive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Spliterators;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class AbstractCharListTest {

	@Test
	public void guardConditionsTest() {
		SimpleCharList list = new SimpleCharList();
		
		assertThat(list.contains(null)).isFalse();
	}

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Character> expected = new ArrayList<>();
		SimpleCharList list = new SimpleCharList();
		
		for(int i=0; i<2000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				char v = ((char)r.nextInt(100));
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				char v;
				switch(r.nextInt(3)) {
					case 0:
						v = ((char)r.nextInt(100));
						assertThat(list.remove((Character)v))
							.isEqualTo(expected.remove((Character)v));
						break;
					case 1:
						v = ((char)r.nextInt(100));
						assertThat(list.indexOf((Character)v))
							.isEqualTo(expected.indexOf((Character)v));
						break;
					case 2:
						v = ((char)r.nextInt(100));
						assertThat(list.contains((Character)v))
							.isEqualTo(expected.contains((Character)v));
						break;
				}
			}
			
			readOnlyTests(list, expected);
		}
		
		//replace all elements with '\u0000'
		list.replaceAll(v -> '\u0000');
		expected.replaceAll(v -> '\u0000');
		readOnlyTests(list, expected);
	}
	
	private void readOnlyTests(SimpleCharList list, List<Character> expected) {
		assertThat(list.size()).isEqualTo(expected.size());
		assertThat(list.toString()).isEqualTo(expected.toString());
		
		assertThat(list.toArray()).isEqualTo(expected.toArray());
		assertThat(list.toArray(Character[]::new)).isEqualTo(expected.toArray(new Character[expected.size()]));
		
		assertThat(expected).containsAll(list);
		assertThat(list).containsAll(expected);
		
		assertThat(list.spliterator().characteristics()).isEqualTo(expected.spliterator().characteristics());
		assertThat(Spliterators.iterator(list.spliterator())).containsExactlyElementsOf(()->Spliterators.iterator(expected.spliterator()));
	}
}