package component.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class SingletonTest {
	private boolean field = false;
	private boolean method = false;

	private Singleton s;

	public SingletonTest() {
		s = new Singleton(null);
	}

	@Test
	public void testGetType() {
		assertEquals("Singleton", s.getType());
	}

	@Test
	public void testIsField() {
		assertEquals(field, s.isField());
	}

	@Test
	public void testSetField() {
		s.setField();
		assertEquals(!field, s.isField());
	}

	@Test
	public void testIsMethod() {
		assertEquals(method, s.isMethod());
	}

	@Test
	public void testSetMethod() {
		s.setMethod();
		assertEquals(!method, s.isMethod());
	}

	@Test
	public void testIsSingleton() {
		s.setField();
		s.setMethod();
		assertEquals(s.isSingleton(), true);
	}
}
