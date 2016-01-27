package component.impl;

import java.util.Arrays;

import visitor.api.ITraverser;
import visitor.api.IVisitor;
import component.api.IRelation;

public class Implement implements IRelation, ITraverser {

	String classname = "";
	String[] implementedclasses;
	public Implement(String classname, String[] implementedclasses){
		this.classname = classname;
		this.implementedclasses = implementedclasses;
	}
	
	@Override
	public void accept(IVisitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		return "Implement";
	}

}
