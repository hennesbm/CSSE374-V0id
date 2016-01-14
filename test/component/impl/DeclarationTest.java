package component.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeclarationTest {

	private final Declaration d;

	private final int version;
	private final int access;
	private final String name;
	private final String signature;
	private final String superName;
	private final String[] interfaces;

	public DeclarationTest() {
		version = 5;
		access = 1;
		name = "myDeclaration";
		signature = "Chi Zhang";
		superName = "MYDECLARATION";
		interfaces = new String[100];
		this.d = new Declaration(version, access, name, signature, superName, interfaces);
	}

	@Test
	public void testGetVersion() {
		assertEquals(this.version, this.d.getVersion());
	}

	@Test
	public void testGetAccess() {
		assertEquals(this.access, this.d.getAccess());
	}

	@Test
	public void testGetName() {
		assertEquals(this.name, this.d.getName());
	}

	@Test
	public void testGetSignature() {
		assertEquals(this.signature, this.d.getSignature());
	}

	@Test
	public void testGetSuperClass() {
		assertEquals(this.superName, this.d.getSuperClass());
	}

}