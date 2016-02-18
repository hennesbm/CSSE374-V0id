package component.impl;

import java.util.ArrayList;

import component.api.IPattern;
import visitor.api.IVisitor;

public class Adapter implements IPattern {
	private String className;
	private String component;
	private String adaptee;
	private ArrayList<String> allInfluencedClasses;

	public Adapter(String className, String component, String adaptee, ArrayList<String> allInfluencedClasses) {
		this.className = className;
		this.component = component;
		this.adaptee = adaptee;
		this.allInfluencedClasses = allInfluencedClasses;
		if(this.adaptee == null){
			this.adaptee = "";
		}
	}

	@Override
	public String getType() {
		return "Adapter";
	}

	@Override
	public String getComponent() {
		return this.component;
	}

	@Override
	public String getColor() {
		return "red";
	}

	@Override
	public void accept(IVisitor v) {
		v.preVisit(this);
		v.visit(this);
		v.postVisit(this);
	}
	
	public String getClassName() {
		return this.className;
	}
	
	public String getAdaptee() {
		return this.adaptee;
	}

	@Override
	public String getInvoker() {
		return this.className;
	}

	@Override
	public String getAccepter() {
		return this.adaptee;
	}

	@Override
	public ArrayList<String> getAllInfluencedClasses() {
		return this.allInfluencedClasses;
	}
	
	

}