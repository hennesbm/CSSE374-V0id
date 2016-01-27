package problem.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
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
		return toDecorate;
	};
}