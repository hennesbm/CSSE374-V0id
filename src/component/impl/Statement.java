package component.impl;

import component.api.IStatement;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class Statement implements ITraverser, IStatement{
	private int opcode;
	private String owner;
	private String name;
	private String desc;
	private boolean inter;

	public Statement(int opcode, String owner, String name, String desc, boolean inter){
		this.opcode = opcode;
		this.owner = owner;
		this.name = name;
		this.desc = desc;
		this.inter = inter;
	}
	
	@Override
	public String getType() {
		return "Statement";
	}

	@Override
	public String getSignature() {
		return null;
	}

	@Override
	public String getOwner() {
		return this.owner;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.desc;
	}

	@Override
	public boolean ifInterface() {
		return this.inter;
	}

	@Override
	public void accept(IVisitor v) {
		v.preVisit(this);
		v.visit(this);
		v.postVisit(this);
	}

	@Override
	public int getOpcode() {
		return this.opcode;
	}


}
