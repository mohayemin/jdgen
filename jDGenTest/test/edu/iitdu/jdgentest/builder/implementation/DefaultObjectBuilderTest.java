package edu.iitdu.jdgentest.builder.implementation;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import edu.iitdu.jdgen.builder.abstraction.ConstrainableBuilder;
import edu.iitdu.jdgen.builder.implementation.DefaultObjectBuilder;
import edu.iitdu.jdgentest.testclasses.Product;

public class DefaultObjectBuilderTest {

	@Test
	public void testBuild() {
		fail("Not yet implemented");
	}

	@Test
	public void testWithConstructorConstructorOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testWithMethod() {
		fail("Not yet implemented");
	}

	@Test
	public void testWithStringClassOfQArray() {
		ConstrainableBuilder<Product> builder =
			new DefaultObjectBuilder<Product>(Product.class).with("sell");

		Product actual = builder.build();
		
		Assert.assertTrue(actual.isSold());
	}

	@Test
	public void testWithConstructorClassOfQArray() {
		fail("Not yet implemented");
	}

}
