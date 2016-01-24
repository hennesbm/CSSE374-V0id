package component.impl;

import component.api.IRelation;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class Singleton implements IRelation, ITraverser {
	private boolean field = false;
	private boolean method = false;
		
	@Override
	public String getType() {
		return "Singleton";
	}
	@Override
	public String getName() {
		return null;
	}
	@Override
	public String getReference() {
		return null;
	}
	@Override
	public void accept(IVisitor v) {
		v.preVisit(this);
		v.visit(this);
		v.postVisit(this);
	}
	public boolean isField() {
		return this.field;
	}
	public void setField() {
		this.field = true;
	}
	public void setMethod() {
		this.method = true;
	}
	public boolean isMethod() {
		return this.method;
	}
	public boolean isSingleton() {
		return (isField() && isMethod());
	}
}
