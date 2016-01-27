package component.impl;

import visitor.api.ITraverser;
import visitor.api.IVisitor;
import component.api.IRelation;

public class Extend implements IRelation, ITraverser {
	String classextended = "";
	String classname = "";
	public Extend(String classextended, String classname){
		this.classextended = classextended;
		this.classname = classname;
	}
	
	@Override
	public void accept(IVisitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		return "Extend";
	}

	@Override
	public String getName() {
		return this.classname;
	}

	@Override
	public String getReference() {
		return this.classextended;
	}
	
	public void setRegerence(String classextended){
		this.classextended = classextended;
	}

}
