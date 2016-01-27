package problem.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import component.api.IModel;
import component.impl.Singleton;

public class SingletonVisitor extends ClassVisitor {
	private IModel _model;
	private boolean isSingleton = false;
	private boolean isField = false;
	private boolean isMethod = false;

	public SingletonVisitor(int api, IModel model) {
		super(api);
		this._model = model;
	}

	public SingletonVisitor(int api, ClassVisitor decorated, IModel model) {
		super(api, decorated);
		this._model = model;
	}

	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		if (Type.getType(desc).getClassName() != null) {
			if (Type.getType(desc).getClassName()
					.equals(this._model.getCurrentClass().getName().replaceAll("/", "."))) {
				setField();
				if (this.isField) {
					if (this.isMethod) {
						if (!this.isSingleton) {
							setSingleton();
						}
					}
				}
			}
		}
		return toDecorate;
	}

	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		if (Type.getReturnType(desc).getClassName() != null) {
			if (Type.getReturnType(desc).getClassName()
					.equals(this._model.getCurrentClass().getName().replaceAll("/", "."))) {
				setMethod();
				if (this.isMethod) {
					if (this.isField) {
						if (!this.isSingleton) {
							setSingleton();
						}
					}
				}
			}
		}
		return toDecorate;
	}

	private void setField() {
		this.isField = true;
	}

	private void setMethod() {
		this.isMethod = true;
	}

	private void setSingleton() {
		this.isSingleton = true;
		String[] classNameParts = this._model.getCurrentClass().getName().split("/");
		this._model.getCurrentClass().addRelation(new Singleton(classNameParts[classNameParts.length - 1]));
	}
}
