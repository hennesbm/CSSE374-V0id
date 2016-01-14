package problem.asm;

import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import component.api.IModel;
import component.api.IStatement;
import component.impl.Statement;

public class InvokeVisitor extends MethodVisitor {
	private IModel _model;

	public InvokeVisitor(int api, IModel model) {
		super(api);
	}

	public InvokeVisitor(int api, MethodVisitor decorated, IModel model) {
		super(api, decorated);
		this._model = model;
	}
	
	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		super.visitMethodInsn(opcode, owner, name, desc, itf);
		IStatement statement = new Statement(opcode, owner, name, desc, itf);
		this._model.getCurrentClass().addComponent(statement);
//		System.out.println(owner);
//		System.out.println(name);
//		System.out.println(desc);
//		System.out.println(itf);
		//this._model.getCurrentClass().addComponent(c);
//		System.out.println(bsm);
//		System.out.println(bsmArgs);
	}

}