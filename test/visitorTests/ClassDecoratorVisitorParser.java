package visitorTests;

import java.io.IOException;
import java.util.ArrayList;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

import component.api.IModel;
import problem.asm.ClassDecoratorVisitor;

public class ClassDecoratorVisitorParser {
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
		ClassDecoratorVisitorParser.CLASSES = files;

		this.model = model;

		for (String className : CLASSES) {
			ClassReader reader = new ClassReader(className);

			ClassDecoratorVisitor av = new ClassDecoratorVisitor(Opcodes.ASM5, this.model);
			reader.accept(av, ClassReader.EXPAND_FRAMES);
		}
	}
}
