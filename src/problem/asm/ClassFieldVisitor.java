package problem.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

import component.api.IModel;
import component.impl.Field;

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
		Field field = new Field(access, name, desc, signature, value);
		this._model.getCurrentClass().addComponent(field);
		return toDecorate;
	};
}