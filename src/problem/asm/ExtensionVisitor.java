package problem.asm;

import org.objectweb.asm.ClassVisitor;

import component.api.IModel;
import component.impl.Extends;

public class ExtensionVisitor extends ClassVisitor {
	private IModel _model;

	public ExtensionVisitor(int api, IModel model) {
		super(api);
		this._model = model;
	}

	public ExtensionVisitor(int api, ClassVisitor decorated, IModel model) {
		super(api, decorated);
		this._model = model;
	}

	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		super.visit(version, access, name, signature, superName, interfaces);
		String[] typeParts = superName.split("/");
		setExtensionRelation(typeParts[typeParts.length - 1]);
	}

	private void setExtensionRelation(String referenceName) {
		String[] classNameParts = this._model.getCurrentClass().getName().split("/");
		this._model.getCurrentClass()
				.addRelation(new Extends(classNameParts[classNameParts.length - 1], referenceName));
	}
}
