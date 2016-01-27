package problem.asm;

import java.util.Arrays;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import com.sun.org.apache.xpath.internal.compiler.OpCodes;

import component.api.IDeclaration;
import component.api.IModel;
import component.impl.Declaration;
import component.impl.Extend;
import component.impl.Implement;
import component.impl.Singleton;

public class ClassDeclarationVisitor extends ClassVisitor {
	private IModel _model;
	private IDeclaration declaration;
	public ClassDeclarationVisitor(int api, IModel model) {
		super(api);
		this._model = model;
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		this.declaration = new Declaration(version, access, name, signature, superName, interfaces);
		System.out.println("Class: "+name+" extends "+superName+" implements "+Arrays.toString(interfaces));
		
		Implement imp = new Implement(name, interfaces);
		declaration.addRelation(imp);
		
//		if ((access & Opcodes.ACC_ABSTRACT) != 0 && (access & Opcodes.ACC_INTERFACE) == 0){
//			this.declaration = new Declaration(version, access, name, signature, superName, interfaces, true);
//		}else{
//			this.declaration = new Declaration(version, access, name, signature, superName, interfaces, false);
//		}
		
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