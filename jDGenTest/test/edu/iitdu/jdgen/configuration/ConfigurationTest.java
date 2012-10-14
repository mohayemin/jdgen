package edu.iitdu.jdgen.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.iitdu.jdgen.reflection.ConstructorInvoker;
import edu.iitdu.jdgen.testclasses.Rectangle;

public class ConfigurationTest {
	private Configuration<Rectangle> config;
	
	@Before
	public void setUp(){
		config = new Configuration<>(Rectangle.class);
	}
	
	@Test
	public void testConfiguration() {
		
		List<?> methods = config.getMethods();
		List<?> setters = config.getSetters();
		
		assertNotNull(methods);
		assertNotNull(setters);
		
		assertEquals(0, methods.size());
		assertEquals(0, setters.size());
	}

	@Test
	public void testConstruct() throws NoSuchMethodException {
		ConstructorInvoker<Rectangle> constructor = new ConstructorInvoker<>(Rectangle.class);
		assertEquals(constructor, config.getConstructor());
		
		constructor = new ConstructorInvoker<>(Rectangle.class, 2);
		config.construct(2);
		assertEquals(constructor, config.getConstructor());
	}

	@Test
	public void testExecute() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSet() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetMethods() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetConstructor() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetSetters() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetType() {
		fail("Not yet implemented"); // TODO
	}

}
