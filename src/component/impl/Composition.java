package component.impl;

import component.api.IRelation;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class Composition implements IRelation, ITraverser {
	private String className;
	private String referenceName;
	
	public Composition(String className, String referenceName) {
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
		return "Composition";
	}
	
	public String getClassName() {
		return this.className;
	}

	public String getReferenceName() {
		return this.referenceName;
	}
}
