package edu.iitdu.jdgen.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.iitdu.jdgen.dummy.Primitive;

public class BuilderTest {
	@Test
	public void buildDefault() throws InstantiationException, IllegalAccessException {
		Builder<Primitive> primitiveBuilder = new Builder<Primitive>(Primitive.class);
		Primitive actual = primitiveBuilder.Build();

		assertEquals(0, (byte)actual.getB());
		assertEquals(false, actual.getBool());
		assertEquals('A', (char) actual.getC());
		assertEquals(0.0, actual.getD(), 1e-8);
		assertEquals(0.0f, actual.getF(), 1e-8);
		assertEquals(0, (int) actual.getI());
		assertEquals(0L, (long) actual.getL());
		assertEquals(0, (short)actual.getS());
	}
}
