package problem.asm;

import java.io.IOException;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import umlMaker.api.IModel;
import umlMaker.impl.Model;

public class DesignParser {
	public String[] CLASSES;
	public IModel model;
	/**
	 * Reads in a list of Java Classes and reverse engineers their design.
	 *
	 * @param args:
	 *            the names of the classes, separated by spaces. For example:
	 *            java DesignParser java.lang.String
	 *            edu.rosehulman.csse374.ClassFieldVisitor java.lang.Math
	 * @throws IOException
	 */
	public void main(String[] args) throws IOException {
		this.CLASSES = args;
		
		for (String className : CLASSES) {
//			System.out.println("====================");
			// ASM's ClassReader does the heavy lifting of parsing the compiled
			// Java class
//			System.out.println("Analyzing: " + className);
//			System.out.println(className + "[");
			ClassReader reader = new ClassReader(className);
			this.model = new Model();
			
			// make class declaration visitor to get superclass and interfaces
			ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, this.model);
			
			// DECORATE declaration visitor with field visitor
			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor, this.model);
			
			// DECORATE field visitor with method visitor
			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, this.model);
			
			// TODO: add more DECORATORS here in later milestones to accomplish
			// specific tasks
			// Tell the Reader to use our (heavily decorated) ClassVisitor to
			// visit the class
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
//			System.out.println("\n]");
		}
	}
}