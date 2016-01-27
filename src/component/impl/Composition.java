package component.impl;

import component.api.IRelation;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class Composition implements IRelation, ITraverser {

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

}
