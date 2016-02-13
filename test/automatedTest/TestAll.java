package automatedTest;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import problem.asm.DesignParser;
import visitor.api.ITraverser;
import directory.reader.DirectoryReader;

public class TestAll {

	@Test
	public void testDirectoryReader() {
		String f = new File("src").getAbsoluteFile().getPath();
		DirectoryReader reader = new DirectoryReader(f);
		ArrayList<String> files = reader.readDirectory();
		String check = "[component.api.IComponent, component.api.IDeclaration, component.api.IModel, component.api.IPattern, component.api.IRelation, component.api.IStatement, component.impl.Adapter, component.impl.Component, component.impl.Composite, component.impl.Composition, component.impl.Declaration, component.impl.Decorator, component.impl.Extends, component.impl.Field, component.impl.Implements, component.impl.Method, component.impl.Model, component.impl.Singleton, component.impl.Statement, component.impl.Uses, directory.reader.DirectoryReader, problem.asm.AdapterVisitor, problem.asm.ClassDeclarationVisitor, problem.asm.ClassDecoratorVisitor, problem.asm.ClassFieldVisitor, problem.asm.ClassMethodVisitor, problem.asm.CompositeVisitor, problem.asm.CompositionVisitor, problem.asm.DesignParser, problem.asm.ExtensionVisitor, problem.asm.FirstASM, problem.asm.HierarchyVisitor, problem.asm.ImplementationVisitor, problem.asm.InvokeVisitor, problem.asm.LeafVisitor, problem.asm.PatternParser, problem.asm.SingletonVisitor, problem.asm.UsesVisitor, sdedit.app.SDEdit, sdedit.app.SDEditGenerator, sdedit.impl.SDEditOutputStream, umlMaker.app.UMLGenerator, umlMaker.app.UMLMaker, umlMaker.impl.UMLMakerOutputStream, visitor.api.ITraverser, visitor.api.IVisitor, visitor.api.VisitorAdapter]";
		assertEquals(check, files.toString());
		
	}
	
}
