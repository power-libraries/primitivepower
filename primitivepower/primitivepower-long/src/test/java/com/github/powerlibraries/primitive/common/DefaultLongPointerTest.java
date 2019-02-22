package com.github.powerlibraries.primitive.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class DefaultLongPointerTest {

	@Test
	public void test() {
		DefaultLongPointer pointer = new DefaultLongPointer();
		
		Random r = new Random(7);
		
		for(int i=0; i<100; i++) {
			long v = ((long)r.nextInt(9));
			pointer.set(v);
			assertThat(pointer.get()).isEqualTo(v);
		}
	}
}