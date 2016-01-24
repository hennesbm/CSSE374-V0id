package component.impl;

import component.api.IComponent;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class Field implements IComponent, ITraverser {
	private int access;
	private String name;
	private String description;
	private String signature;
	private Object value;

	public Field(int access, String name, String desc, String signature, Object value) {
		this.access = access;
		this.name = name;
		this.description = desc;
		this.signature = signature;
		this.value = value;
	}

	public int getAccess() {
		return this.access;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getSignature() {
		return this.signature;
	}

	public Object getValue() {
		return this.value;
	}

	@Override
	public void accept(IVisitor v) {
		v.preVisit(this);
		v.visit(this);
		v.postVisit(this);
	}

	@Override
	public String getType() {
		return "Field";
	}

}
