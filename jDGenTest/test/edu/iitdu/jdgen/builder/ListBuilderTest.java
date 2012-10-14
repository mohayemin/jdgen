package edu.iitdu.jdgen.builder;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.iitdu.jdgen.builder.ListBuilder;
import edu.iitdu.jdgen.configuration.Configurable;
import edu.iitdu.jdgen.configuration.Configuration;
import edu.iitdu.jdgen.testclasses.Rectangle;

public class ListBuilderTest {

	@Test
	public void testBuild() {
		Configurable<Rectangle> configuration =
			new Configuration<>(Rectangle.class).construct(10)
				.execute("resize", 20, 30).set("width", 25);

		ListBuilder<Rectangle> builder =
			new ListBuilder<>(configuration, 10);

		Rectangle expected = new Rectangle(20, 30);

		for (Rectangle actual : builder.build()) {
			assertEquals(expected, actual);
		}
	}

	@Test
	public void testSize() {
		ListBuilder<Rectangle> builder =
			new ListBuilder<>(new Configuration<>(Rectangle.class), 10);
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
