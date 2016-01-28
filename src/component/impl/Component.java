package component.impl;

import visitor.api.ITraverser;
import visitor.api.IVisitor;
import component.api.IRelation;

public class Component implements IRelation, ITraverser{

	@Override
	public void accept(IVisitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		return "Component";
	}

}
