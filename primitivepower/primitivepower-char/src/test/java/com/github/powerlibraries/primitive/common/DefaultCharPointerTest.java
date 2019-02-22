package com.github.powerlibraries.primitive.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class DefaultCharPointerTest {

	@Test
	public void test() {
		DefaultCharPointer pointer = new DefaultCharPointer();
		
		Random r = new Random(7);
		
		for(int i=0; i<100; i++) {
			char v = ((char)r.nextInt(9));
			pointer.set(v);
			assertThat(pointer.get()).isEqualTo(v);
		}
	}
}