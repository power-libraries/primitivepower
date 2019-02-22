package com.github.powerlibraries.primitive.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class DefaultBooleanPointerTest {

	@Test
	public void test() {
		DefaultBooleanPointer pointer = new DefaultBooleanPointer();
		
		Random r = new Random(7);
		
		for(int i=0; i<100; i++) {
			boolean v = r.nextBoolean();
			pointer.set(v);
			assertThat(pointer.get()).isEqualTo(v);
		}
	}
}