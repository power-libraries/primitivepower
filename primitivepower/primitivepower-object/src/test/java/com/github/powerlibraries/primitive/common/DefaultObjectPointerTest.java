package com.github.powerlibraries.primitive.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class DefaultObjectPointerTest {

	@Test
	public void test() {
		DefaultObjectPointer pointer = new DefaultObjectPointer();
		
		Random r = new Random(7);
		
		for(int i=0; i<100; i++) {
			E v = (E)TimeUnit.values()[r.nextInt(7)];
			pointer.set(v);
			assertThat(pointer.get()).isEqualTo(v);
		}
	}
}