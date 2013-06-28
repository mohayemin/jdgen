package edu.iitdu.jdgen.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.iitdu.jdgen.dummy.Circle;
import edu.iitdu.jdgen.dummy.Primitive;

public class ObjectBuilderTest {
	@Test
	public void buildDefault_Primitive() {
		IObjectBuilder<Primitive> primitiveBuilder = new ObjectBuilder<Primitive>(Primitive.class);
		Primitive actual = primitiveBuilder.buildObject();

		assertEquals(0, (byte)actual.getB());
		assertEquals(false, actual.getBool());
		assertEquals('A', (char) actual.getC());
		assertEquals(0.0, actual.getD(), 1e-8);
		assertEquals(0.0f, actual.getF(), 1e-8);
		assertEquals(0, (int) actual.getI());
		assertEquals(0L, (long) actual.getL());
		assertEquals(0, (short)actual.getS());
	}
	
	@Test
	public void buildDefault_NonPrimitive() {
		IObjectBuilder<Circle> nonPrimitiveBuilder = new ObjectBuilder<Circle>(Circle.class);
		Circle actual = nonPrimitiveBuilder.buildObject();
		
		assertEquals(0, (int)actual.getRadius());
		assertEquals(0, (int)actual.getLeftTop().getX());
		assertEquals(0, (int)actual.getLeftTop().getY());
	}
}
