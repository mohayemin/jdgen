package edu.iitdu.jdgentest.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

import org.junit.Test;

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
		MethodUtils.findBestMatch(Ginipig.class, "mixedParameters", int.class,
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
		MethodUtils.findBestMatch(Ginipig.class, "primitiveParameters",
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
		MethodUtils.findBestMatch(Ginipig.class, "wrapperParameters", int.class,
			Integer.class);
	}
	

	@Test
	public void testHasParameter() throws NoSuchMethodException,
		SecurityException {
		Method twoParameters = Ginipig.class.getMethod("mixedParameters", int.class, Integer.class);
		Method noParameter = Ginipig.class.getMethod("noParameter");

		assertTrue(MethodUtils.hasParameter(twoParameters));
		assertFalse(MethodUtils.hasParameter(noParameter));
	}

	@Test
	public void testIsSetter() throws NoSuchMethodException, SecurityException {
		Method setName = Ginipig.class.getMethod("setName", String.class);
		Method setButNoParameter = Ginipig.class.getMethod("setButNoParameter");

		assertTrue(MethodUtils.isSetter(setName));
		assertFalse(MethodUtils.isSetter(setButNoParameter));
	}
	
	

	static interface Ginipig {
		public void mixedParameters(int i1, Integer i2);

		public void primitiveParameters(int i1, int i2);

		public void wrapperParameters(Integer i1, Integer i2);
		
		public void noParameter();
		
		public void setName(String name);
		
		public void setButNoParameter();
	}
}
