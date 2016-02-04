package component.impl;

import visitor.api.IVisitor;
import component.api.IPattern;

public class Composite implements IPattern{

	private String componentclassname;
	private String className;
	private String component;
	
	
	public Composite(String className, String component){
		this.className = className;
		this.component = component;
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

}