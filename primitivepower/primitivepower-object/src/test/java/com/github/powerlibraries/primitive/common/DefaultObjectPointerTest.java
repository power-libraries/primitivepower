package com.github.powerlibraries.primitive.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class DefaultObjectPointerTest<E> {

	@Test
	public void test() {
		DefaultObjectPointer<E> pointer = new DefaultObjectPointer<E>();
		
		Random r = new Random(7);
		
		for(int i=0; i<100; i++) {
			E v = (E)TimeUnit.values()[r.nextInt(7)];
			pointer.set(v);
			assertThat(pointer.get()).isEqualTo(v);
		}
	}
}