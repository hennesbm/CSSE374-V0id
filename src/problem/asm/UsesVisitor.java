package problem.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import com.sun.org.apache.bcel.internal.generic.Type;

import component.api.IModel;
import component.api.IRelation;
import component.impl.Uses;

public class UsesVisitor extends ClassVisitor {
	private IModel _model;

	public UsesVisitor(int api, IModel model) {
		super(api);
		this._model = model;
	}

	public UsesVisitor(int api, ClassVisitor decorated, IModel model) {
		super(api, decorated);
		this._model = model;
	}

	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		for (Type type : Type.getArgumentTypes(desc)) {
			String typeName = type.toString();
			String[] typeParts = typeName.split("\\.");
			if (!typeParts[0].equals("java") && !typeParts[0].equals("int") && !typeParts[0].equals("boolean")
					&& !typeParts[0].equals("org")) {
				setUsesRelation(typeParts[typeParts.length - 1]);
			}
		}
		return toDecorate;
	}

	private void setUsesRelation(String referenceName) {
		String[] classNameParts = this._model.getCurrentClass().getName().split("/");

		for (IRelation r : this._model.getCurrentClass().getRelations()) {
			if (r.getType().equals("Uses")) {
				Uses u = (Uses) r;
				if(u.getClassName().equals(classNameParts[classNameParts.length - 1]) && u.getReferenceName().equals(referenceName)){
					return;
				}
			}
		}

		this._model.getCurrentClass().addRelation(new Uses(classNameParts[classNameParts.length - 1], referenceName));
	}
}