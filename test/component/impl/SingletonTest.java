package component.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class SingletonTest {
	private Singleton s;

	public SingletonTest() {
		s = new Singleton("Test");
	}
	
	@Test
	public void testGetClassName() {
		assertEquals("Test", s.getClassName());
	}

	@Test
	public void testGetType() {
		assertEquals("Singleton", s.getType());
	}
}
