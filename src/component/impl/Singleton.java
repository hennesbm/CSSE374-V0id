package component.impl;

import component.api.ISingleton;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class Singleton implements ISingleton, ITraverser {
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
	@Override
	public boolean isField() {
		return this.field;
	}
	@Override
	public void setField() {
		this.field = true;
	}
	@Override
	public void setMethod() {
		this.method = true;
	}
	@Override
	public boolean isMethod() {
		return this.method;
	}
	@Override
	public boolean isSingleton() {
		return (isField() && isMethod());
	}
}
