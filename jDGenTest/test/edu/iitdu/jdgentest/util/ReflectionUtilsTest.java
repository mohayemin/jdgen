package edu.iitdu.jdgentest.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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


}
