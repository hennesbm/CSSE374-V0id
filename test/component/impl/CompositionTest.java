package component.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class CompositionTest {

	private String className;
	private String referenceName;

	Composition c;

	public CompositionTest() {
		className = "CSSE371";
		referenceName = "JuniorDesign";

		c = new Composition(className, referenceName);
	}

	@Test
	public void testGetType() {
		assertEquals("Composition", c.getType());
	}

	@Test
	public void testGetClassName() {
		assertEquals(className, c.getClassName());
	}

	@Test
	public void testGetReferencename() {
		assertEquals(referenceName, c.getReferenceName());
	}

	@Test
	public void testGetInvoker() {
		assertEquals(className, c.getClassName());
	}

}
