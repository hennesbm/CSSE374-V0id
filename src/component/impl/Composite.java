package component.impl;

import visitor.api.IVisitor;

import java.util.ArrayList;

import component.api.IPattern;

public class Composite implements IPattern{

	private String componentclassname;
	private String className;
	private String component;
	
	
	public Composite(String className, String component){
		this.className = className;
		this.component = component;
		this.componentclassname = "";
	}
	
	public Composite(String className, String componentclassname, String component){
		this.className = className;
		this.componentclassname = componentclassname;
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
		return "Composite";
	}

	@Override
	public String getComponent() {
		return this.component;
	}

	@Override
	public String getColor() {
		return "Yellow";
	}
	
	public String getClassName(){
		return this.className;
	}
	
	public String getComponentClassName(){
		return this.componentclassname;
	}

	@Override
	public String getInvoker() {
		return className;
	}

	@Override
	public String getAccepter() {
		return componentclassname;
	}

	@Override
	public ArrayList<String> getAllInfluencedClasses() {
		ArrayList<String> allInfluencedClasses = new ArrayList<String>();
		allInfluencedClasses.add(this.className);
		allInfluencedClasses.add(this.componentclassname);
		return allInfluencedClasses;
	}

}
