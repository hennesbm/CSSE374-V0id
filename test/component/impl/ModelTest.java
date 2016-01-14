package component.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import component.api.IDeclaration;

public class ModelTest {

	private IDeclaration currentClass;
//	private ArrayList<IDeclaration> classList = new ArrayList<IDeclaration>();

	private Model m;

	public ModelTest() {
		m = new Model();
		int version = 5;
		int access = 1;
		String name = "myDeclaration";
		String signature = "Chi Zhang";
		String superName = "MYDECLARATION";
		String interfaces[] = new String[100];
		currentClass = new Declaration(version, access, name, signature, superName, interfaces);

	}

	@Test
	public void testSetGetCurrentClass() {
		m.setCurrentClass(currentClass);
		assertEquals(currentClass, m.getCurrentClass());
	}

}
