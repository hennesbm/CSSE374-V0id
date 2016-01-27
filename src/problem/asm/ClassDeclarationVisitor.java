package problem.asm;

import java.util.Arrays;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import com.sun.org.apache.xpath.internal.compiler.OpCodes;

import component.api.IDeclaration;
import component.api.IModel;
import component.impl.Declaration;


public class ClassDeclarationVisitor extends ClassVisitor {
	private IModel _model;
	private IDeclaration declaration;
	public ClassDeclarationVisitor(int api, IModel model) {
		super(api);
		this._model = model;
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {

		IDeclaration declaration = new Declaration(version, access, name, signature, superName, interfaces);
		this._model.setCurrentClass(declaration);
//		String nameforextend = name.replaceAll("/", ".");
//		String supernameforextend = superName.replaceAll("/", ".");
		String nameforextend = name;
		String supernameforextend = superName;
		Extend e = new Extend(supernameforextend, nameforextend);
		declaration.addRelation(e);
		
		this._model.addCurrentClass();
		super.visit(version, access, name, signature, superName, interfaces);
	}
}