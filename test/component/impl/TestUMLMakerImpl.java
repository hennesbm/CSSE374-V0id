import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import problem.asm.DesignParser;
import component.api.IDeclaration;
import component.api.IField;
import component.api.IMethod;
import component.impl.Declaration;
import component.impl.Field;
import component.impl.Method;
import umlMaker.impl.UMLMakerOutputStream;


public class TestUMLMakerImpl {

	@Test
	public void testVisitDeclaration() throws IOException {
		String out = new File("docs\\TestUMLMakerImpl.txt").getAbsoluteFile().getPath();
		FileOutputStream fop = new FileOutputStream(out);
		UMLMakerOutputStream stream = new UMLMakerOutputStream(fop);
		String[] interfaces = {"Interface 1","Interface 2"};
		IDeclaration id = new Declaration(87, 4, "This_is_a_test", "Bo Peng", "I'm Super Name",interfaces);
		stream.preVisit(id);
		stream.visit(id);
		DesignParser d = new DesignParser();
		stream.postVisit(id);
		FileInputStream fip = new FileInputStream(out);
		assertEquals(fip.available(),251);
	}
	
	@Test
	public void testVisitField() throws IOException {
		String out = new File("docs\\TestUMLMakerImpl.txt").getAbsoluteFile().getPath();
		FileOutputStream fop = new FileOutputStream(out);
		UMLMakerOutputStream stream = new UMLMakerOutputStream(fop);
		IField ife = new Field(2, "This is a name", "This is a desc", "This.is.a.signature", null);
		stream.preVisit(ife);
		stream.visit(ife);
		DesignParser d = new DesignParser();
		stream.postVisit(ife);
		FileInputStream fip = new FileInputStream(out);
		assertEquals(fip.available(),44);
	}
	
	@Test
	public void testVisitMethod() throws IOException {
		String out = new File("docs\\TestUMLMakerImpl.txt").getAbsoluteFile().getPath();
		FileOutputStream fop = new FileOutputStream(out);
		UMLMakerOutputStream stream = new UMLMakerOutputStream(fop);
		IMethod im = new Method(2, "This is a name", null, "This.is.a.signature", null);
		stream.preVisit(im);
		stream.visit(im);
		DesignParser d = new DesignParser();
		stream.postVisit(im);
		FileInputStream fip = new FileInputStream(out);
		assertEquals(fip.available(),46);
	}
	
	

}
