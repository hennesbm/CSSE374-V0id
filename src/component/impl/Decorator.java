package component.impl;

import visitor.api.ITraverser;
import visitor.api.IVisitor;
import component.api.IRelation;

public class Decorator implements IRelation, ITraverser{

	private String decorated;
	public Decorator(String decorated){
		this.decorated = decorated;
	}
	
	@Override
	public void accept(IVisitor v) {
		v.preVisit(this);
		v.visit(this);
		v.postVisit(this);
	}

	@Override
	public String getType() {
		return  "Decorator";
	}
	
	public String getDecorated(){
		return this.decorated;
	}
	
	

}
