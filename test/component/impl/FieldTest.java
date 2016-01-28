package component.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FieldTest {

	private final Field f;

	private final int access;
	private final String name;
	private final String desc;
	private final String signature;
	private final Object value;

	public FieldTest() {
		access = 1;
		name = "myField";
		desc = "this is a test";
		signature = "Chi Zhang";
		value = new Object();
		this.f = new Field(access, name, desc, signature, value, null);
	}

	@Test
	public void testGetAccess() {
		assertEquals(this.access, this.f.getAccess());
	}

	@Test
	public void testGetDescription() {
		assertEquals(this.desc, this.f.getDescription());
	}

	@Test
	public void testGetName() {
		assertEquals(this.name, this.f.getName());
	}

	@Test
	public void testGetSignature() {
		assertEquals(this.signature, this.f.getSignature());
	}

	@Test
	public void testGetValue() {
		assertEquals(this.value, this.f.getValue());
	}

}
