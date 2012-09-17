package edu.iitdu.jdgentest.builder.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.iitdu.jdgen.builder.abstraction.Buildable;
import edu.iitdu.jdgen.builder.abstraction.ConstrainableBuilder;
import edu.iitdu.jdgen.builder.implementation.ObjectBuilder;
import edu.iitdu.jdgentest.testclasses.Product;
import edu.iitdu.jdgentest.testclasses.Rectangle;

public class ObjectBuilderTest {

	@Test
	public void testBuild_Simple() {
		ObjectBuilder<Product> builder =
			new ObjectBuilder<>(Product.class);

		Product product = builder.build();

		assertEquals(0, product.getProductId());
		assertEquals(false, product.isSold());
	}

	@Test
	public void testBuild_Complex() {
		ConstrainableBuilder<Rectangle> builder =
			new ObjectBuilder<>(Rectangle.class).construct(10, 20).execute(
				"scale", 2.5);

		//System.out.println("**First Build**");
		Rectangle rect = builder.build();
		assertEquals((Integer) 25, rect.getWidth());
		assertEquals((Integer) 50, rect.getHeight());

		//System.out.println("**Second Build**");
		builder =
			new ObjectBuilder<>(Rectangle.class).construct(10)
				.execute("resize", 12, 20).execute("scale", .5)
				.set("width", 30);

		rect = builder.build();
		assertEquals((Integer) 6, rect.getWidth());
		assertEquals((Integer) 10, rect.getHeight());
	}

	@Test
	public void testConstruct_Arguments() {
		Buildable<Product> builder =
			new ObjectBuilder<>(Product.class).construct(5);

		Product product = builder.build();
		assertEquals(5, product.getProductId());
	}

	@Test
	public void testWith_MethodName() {
		ConstrainableBuilder<Product> builder =
			new ObjectBuilder<Product>(Product.class).execute("sell").execute(
				"setProductId", 10);

		Product product = builder.build();

		assertTrue(product.isSold());
		assertEquals(10, product.getProductId());
	}

	@Test
	public void testSet() {
		ConstrainableBuilder<Product> builder =
			new ObjectBuilder<>(Product.class).set("productId", 10);

		Product product = builder.build();

		assertEquals(10, product.getProductId());
	}

	// @Test
	// public void testWith_MethodInvoker() throws NoSuchMethodException,
	// SecurityException {
	// MethodInvoker<Product> setProductIdInvoker =
	// new MethodInvoker<>(Product.class, "setProductId", 10);
	// MethodInvoker<Product> sellInvoker =
	// new MethodInvoker<>(Product.class, "sell");
	// ConstrainableBuilder<Product> builder =
	// new ObjectBuilder<>(Product.class).execute(setProductIdInvoker)
	// .execute(sellInvoker);
	//
	// Product product = builder.build();
	// assertTrue(product.isSold());
	// assertEquals(10, product.getProductId());
	// }

	//
	// @Test
	// public void testConstructor_ConstructorInvoker()
	// throws NoSuchMethodException {
	// ConstructorInvoker<Product> invoker =
	// new ConstructorInvoker<>(Product.class, 5);
	//
	// Buildable<Product> builder =
	// new ObjectBuilder<>(Product.class).construct(invoker);
	//
	// Product product = builder.build();
	//
	// assertEquals(5, product.getProductId());
	// }
}
