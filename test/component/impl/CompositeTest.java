package component.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class CompositeTest {

	private String componentclassname;
	private String className1;
	private String className2;
	private String component;

	Composite c1;
	Composite c2;

	public CompositeTest() {
		componentclassname = "CSSE";
		className1 = "CSSE374-01";
		className2 = "CSSE374-02";
		component = "374";

		c1 = new Composite(className1, component);
		c2 = new Composite(className2, componentclassname, component);
	}

	@Test
	public void testGetType() {
		assertEquals("Composite", c1.getType());
		assertEquals("Composite", c2.getType());
	}

	@Test
	public void testGetComponent() {
		assertEquals(component, c1.getComponent());
		assertEquals(component, c2.getComponent());
	}

	@Test
	public void testGetColor() {
		assertEquals("Yellow", c1.getColor());
		assertEquals("Yellow", c2.getColor());
	}

	@Test
	public void testGetClassName() {
		assertEquals(className1, c1.getClassName());
		assertEquals(className2, c2.getClassName());
	}

	@Test
	public void testGetComponentClassName() {
		assertEquals(componentclassname, c2.getComponentClassName());
		assertEquals(null, c1.getComponentClassName());
	}
}
