package component.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.objectweb.asm.Opcodes;

import component.api.IComponent;
import component.api.IDeclaration;
import component.api.IPattern;
import component.api.IRelation;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class Declaration implements IDeclaration, ITraverser {
	private int version;
	private int access;
	private String name;
	private String signature;
	private String superName;
	private String[] interfaces;
	private Collection<IComponent> components = new ArrayList<>();
	private Collection<IRelation> relations = new ArrayList<>();
	private Collection<IPattern> patterns = new ArrayList<>();
	private Collection<IDeclaration> hierarchy = new ArrayList<>();
	private boolean activity = true;
	//private boolean isabstract = false;

	public Declaration(int version, int access, String name, String signature, String superName, String[] interfaces) {
		this.version = version;
		this.access = access;
		this.name = name;
		this.signature = signature;
		this.superName = superName;
		this.interfaces = interfaces;
	}

	@Override
	public int getVersion() {
		return this.version;
	}

	@Override
	public int getAccess() {
		return this.access;
	}
	
	public void setActivity(boolean activity){
		this.activity = activity;
	}
	
	public boolean getActivity(){
		return this.activity;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getSignature() {
		return this.signature;
	}

	@Override
	public String getSuperClass() {
		return this.superName;
	}

	@Override
	public String[] getInterfaces() {
		return this.interfaces;
	}

	@Override
	public void addComponent(IComponent c) {
		this.components.add(c);
	}

	@Override
	public Collection<IComponent> getComponents() {
		return this.components;
	}
	
	@Override
	public void addRelation(IRelation r) {
		this.relations.add(r);
	}
	
	@Override
	public Collection<IRelation> getRelations() {
		return this.relations;
	}

	@Override
	public void accept(IVisitor v) {
		v.preVisit(this);
		for (IComponent p : this.components) {
			ITraverser t = (ITraverser) p;
			t.accept(v);
		}
		for (IRelation p : this.relations) {
			ITraverser t = (ITraverser) p;
			t.accept(v);
		}
		v.postVisit(this);
	}
	
	public boolean isAbstract(){
		if((access & Opcodes.ACC_ABSTRACT) != 0 && (access & Opcodes.ACC_INTERFACE) == 0){
			return true;
		}
		return false;
	}
	
	public boolean isInterface(){
		if((access & Opcodes.ACC_ABSTRACT) != 0 && (access & Opcodes.ACC_INTERFACE) != 0){
			return true;
		}
		return false;
	}
	
	@Override
	public Collection<IPattern> getPatterns() {
		return this.patterns;
	}

	@Override
	public void addPattern(IPattern p) {
//		for(IPattern pattern: this.patterns){
//			IPattern t = pattern;
//			//System.out.println(p.getAccepter()+ p.getComponent()+p.getType() +p.getInvoker());
//			if(
//			pattern.getAccepter().equals(p.getAccepter())&&
//			pattern.getComponent().equals(p.getComponent())&&
//			pattern.getType().equals(p.getType())&&
//			pattern.getInvoker().equals(p.getInvoker())){
//				return;
//			}
//		}
//		
//		if(this.activity){
//			
//		}
		this.patterns.add(p);
	}
	
	@Override
	public Collection<IDeclaration> getHierarchy(){
		return this.hierarchy;
	}
}
