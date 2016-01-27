package component.impl;

import component.api.IRelation;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class Singleton implements IRelation, ITraverser {
	private String className;
		
	public Singleton(String className) {
		this.className = className;
	}
	
	@Override
	public String getType() {
		return "Singleton";
	}
	
	public String getClassName() {
		return this.className;
	}
	
	@Override
	public void accept(IVisitor v) {
		v.preVisit(this);
		v.visit(this);
		v.postVisit(this);
	}
}
