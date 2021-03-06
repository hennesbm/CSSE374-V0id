package component.impl;

import java.util.ArrayList;

import component.api.IPattern;
import visitor.api.IVisitor;

public class Singleton implements IPattern {

	private String className;
		
	public Singleton(String className) {
		this.className = className;
	}
	
	@Override
	public String getType() {
		return "Singleton";
	}
	
	@Override
	public void accept(IVisitor v) {
		v.preVisit(this);
		v.visit(this);
		v.postVisit(this);
	}
	
	@Override
	public String getComponent() {
		return "Singleton";
	}

	@Override
	public String getColor() {
		return "blue";
	}
	
	public String getClassName() {
		return this.className;
	}

	@Override
	public String getInvoker() {
		return this.className;
	}

	@Override
	public String getAccepter() {
		return "";
	}

	@Override
	public ArrayList<String> getAllInfluencedClasses() {
		ArrayList<String> allInfluencedClasses = new ArrayList<String>();
		allInfluencedClasses.add(this.className);
		return allInfluencedClasses;
	}


}
