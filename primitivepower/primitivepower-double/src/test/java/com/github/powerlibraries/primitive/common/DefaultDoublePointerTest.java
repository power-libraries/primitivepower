package com.github.powerlibraries.primitive.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class DefaultDoublePointerTest {

	@Test
	public void test() {
		DefaultDoublePointer pointer = new DefaultDoublePointer();
		
		Random r = new Random(7);
		
		for(int i=0; i<100; i++) {
			double v = ((double)r.nextInt(9));
			pointer.set(v);
			assertThat(pointer.get()).isEqualTo(v);
		}
	}
}