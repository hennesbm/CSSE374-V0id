package problem.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import component.api.IModel;
import component.impl.Field;
import component.impl.Singleton;

public class ClassFieldVisitor extends ClassVisitor {
	private IModel _model;

	public ClassFieldVisitor(int api, IModel model) {
		super(api);
		this._model = model;
	}

	public ClassFieldVisitor(int api, ClassVisitor decorated, IModel model) {
		super(api, decorated);
		this._model = model;
	}

	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		@SuppressWarnings("unused")
		String type = Type.getType(desc).getClassName();
		// TODO: delete the line below
		Field field = new Field(access, name, desc, signature, value);
		if (Type.getType(desc).getClassName() != null) {
			if (Type.getType(desc).getClassName().equals(this._model.getCurrentClass().getName().replaceAll("/", "."))) {
				Singleton single = (Singleton) this._model.getCurrentClass().getRelations().iterator().next();
				if (single.getType().equals("Singleton")) {
					single.setField();
				}
			}
		}
		this._model.getCurrentClass().addComponent(field);
		// System.out.println(" " + type + " " + signature + "-----");

		// addAccessLevel(access);
		// addColon(name);
		// addEnter(type);

		// TODO: add this field to your internal representation of the current
		// class.
		// What is a good way to know what the current class is?
		return toDecorate;
	};

	@SuppressWarnings("unused")
	private void addEnter(String signature) {
		// TODO deal with java.util.ArrayList
		System.out.print(signature + "\\" + "l");

	}

	@SuppressWarnings("unused")
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