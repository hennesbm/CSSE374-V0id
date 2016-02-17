package component.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class DecoratorTest {

	private String decorates;
	private String className;
	private String component;

	Decorator d1;
	Decorator d2;

	public DecoratorTest() {
		decorates = "decorates";
		className = "className";
		component = "component";

		d1 = new Decorator(className, component);
		d2 = new Decorator(className, decorates, component);
	}

	@Test
	public void testGetType() {
		assertEquals("Decorator", d1.getType());
		assertEquals("Decorator", d2.getType());
	}

	@Test
	public void testGetDecorates() {
		assertEquals(null, d1.getDecorates());
		assertEquals(decorates, d2.getDecorates());
	}

	@Test
	public void testGetComponent() {
		assertEquals(component, d1.getComponent());
		assertEquals(component, d2.getComponent());
	}

	@Test
	public void testGetColor() {
		assertEquals("Green", d1.getColor());
		assertEquals("Green", d2.getColor());
	}

	@Test
	public void testGetClassName() {
		assertEquals(className, d1.getClassName());
		assertEquals(className, d2.getClassName());
	}

	@Test
	public void testGetAccepter() {
		assertEquals(null, d1.getAccepter());
		assertEquals(decorates, d2.getAccepter());
	}

	@Test
	public void testGetInvoker() {
		assertEquals(className, d1.getInvoker());
		assertEquals(className, d2.getInvoker());
	}

}
