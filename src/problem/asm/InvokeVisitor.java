package problem.asm;

import org.objectweb.asm.MethodVisitor;

import component.api.IModel;
import component.api.IStatement;
import component.impl.Statement;

public class InvokeVisitor extends MethodVisitor {
	private IModel _model;
	private String methodname;

	public InvokeVisitor(int api, IModel model) {
		super(api);
	}

	public InvokeVisitor(int api, MethodVisitor decorated, IModel model, String methodname) {
		super(api, decorated);
		this._model = model;
		this.methodname = methodname;
	}
	
	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		//this._model.getCurrentClass()
		super.visitMethodInsn(opcode, owner, name, desc, itf);
		System.out.println("Current Class:" + this._model.getCurrentClass().getName());
		IStatement statement = new Statement(opcode, owner, name, desc, itf, this._model.getCurrentClass().getName(),this.methodname);
		this._model.getCurrentClass().addComponent(statement);
		System.out.println("Arguments Type:" + owner);
		System.out.println("Current Statement's Method Type:" + name);
		System.out.println("Current Statement's Return Type:" + desc);
		System.out.println("Does Current Statement's Method Has Interface :" + itf);
		System.out.println();
		//this._model.getCurrentClass().addComponent(c);
//		System.out.println(bsm);
//		System.out.println(bsmArgs);
	}

}