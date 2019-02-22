package com.github.powerlibraries.primitive.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class DefaultShortPointerTest {

	@Test
	public void test() {
		DefaultShortPointer pointer = new DefaultShortPointer();
		
		Random r = new Random(7);
		
		for(int i=0; i<100; i++) {
			short v = ((short)r.nextInt(9));
			pointer.set(v);
			assertThat(pointer.get()).isEqualTo(v);
		}
	}
}