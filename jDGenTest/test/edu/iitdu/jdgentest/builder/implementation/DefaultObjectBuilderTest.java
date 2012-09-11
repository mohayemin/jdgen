package edu.iitdu.jdgentest.builder.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.iitdu.jdgen.builder.abstraction.Buildable;
import edu.iitdu.jdgen.builder.abstraction.ConstrainableBuilder;
import edu.iitdu.jdgen.builder.implementation.DefaultObjectBuilder;
import edu.iitdu.jdgen.reflection.ConstructorInvoker;
import edu.iitdu.jdgen.reflection.MethodInvoker;
import edu.iitdu.jdgentest.testclasses.Product;

public class DefaultObjectBuilderTest {

	@Test
	public void testBuild() {
		DefaultObjectBuilder<Product> builder =
			new DefaultObjectBuilder<>(Product.class);

		Product product = builder.build();

		assertEquals(0, product.getProductId());
		assertEquals(false, product.isSold());
	}

	@Test
	public void testWithConstructor_Arguments() {
		Buildable<Product> builder =
			new DefaultObjectBuilder<>(Product.class).withConstructor(5);

		Product product = builder.build();
		assertEquals(5, product.getProductId());
	}

	@Test
	public void testWithConstructor_ConstructorInvoker()
		throws NoSuchMethodException {
		ConstructorInvoker<Product> invoker =
			new ConstructorInvoker<>(Product.class, 5);

		Buildable<Product> builder =
			new DefaultObjectBuilder<>(Product.class).withConstructor(invoker);

		Product product = builder.build();

		assertEquals(5, product.getProductId());
	}

	@Test
	public void testWith_MethodInvoker() throws NoSuchMethodException,
		SecurityException {
		MethodInvoker<Product> setProductIdInvoker =
			new MethodInvoker<>(Product.class, "setProductId", 10);
		MethodInvoker<Product> sellInvoker =
			new MethodInvoker<>(Product.class, "sell");
		ConstrainableBuilder<Product> builder =
			new DefaultObjectBuilder<>(Product.class).with(setProductIdInvoker)
				.with(sellInvoker);

		Product product = builder.build();
		assertTrue(product.isSold());
		assertEquals(10, product.getProductId());
	}

	@Test
	public void testWith_MethodName() {
		ConstrainableBuilder<Product> builder =
			new DefaultObjectBuilder<Product>(Product.class).with("sell").with(
				"setProductId", 10);

		Product product = builder.build();

		assertTrue(product.isSold());
		assertEquals(10, product.getProductId());
	}

}
