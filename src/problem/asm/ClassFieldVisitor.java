package problem.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ClassFieldVisitor extends ClassVisitor {
	public ClassFieldVisitor(int api) {
		super(api);
	}

	public ClassFieldVisitor(int api, ClassVisitor decorated) {
		super(api, decorated);
	}

	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		String type = Type.getType(desc).getClassName();
		// TODO: delete the line below

		// System.out.println(" " + type + " " + signature + "-----");

		addAccessLevel(access);
		addColon(name);
		addEnter(type);

		// TODO: add this field to your internal representation of the current
		// class.
		// What is a good way to know what the current class is?
		return toDecorate;
	};

	private void addEnter(String signature) {
		// TODO deal with java.util.ArrayList
		System.out.print(signature + "\\" + "l");

	}

	private void addColon(String name) {
		System.out.print(name + " : ");
	}

	String addAccessLevel(int access) {
		String level = " ";
		if ((access & Opcodes.ACC_PUBLIC) != 0) {
			level = " + ";
		} else if ((access & Opcodes.ACC_PROTECTED) != 0) {
			level = " ~ ";
		} else if ((access & Opcodes.ACC_PRIVATE) != 0) {
			level = " - ";
		} else {
			level = " ";
			// TODO: Verify default symbol
		}
		// TODO: ADD this information to your representation of the current
		// method.
		return level;
	}
}