package problem.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

import com.sun.org.apache.bcel.internal.generic.Type;

import component.api.IModel;
import component.api.IRelation;
import component.impl.Composition;

public class CompositionVisitor extends ClassVisitor {
	private IModel _model;

	public CompositionVisitor(int api, IModel model) {
		super(api);
		this._model = model;
	}

	public CompositionVisitor(int api, ClassVisitor decorated, IModel model) {
		super(api, decorated);
		this._model = model;
	}

	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		String typeName = Type.getType(desc).toString();
		String[] typeParts = typeName.split("\\.");
		if (!typeName.equals(this._model.getCurrentClass().getName().replaceAll("/", "."))) {
			setCompositionRelation(typeParts[typeParts.length - 1]);
		}
		return toDecorate;
	}

	private void setCompositionRelation(String referenceName) {
		String[] classNameParts = this._model.getCurrentClass().getName().split("/");

		for (IRelation r : this._model.getCurrentClass().getRelations()) {
			if (r.getType().equals("Composition")) {
				Composition c = (Composition) r;
				if (c.getClassName().equals(classNameParts[classNameParts.length - 1])
						&& c.getReferenceName().equals(referenceName)) {
					return;
				}
			}
		}

		this._model.getCurrentClass()
				.addRelation(new Composition(classNameParts[classNameParts.length - 1], referenceName));
	}
}