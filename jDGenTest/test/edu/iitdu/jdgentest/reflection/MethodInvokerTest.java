package edu.iitdu.jdgentest.reflection;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.junit.Test;

import edu.iitdu.jdgen.reflection.MethodInvoker;
import edu.iitdu.jdgen.util.MethodUtils;
import edu.iitdu.jdgentest.testclasses.Rectangle;

public class MethodInvokerTest {

	@Test
	public void testConstructor_ClassStringObjects()
		throws NoSuchMethodException,
		SecurityException {

		new MethodInvoker<String>(String.class, "charAt", 4);
		new MethodInvoker<Integer>(Integer.class, "floatValue");
	}

	@Test
	public void testConstructor_MethodObjects() {

		try {
			Method method = String.class.getMethod("charAt", int.class);
			new MethodInvoker<String>(method, 4);

			method = Integer.class.getMethod("floatValue");
			new MethodInvoker<Integer>(method);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testInvoke() throws NoSuchMethodException,
		IllegalAccessException, IllegalArgumentException,
		InvocationTargetException {
		MethodInvoker<String> invoker =
			new MethodInvoker<>(String.class, "charAt", 1);

		char c = (char) invoker.invoke("Hello World");
		
		assertEquals('e', c);
	}

	@Test
	public void testGetSetters() throws NoSuchMethodException{
		Map<String, Method> actualSetters = MethodUtils.getSetters(Rectangle.class);
		
		Method setWidth = Rectangle.class.getMethod("setWidth", Integer.class);
		Method setHeight = Rectangle.class.getMethod("setHeight", Integer.class);
		
		assertEquals(2, actualSetters.size());
		assertTrue(actualSetters.containsKey(setWidth));
		assertTrue(actualSetters.containsKey(setHeight));
		
		fail("no impl");
	}
}
