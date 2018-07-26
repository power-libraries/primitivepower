package com.github.powerlibraries.primitive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class AbstractIntListTest {

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
				if(r.nextBoolean()) {
					int v = r.nextInt();
					assertThat(list.remove((Integer)v))
						.isEqualTo(expected.remove((Integer)v));
				}
				else {
					int v = r.nextInt();
					assertThat(list.indexOf((Integer)v))
						.isEqualTo(expected.indexOf((Integer)v));
				}
			}
			
			
			assertThat(list.size()).isEqualTo(expected.size());
			assertThat(list.toString()).isEqualTo(expected.toString());
			assertThat(list.toArray()).isEqualTo(expected.toArray());
		}
	}
}