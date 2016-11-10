package problem.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import component.api.IModel;
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
		MethodVisitor newMethodVisitor = new InvokeVisitor(this.api, toDecorate, this._model, name);
		// newMethodVisitor.visitInvokeInsn(access, name, desc);

		String[] classNameParts = this._model.getCurrentClass().getName().split("/");
		Method method = new Method(access, name, desc, signature, exceptions,
				classNameParts[classNameParts.length - 1]);
		if (name.equals("visit")) {
			this._model.getCurrentClass().addComponent(method);
		}
		return newMethodVisitor;
	}
}