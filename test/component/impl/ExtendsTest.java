package component.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExtendsTest {
	private String className;
	private String referenceName;

	Extends e;

	public ExtendsTest() {
		className = "CSSE374";
		referenceName = "referenceName";

		e = new Extends(className, referenceName);
	}

	@Test
	public void testGetType() {
		assertEquals("Extends", e.getType());
	}

	@Test
	public void testGetClassName() {
		assertEquals(className, e.getClassName());
	}

	@Test
	public void testGetReferenceName() {
		assertEquals(referenceName, e.getReferenceName());
	}

	@Test
	public void testGetInvoker() {
		assertEquals(className, e.getClassName());
	}
}
