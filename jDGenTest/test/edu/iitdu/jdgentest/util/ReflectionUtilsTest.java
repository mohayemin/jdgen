package edu.iitdu.jdgentest.util;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.iitdu.jdgen.util.ReflectionUtils;
import edu.iitdu.jdgentest.testclasses.Employee;
import edu.iitdu.jdgentest.testclasses.Person;

public class ReflectionUtilsTest {
	private static Class<?> personType;
	private static Class<?> employeeType;

	@BeforeClass
	public static void beforeClass() {
		personType = Person.class;
		employeeType = Employee.class;
	}

	@Test
	public void testGetPrimitiveTypes() {
		Object[] wrapperValues = {
				Integer.MIN_VALUE,
				Byte.MIN_VALUE,
				Short.MIN_VALUE,
				Long.MIN_VALUE,
				Double.NEGATIVE_INFINITY,
				Float.MIN_VALUE,
				new Character('a'),
				Boolean.FALSE
		};

		Class<?>[] primitiveTypes =
			ReflectionUtils.getPrimitiveTypes(wrapperValues);

		assertEquals(int.class, primitiveTypes[0]);
		assertEquals(byte.class, primitiveTypes[1]);
		assertEquals(short.class, primitiveTypes[2]);
		assertEquals(long.class, primitiveTypes[3]);
		assertEquals(double.class, primitiveTypes[4]);
		assertEquals(float.class, primitiveTypes[5]);
		assertEquals(char.class, primitiveTypes[6]);
		assertEquals(boolean.class, primitiveTypes[7]);
	}

	@Test
	public void testGetSettersWithEmployee() {
		List<Method> actual = ReflectionUtils.getSetters(employeeType);

		assertEquals(2, actual.size());
	}

	@Test
	public void testGetSettersWithPerson() {
		List<Method> actual = ReflectionUtils.getSetters(personType);

		assertEquals(1, actual.size());
	}

	@Test
	public void testGetTypes() {
		Object[] objects = { "String", new StringBuilder(), new Integer(5), 5 };
		Integer.valueOf(4);
		Class<?>[] classes = ReflectionUtils.getTypes(objects);
		assertTrue(classes[0].equals(String.class));
		assertTrue(classes[1].equals(StringBuilder.class));
		assertTrue(classes[2].equals(Integer.class));
		assertTrue(classes[3].equals(Integer.class));
	}

	@Test
	public void testGetTypesWithPrimitiveWrapper(){
		Assert.fail("Not impl");
	}

	@Test
	public void testHasParameter() throws NoSuchMethodException,
		SecurityException {
		Method setName = personType.getMethod("setName", String.class);
		Method setButNoParameter = employeeType.getMethod("setButNoParameter");

		assertTrue(ReflectionUtils.hasParameter(setName));
		assertFalse(ReflectionUtils.hasParameter(setButNoParameter));
	}

	@Test
	public void testIsSetter() throws NoSuchMethodException, SecurityException {
		Method setName = personType.getMethod("setName", String.class);
		Method setButNoParameter = employeeType.getMethod("setButNoParameter");

		assertTrue(ReflectionUtils.isSetter(setName));
		assertFalse(ReflectionUtils.isSetter(setButNoParameter));
	}

	@Test
	public void testResolveMethodWithParameterTypeDifference()
		throws NoSuchMethodException, SecurityException {
		List<Method> methods = new ArrayList<Method>();
		methods.add(String.class.getMethod("indexOf", int.class));
		methods.add(String.class.getMethod("indexOf", String.class));
		
		Method resolved = ReflectionUtils.resolveMethodWithParameterTypeDifference(methods, "Hello");
		assertEquals(methods.get(1), resolved);
		
		resolved = ReflectionUtils.resolveMethodWithParameterTypeDifference(methods, 34);
		assertEquals(methods.get(0), resolved);
	}

	@Test
	public void testSearchMethod() throws NoSuchMethodException,
		SecurityException {
		Method actual, expected;

		actual = ReflectionUtils.searchMethod(String.class, "charAt", 3);
		expected = String.class.getMethod("charAt", int.class);
		assertEquals(expected, actual);

		actual = ReflectionUtils.searchMethod(String.class, "indexOf", 'c');
		expected = String.class.getMethod("indexOf", char.class);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testUnbox() {
		Class<?> unboxed =
			ReflectionUtils.unbox(Integer.class);
		assertEquals(int.class, unboxed);
		unboxed = ReflectionUtils.unbox(Byte.class);
		assertEquals(byte.class, unboxed);
		unboxed = ReflectionUtils.unbox(Short.class);
		assertEquals(short.class, unboxed);
		unboxed = ReflectionUtils.unbox(Long.class);
		assertEquals(long.class, unboxed);
		unboxed = ReflectionUtils.unbox(Double.class);
		assertEquals(double.class, unboxed);
		unboxed = ReflectionUtils.unbox(Float.class);
		assertEquals(float.class, unboxed);
		unboxed = ReflectionUtils.unbox(Character.class);
		assertEquals(char.class, unboxed);
		unboxed = ReflectionUtils.unbox(Boolean.class);
		assertEquals(boolean.class, unboxed);

		unboxed = ReflectionUtils.unbox(StringBuilder.class);
		assertEquals(StringBuilder.class, unboxed);
	}
}
