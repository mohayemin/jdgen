package edu.iitdu.jdgen.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iitdu.jdgen.util.WrapperUtils;

public class WrapperUtilsTest {
	static Class<?>[] wrapperTypes = { Integer.class, Byte.class, Short.class,
			Long.class, Double.class, Float.class, Character.class,
			Boolean.class };

	static Class<?>[] primitiveTypes = { int.class, byte.class, short.class,
			long.class, double.class, float.class, char.class,
			boolean.class };

	@Test
	public void testToPrimitive_Single() {
		for (int i = 0; i < wrapperTypes.length; i++) {
			Class<?> unboxed = WrapperUtils.toPrimitive(wrapperTypes[i]);
			assertEquals(primitiveTypes[i], unboxed);
		}
		
		Class<?> unboxed = WrapperUtils.toPrimitive(StringBuilder.class);
		assertEquals(StringBuilder.class, unboxed);
	}

	@Test
	public void testToWrapper() {
		for (int i = 0; i < primitiveTypes.length; i++) {
			Class<?> boxed = WrapperUtils.toWrapper(primitiveTypes[i]);
			assertEquals(wrapperTypes[i], boxed);
		}
		
		Class<?> boxed = WrapperUtils.toWrapper(StringBuilder.class);
		assertEquals(StringBuilder.class, boxed);
	}

	@Test
	public void testToPrimitiveArray() {
		Class<?> types[] =
		{ String.class, Integer.class, Character.class, byte.class };

		Class<?> primitives[] = WrapperUtils.toPrimitiveArray(types);

		assertEquals(primitives[0], String.class);
		assertEquals(primitives[1], int.class);
		assertEquals(primitives[2], char.class);
		assertEquals(primitives[3], byte.class);
	}


	@Test
	public void testToWrapperArray() {
		Class<?> types[] =
		{ String.class, int.class, char.class, Byte.class };

		Class<?> primitives[] = WrapperUtils.toWrapperArray(types);

		assertEquals(primitives[0], String.class);
		assertEquals(primitives[1], Integer.class);
		assertEquals(primitives[2], Character.class);
		assertEquals(primitives[3], Byte.class);
	}
}
