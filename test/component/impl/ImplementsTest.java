package component.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ImplementsTest {
	private String className;
	private String referenceName;

	Implements i;

	public ImplementsTest() {
		className = "CSSE374";
		referenceName = "referenceName";

		i = new Implements(className, referenceName);
	}

	@Test
	public void testGetType() {
		assertEquals("Implements", i.getType());
	}

	@Test
	public void testGetClassName() {
		assertEquals(className, i.getClassName());
	}

	@Test
	public void testGetReferenceName() {
		assertEquals(referenceName, i.getReferenceName());
	}

	@Test
	public void testGetInvoker() {
		assertEquals(className, i.getClassName());
	}
}
