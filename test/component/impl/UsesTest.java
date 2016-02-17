package component.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UsesTest {

	private String className;
	private String referenceName;

	Uses u;

	public UsesTest() {
		className = "CSSE374";
		referenceName = "referenceName";

		u = new Uses(className, referenceName);
	}

	@Test
	public void testGetType() {
		assertEquals("Uses", u.getType());
	}

	@Test
	public void testGetClassName() {
		assertEquals(className, u.getClassName());
	}

	@Test
	public void testGetReferenceName() {
		assertEquals(referenceName, u.getReferenceName());
	}

	@Test
	public void testGetInvoker() {
		assertEquals(className, u.getClassName());
	}
}
