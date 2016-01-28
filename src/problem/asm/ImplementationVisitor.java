package problem.asm;

import org.objectweb.asm.ClassVisitor;

import component.api.IModel;
import component.impl.Implements;

public class ImplementationVisitor extends ClassVisitor {
	private IModel _model;

	public ImplementationVisitor(int api, IModel model) {
		super(api);
		this._model = model;
	}

	public ImplementationVisitor(int api, ClassVisitor decorated, IModel model) {
		super(api, decorated);
		this._model = model;
	}

	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		super.visit(version, access, name, signature, superName, interfaces);
		for (String s : interfaces) {
			String[] typeParts = s.split("/");
			setImplementationRelation(typeParts[typeParts.length - 1]);
		}
	}

	private void setImplementationRelation(String referenceName) {
		String[] classNameParts = this._model.getCurrentClass().getName().split("/");
		this._model.getCurrentClass()
				.addRelation(new Implements(classNameParts[classNameParts.length - 1], referenceName));
	}

}