package component.impl;

import component.api.IRelation;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class Uses implements IRelation, ITraverser {
	private String className;
	private String referenceName;
	
	public Uses(String className, String referenceName) {
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
		return "Uses";
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
