package component.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MethodTest {

	private Method m;

	private int access;
	private String name;
	private String description;
	private String signature;
	private String[] exceptions;

	public MethodTest() {
		this.access = 1;
		this.name = "myMethod";
		this.description = "this is a test";
		this.signature = "Chi Zhang";
		this.exceptions = new String[100];

		m = new Method(access, name, description, signature, exceptions);
	}

	@Test
	public void testGetAccess() {
		assertEquals(this.access, this.m.getAccess());
	}

	@Test
	public void testGetName() {
		assertEquals(this.name, this.m.getName());
	}
	
	@Test
	public void testGetDescription() {
		assertEquals(this.description, this.m.getDescription());
	}

	@Test
	public void testGetSignature() {
		assertEquals(this.signature, this.m.getSignature());
	}
	
	@Test
	public void testGetExceptions() {
		assertEquals(this.exceptions, this.m.getExceptions());
	}
	
}
