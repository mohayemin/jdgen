package edu.iitdu.jdgen.reflection;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class DefaultValueProviderTest {

	private static DefaultValueProvider defaultValueProvider;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		defaultValueProvider = new DefaultValueProvider();
	}

	@Test
	public void getValue_Boolean() {
		Boolean actual = defaultValueProvider.getValueFor(Boolean.class);

		assertEquals(false, actual);
	}

	@Test
	public void getValue_Byte() {
		Byte actual = defaultValueProvider.getValueFor(Byte.class);

		assertEquals(new Byte((byte) 0), actual);
	}

	@Test
	public void getValue_Charecter() {
		Character actual = defaultValueProvider.getValueFor(Character.class);

		assertEquals(new Character('A'), actual);
	}

	@Test
	public void getValue_Double() {
		Double actual = defaultValueProvider.getValueFor(Double.class);

		assertEquals(new Double(0.0), actual);
	}

	@Test
	public void getValue_Float() {
		Float actual = defaultValueProvider.getValueFor(Float.class);

		assertEquals(new Float(0.0), actual);
	}

	@Test
	public void getValue_Integer() {
		Integer actual = defaultValueProvider.getValueFor(Integer.class);

		assertEquals(new Integer(0), actual);
	}

	@Test
	public void getValue_Long() {
		Long actual = defaultValueProvider.getValueFor(Long.class);

		assertEquals(new Long(0), actual);
	}

	@Test
	public void getValue_Short() {
		Short actual = defaultValueProvider.getValueFor(Short.class);

		assertEquals(new Short((short) 0), actual);
	}
}
