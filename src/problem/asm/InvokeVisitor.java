package problem.asm;

import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;

import component.api.IModel;

public class InvokeVisitor extends MethodVisitor {
	private IModel _model;

	public InvokeVisitor(int api, IModel model) {
		super(api);
	}

	public InvokeVisitor(int api, MethodVisitor decorated, IModel model) {
		super(api, decorated);
		this._model = model;
	}
	
	@Override
	public void visitInvokeDynamicInsn(String name, String desc, Handle bsm,
            Object... bsmArgs) {
		super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
		System.out.println(name);
		System.out.println(desc);
		System.out.println(bsm);
		System.out.println(bsmArgs);
	}

}