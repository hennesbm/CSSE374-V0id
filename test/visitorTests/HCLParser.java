package visitorTests;

import java.io.IOException;
import java.util.ArrayList;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import component.api.IModel;
import problem.asm.CompositeVisitor;
import problem.asm.HierarchyVisitor;
import problem.asm.LeafVisitor;

public class HCLParser {
	// HierarchyVisitor
	// compositeVisitor
	// leafVisitor

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
	public void main(ArrayList<String> files, IModel model) throws IOException {
		HCLParser.CLASSES = files;

		this.model = model;

		for (String className : CLASSES) {
			ClassReader reader = new ClassReader(className);

			ClassVisitor hierarchyVisitor = new HierarchyVisitor(Opcodes.ASM5, this.model);
			ClassVisitor compositeVisitor = new CompositeVisitor(Opcodes.ASM5, hierarchyVisitor, this.model);
			ClassVisitor leafVisitor = new LeafVisitor(Opcodes.ASM5, compositeVisitor, this.model);
			reader.accept(leafVisitor, ClassReader.EXPAND_FRAMES);
		}
	}

}
