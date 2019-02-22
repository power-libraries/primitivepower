package com.github.powerlibraries.primitive.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class DefaultIntPointerTest {

	@Test
	public void test() {
		DefaultIntPointer pointer = new DefaultIntPointer();
		
		Random r = new Random(7);
		
		for(int i=0; i<100; i++) {
			int v = ((int)r.nextInt(9));
			pointer.set(v);
			assertThat(pointer.get()).isEqualTo(v);
		}
	}
}