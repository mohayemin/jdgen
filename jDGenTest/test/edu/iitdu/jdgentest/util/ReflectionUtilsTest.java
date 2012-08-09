package edu.iitdu.jdgentest.util;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.List;

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
	public void testGetSettersWithPerson() {
		List<Method> actual = ReflectionUtils.getSetters(personType);

		assertEquals(1, actual.size());
	}

	@Test
	public void testGetSettersWithEmployee() {
		List<Method> actual = ReflectionUtils.getSetters(employeeType);
		
		assertEquals(2, actual.size());
	}

	@Test
	public void testIsSetter() throws NoSuchMethodException, SecurityException {
		Method setName = personType.getMethod("setName", String.class);
		Method setButNoParameter = employeeType.getMethod("setButNoParameter");

		assertTrue(ReflectionUtils.isSetter(setName));
		assertFalse(ReflectionUtils.isSetter(setButNoParameter));
	}

	@Test
	public void testHasParameter() throws NoSuchMethodException,
		SecurityException {
		Method setName = personType.getMethod("setName", String.class);
		Method setButNoParameter = employeeType.getMethod("setButNoParameter");

		assertTrue(ReflectionUtils.hasParameter(setName));
		assertFalse(ReflectionUtils.hasParameter(setButNoParameter));
	}
}
