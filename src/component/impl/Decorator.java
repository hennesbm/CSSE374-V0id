package component.impl;

import visitor.api.ITraverser;
import visitor.api.IVisitor;

import java.util.ArrayList;

import component.api.IPattern;
import component.api.IRelation;

public class Decorator implements IPattern {

	private String decorates;
	private String className;
	private String component;
	
	public Decorator(String className, String component){
		this.className = className;
		this.component = component;
		this.decorates = "";
	}
	
	public Decorator(String className, String decorates, String component){
		this.className = className;
		this.decorates = decorates;
		this.component = component;
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
	
	public String getDecorates(){
		return this.decorates;
	}

	@Override
	public String getComponent() {
		return this.component;
	}

	@Override
	public String getColor() {
		return "Green";
	}
	
	public String getClassName(){
		return this.className;
	}

	@Override
	public String getInvoker() {
		return this.className;
	}

	@Override
	public String getAccepter() {
		return this.decorates;
	}

	@Override
	public ArrayList<String> getAllInfluencedClasses() {
		ArrayList<String> allInfluencedClasses = new ArrayList<String>();
		allInfluencedClasses.add(this.className);
		allInfluencedClasses.add(this.decorates);
		return allInfluencedClasses;
	}
	

}
