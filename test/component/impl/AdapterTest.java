package component.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdapterTest {

	private String className;
	private String component;
	private String adaptee;

	Adapter a;

	public AdapterTest() {
		className = "CSSE374";
		component = "code";
		adaptee = "CSSE120";
		this.a = new Adapter(className, component, adaptee, null);
	}

	@Test
	public void testGetType() {
		assertEquals("Adapter", a.getType());
	}

	@Test
	public void testGetComponent() {
		assertEquals(component, a.getComponent());
	}

	@Test
	public void testGetColor() {
		assertEquals("red", a.getColor());
	}

	@Test
	public void testGetClassName() {
		assertEquals(className, a.getClassName());
	}

	@Test
	public void testGetAdaptee() {
		assertEquals(adaptee, a.getAdaptee());
	}
}
