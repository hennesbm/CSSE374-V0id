package component.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class StatementTest {

	private Statement s;

	private int opcode;
	private String owner;
	private String name;
	private String desc;
	private boolean inter;
	private String className;
	private String methodName;

	public StatementTest() {

		opcode = 1;
		desc = "myDeclaration";
		owner = "Chi Zhang";
		name = "MYDECLARATION";
		inter = true;
		className = null;
		methodName = null;

		s = new Statement(opcode, owner, name, desc, true, className, methodName);
	}

	@Test
	public void testGetType() {
		assertEquals("Statement", s.getType());
	}

	@Test
	public void testGetSignature() {
		assertEquals(null, s.getSignature());
	}

	@Test
	public void testGetOwner() {
		assertEquals(owner, s.getOwner());
	}

	@Test
	public void testGetName() {
		assertEquals(name, s.getName());
	}

	@Test
	public void testGetDescription() {
		assertEquals(desc, s.getDescription());
	}

	@Test
	public void testifInterface() {
		assertEquals(inter, s.ifInterface());
	}
}
