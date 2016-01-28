package problem.asm;

import org.objectweb.asm.ClassVisitor;

import component.api.IDeclaration;
import component.api.IModel;
import component.impl.Declaration;


public class ClassDeclarationVisitor extends ClassVisitor {
	private IModel _model;

	public ClassDeclarationVisitor(int api, IModel model) {
		super(api);
		this._model = model;
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		super.visit(version, access, name, signature, superName, interfaces);
		IDeclaration declaration = new Declaration(version, access, name, signature, superName, interfaces);
		this._model.setCurrentClass(declaration);
		this._model.addCurrentClass();	
	}
}