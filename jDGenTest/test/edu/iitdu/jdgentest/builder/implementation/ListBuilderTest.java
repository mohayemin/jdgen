package edu.iitdu.jdgentest.builder.implementation;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.iitdu.jdgen.builder.implementation.ListBuilder;
import edu.iitdu.jdgentest.testclasses.Rectangle;

public class ListBuilderTest {

	@Test
	public void testBuild() {
		ListBuilder<Rectangle> builder =
			new ListBuilder<>(Rectangle.class, 10).construct(10)
				.execute("resize", 20, 30).set("width", 25);
		
		Rectangle expected = new Rectangle(20, 30);
		
		for (Rectangle actual : builder.build()) {
			assertEquals(expected, actual);
		}
	}

	@Test
	public void testSize() {
		ListBuilder<Rectangle> builder = new ListBuilder<>(Rectangle.class, 10);
		List<Rectangle> rectangles = builder.build();

		assertEquals(10, rectangles.size());
	}

	@Test
	public void testExecute() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testConstruct() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSet() {

		fail("Not yet implemented"); // TODO
	}

}
