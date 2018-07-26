package com.github.powerlibraries.primitive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class AbstractCharListTest {

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
				if(r.nextBoolean()) {
					char v = ((char)r.nextInt(100));
					assertThat(list.remove((Character)v))
						.isEqualTo(expected.remove((Character)v));
				}
				else {
					char v = ((char)r.nextInt(100));
					assertThat(list.indexOf((Character)v))
						.isEqualTo(expected.indexOf((Character)v));
				}
			}
			
			
			assertThat(list.size()).isEqualTo(expected.size());
			assertThat(list.toString()).isEqualTo(expected.toString());
			assertThat(list.toArray()).isEqualTo(expected.toArray());
		}
	}
}