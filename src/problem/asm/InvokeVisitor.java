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
		super.visitMethodInsn(opcode, owner, name, desc, itf);
		IStatement statement = new Statement(opcode, owner, name, desc, itf, this._model.getCurrentClass().getName(),this.methodname);
		this._model.getCurrentClass().addComponent(statement);
	}

}