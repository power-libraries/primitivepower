package com.github.powerlibraries.primitive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class AbstractByteListTest {

	@Test
	public void randomTest() {
		Random r = new Random(7);
		List<Byte> expected = new ArrayList<>();
		SimpleByteList list = new SimpleByteList();
		
		for(int i=0; i<10000; i++) {
			//adding a value
			if(r.nextFloat()<0.7) {
				byte v = r.nextByte();
				assertThat(list.add(v))
						.isEqualTo(expected.add(v));
			}
			else {
				if(r.nextBoolean()) {
					byte v = r.nextByte();
					assertThat(list.remove((Byte)v))
						.isEqualTo(expected.remove((Byte)v));
				}
			}
			
			
			assertThat(list.size()).isEqualTo(expected.size());
			assertThat(list.toString()).isEqualTo(expected.toString());
			assertThat(list.toArray()).isEqualTo(expected.toArray());
		}
	}
}