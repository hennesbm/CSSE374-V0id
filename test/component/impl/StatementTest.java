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

	public StatementTest() {

		opcode = 1;
		desc = "myDeclaration";
		owner = "Chi Zhang";
		name = "MYDECLARATION";
		inter = true;

		s = new Statement(opcode, owner, name, desc, true);
	}

	@Test
	public void testGetType() {
		assertEquals("Statements", s.getType());
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
