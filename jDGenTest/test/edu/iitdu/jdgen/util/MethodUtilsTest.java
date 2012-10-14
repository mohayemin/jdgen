package edu.iitdu.jdgen.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

import org.junit.Test;

import edu.iitdu.jdgen.testclasses.MethodUtilTestGinipig;
import edu.iitdu.jdgen.util.MethodUtils;

public class MethodUtilsTest {

	/**
	 * Test whether the method is found by exact match of parameter types
	 * Should through exception on failure
	 * 
	 * @throws NoSuchMethodException
	 */
	@Test
	public void testFindBestMatch_Exactly() throws NoSuchMethodException {
		MethodUtils.findBestMatch(MethodUtilTestGinipig.class,
			"mixedParameters", int.class,
			Integer.class);
	}

	/**
	 * Test whether the method is found by converting parameter types to
	 * primitive
	 * Should through exception on failure.
	 * 
	 * @throws NoSuchMethodException
	 */
	@Test
	public void testFindBestMatch_ToPrimitive() throws NoSuchMethodException {
		MethodUtils.findBestMatch(MethodUtilTestGinipig.class,
			"primitiveParameters",
			int.class,
			Integer.class);
	}

	/**
	 * Test whether the method is found by converting parameter types to wrapper
	 * Should through exception on failure.
	 * 
	 * @throws NoSuchMethodException
	 */
	@Test
	public void testFindBestMatch_ToWrapper() throws NoSuchMethodException {
		MethodUtils.findBestMatch(MethodUtilTestGinipig.class,
			"wrapperParameters",
			int.class,
			Integer.class);
	}

	@Test
	public void testHasParameter() throws NoSuchMethodException,
		SecurityException {
		Method twoParameters =
			MethodUtilTestGinipig.class
				.getMethod("mixedParameters", int.class, Integer.class);
		Method noParameter =
			MethodUtilTestGinipig.class.getMethod("noParameter");

		assertTrue(MethodUtils.hasParameter(twoParameters));
		assertFalse(MethodUtils.hasParameter(noParameter));
	}

	@Test
	public void testIsSetter() throws NoSuchMethodException {
		Method setName =
			MethodUtilTestGinipig.class.getMethod("setName", String.class);
		Method setButNoParameter =
			MethodUtilTestGinipig.class.getMethod("setButNoParameter");

		assertTrue(MethodUtils.isSetter(setName));
		assertFalse(MethodUtils.isSetter(setButNoParameter));

		Method settings =
			MethodUtilTestGinipig.class.getMethod("settings", int.class);
		assertFalse(MethodUtils.isSetter(settings));
	}

	@Test
	public void testFindSetter() throws NoSuchMethodException {
		Method expectedSetName =
			MethodUtilTestGinipig.class.getMethod("setName", String.class);
		Method actualSetName =
			MethodUtils.findSetter(MethodUtilTestGinipig.class, "name");

		assertEquals(expectedSetName, actualSetName);
	}

	@Test(expected = NoSuchMethodException.class)
	public void testFindSetter_Fail() throws NoSuchMethodException {
		MethodUtils.findSetter(MethodUtilTestGinipig.class, "tings");
	}
}
