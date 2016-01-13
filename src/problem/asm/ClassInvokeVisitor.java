package problem.asm;

import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;

import component.api.IModel;

public class ClassInvokeVisitor extends MethodVisitor {
	private IModel _model;

	public ClassInvokeVisitor(int api, IModel model) {
		super(api);
		this._model = model;
	}

	public ClassInvokeVisitor(int api, MethodVisitor decorated, IModel model) {
		super(api, decorated);
		this._model = model;
	}

	@Override
	public void visitInvokeDynamicInsn(String name, String desc, Handle bsm,
            Object... bsmArgs) {
		System.out.println(name);
		System.out.println(desc);
		System.out.println(bsm);
		System.out.println(bsmArgs);
//		System.out.println("--------------------");
		// TODO: delete the line below
//		System.out.println("method " + name);
		// TODO: create an internal representation of the current method and
		// pass it to the methods below
//		addAccessLevel(access);
//		System.out.print(name + "(");
//		addArguments(desc);
//		System.out.print(") : ");
//		addReturnType(desc);
//		System.out.print("\\l");
		// TODO: add the current method to your internal representation of the
		// current class
		// What is a good way for the code to remember what the current class
		// is?
	}

}