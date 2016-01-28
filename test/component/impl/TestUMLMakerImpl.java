package component.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import problem.asm.DesignParser;
import component.impl.Declaration;
import component.impl.Field;
import component.impl.Method;
import umlMaker.impl.UMLMakerOutputStream;


public class TestUMLMakerImpl {

	@Test
	public void testVisitDeclaration() throws IOException {
		String out = new File("docs\\input_output\\TestUMLMakerImpl.txt").getAbsoluteFile().getPath();
		FileOutputStream fop = new FileOutputStream(out);
		UMLMakerOutputStream stream = new UMLMakerOutputStream(fop);
		String[] interfaces = {"Interface 1","Interface 2"};
		Declaration id = new Declaration(87, 4, "This_is_a_test", "Bo Peng", "I'm Super Name",interfaces);
		stream.preVisit(id);
		stream.visit(id);
		@SuppressWarnings("unused")
		DesignParser d = new DesignParser();
		stream.postVisit(id);
		@SuppressWarnings("resource")
		FileInputStream fip = new FileInputStream(out);
		assertEquals(fip.available(),251);
	}
	
	@Test
	public void testVisitField() throws IOException {
		String out = new File("docs\\input_output\\TestUMLMakerImpl.txt").getAbsoluteFile().getPath();
		FileOutputStream fop = new FileOutputStream(out);
		UMLMakerOutputStream stream = new UMLMakerOutputStream(fop);
		Field ife = new Field(2, "This is a name", "This is a desc", "This.is.a.signature", null, null);
		stream.preVisit(ife);
		stream.visit(ife);
		@SuppressWarnings("unused")
		DesignParser d = new DesignParser();
		stream.postVisit(ife);
		@SuppressWarnings("resource")
		FileInputStream fip = new FileInputStream(out);
		assertEquals(fip.available(),44);
	}
	
	@Test
	public void testVisitMethod() throws IOException {
		String out = new File("docs\\input_output\\TestUMLMakerImpl.txt").getAbsoluteFile().getPath();
		FileOutputStream fop = new FileOutputStream(out);
		UMLMakerOutputStream stream = new UMLMakerOutputStream(fop);
		Method im = new Method(2, "This is a name", null, "This.is.a.signature", null, null);
		stream.preVisit(im);
		stream.visit(im);
		@SuppressWarnings("unused")
		DesignParser d = new DesignParser();
		stream.postVisit(im);
		@SuppressWarnings("resource")
		FileInputStream fip = new FileInputStream(out);
		assertEquals(fip.available(),46);
	}
	
	

}
