package problem.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import component.api.IMethod;
import component.api.IModel;
import component.api.IRelation;
import component.api.ISingleton;
import component.impl.Method;

public class ClassMethodVisitor extends ClassVisitor {
	private IModel _model;
	private int api;

	public ClassMethodVisitor(int api, IModel model) {
		super(api);
		this._model = model;
		this.api = api;
	}

	public ClassMethodVisitor(int api, ClassVisitor decorated, IModel model) {
		super(api, decorated);
		this._model = model;
		this.api = api;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		MethodVisitor newMethodVisitor = new InvokeVisitor(this.api, toDecorate, this._model);
		// newMethodVisitor.visitInvokeInsn(access, name, desc);

		// System.out.println("--------------------");
		IMethod method = new Method(access, name, desc, signature, exceptions);
		if (Type.getReturnType(desc).getClassName() != null) {
			if (Type.getReturnType(desc).getClassName().equals(this._model.getCurrentClass().getName().replaceAll("/", "."))) {
				ISingleton single = (ISingleton) this._model.getCurrentClass().getRelations().iterator().next();
				if (single.getType().equals("Singleton")) {
					single.setMethod();
				}
			}
		}
		this._model.getCurrentClass().addComponent(method);
		// TODO: delete the line below
		// System.out.println("method " + name);
		// TODO: create an internal representation of the current method and
		// pass it to the methods below
		// addAccessLevel(access);
		// System.out.print(name + "(");
		// addArguments(desc);
		// System.out.print(") : ");
		// addReturnType(desc);
		// System.out.print("\\l");
		// TODO: add the current method to your internal representation of the
		// current class
		// What is a good way for the code to remember what the current class
		// is?
		return newMethodVisitor;
	}

	void addAccessLevel(int access) {
		String level = "";
		if ((access & Opcodes.ACC_PUBLIC) != 0) {
			level = "+";
		} else if ((access & Opcodes.ACC_PROTECTED) != 0) {
			level = "-";
		} else if ((access & Opcodes.ACC_PRIVATE) != 0) {
			level = "#";
		} else {
			level = "";
		}
		// TODO: delete the next line
		System.out.print(" " + level + " ");
		// TODO: ADD this information to your representation of the current
		// method.
	}

	void addReturnType(String desc) {
		String returnType = Type.getReturnType(desc).getClassName();
		// TODO: delete the next line
		// System.out.println("return type: " + returnType);
		// TODO: ADD this information to your representation of the current
		// method.
		System.out.print(returnType);
	}

	void addArguments(String desc) {
		Type[] args = Type.getArgumentTypes(desc);
		for (int i = 0; i < args.length; i++) {
			String arg = args[i].getClassName();
			// TODO: delete the next line
			// System.out.print("arg " + i + ": " + arg);
			// TODO: ADD this information to your representation of the current
			// method.
			System.out.print(arg + " ");
		}
	}

}