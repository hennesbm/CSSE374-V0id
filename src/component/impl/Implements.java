package component.impl;

import visitor.api.ITraverser;
import visitor.api.IVisitor;
import component.api.IRelation;

public class Implements implements IRelation, ITraverser {
	private String className;
	private String referenceName;
	
	public Implements(String className, String referenceName){
		this.className = className;
		this.referenceName = referenceName;
	}
	
	@Override
	public void accept(IVisitor v) {
		v.preVisit(this);
		v.visit(this);
		v.postVisit(this);
	}

	@Override
	public String getType() {
		return "Implements";
	}
	
	public String getClassName() {
		return this.className;
	}
	
	public String getReferenceName() {
		return this.referenceName;
	}

	@Override
	public String getInvoker() {
		return this.className;
	}

	@Override
	public String getAccepter() {
		return this.referenceName;
	}
}