package problem.asm;

import java.io.IOException;
import java.util.ArrayList;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import component.api.IModel;
import component.impl.Model;

public class DesignParser {
	public static ArrayList<String> CLASSES;
	public static String markedSelected;
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
	public void main(ArrayList<String> files) throws IOException {
		DesignParser.CLASSES = files;
		
		this.model = new Model();

		for (String className : CLASSES) {
//			System.out.println("====================");
			// ASM's ClassReader does the heavy lifting of parsing the compiled
			// Java class
//			System.out.println("Analyzing: " + className);
//			System.out.println(className + "[");
			ClassReader reader = new ClassReader(className);
						
			// make class declaration visitor to get superclass and interfaces
			ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, this.model);
			
			// DECORATE declaration visitor with field visitor
			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor, this.model);
			
			// DECORATE field visitor with method visitor
			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, this.model);
			
			// TODO: add more DECORATORS here in later milestones to accomplish
			// specific tasks
			ClassVisitor singletonVisitor = new SingletonVisitor(Opcodes.ASM5, methodVisitor, this.model);
			
			ClassVisitor usesVisitor = new UsesVisitor(Opcodes.ASM5, singletonVisitor, this.model);
			
			ClassVisitor decoratorVisitor = new ClassDecoratorVisitor(Opcodes.ASM5, usesVisitor, this.model);
			
			// Tell the Reader to use our (heavily decorated) ClassVisitor to
			// visit the class
			reader.accept(decoratorVisitor, ClassReader.EXPAND_FRAMES);
//			System.out.println("\n]");
		}
//		System.out.println("End Of Code");
	}
}