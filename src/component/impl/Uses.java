package component.impl;

import component.api.IRelation;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class Uses implements IRelation, ITraverser {

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

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getReference() {
		return null;
	}

}
