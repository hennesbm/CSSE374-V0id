package problem.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import component.api.IModel;
import component.impl.Method;
import component.impl.Singleton;

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
		MethodVisitor newMethodVisitor  = new InvokeVisitor(this.api, toDecorate, this._model, name);
		// newMethodVisitor.visitInvokeInsn(access, name, desc);

		Method method = new Method(access, name, desc, signature, exceptions);
		if (Type.getReturnType(desc).getClassName() != null) {
			if (Type.getReturnType(desc).getClassName().equals(this._model.getCurrentClass().getName().replaceAll("/", "."))) {
				Singleton single = (Singleton) this._model.getCurrentClass().getRelations().iterator().next();
				if (single.getType().equals("Singleton")) {
					single.setMethod();
				}
			}
		}
		this._model.getCurrentClass().addComponent(method);
		return newMethodVisitor;
	}
}