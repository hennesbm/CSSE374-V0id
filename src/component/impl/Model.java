package component.impl;

import java.io.IOException;
import java.util.ArrayList;

import component.api.IComponent;
import component.api.IDeclaration;
import component.api.IModel;
import component.api.IPattern;
import component.api.IRelation;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class Model implements IModel, ITraverser {
	private IDeclaration currentClass;
	private ArrayList<IDeclaration> classList = new ArrayList<IDeclaration>();

	@Override
	public void setCurrentClass(IDeclaration clazz) {
		this.currentClass = clazz;
	}

	@Override
	public void addCurrentClass() {
		this.classList.add(this.currentClass);
	}

	@Override
	public IDeclaration getCurrentClass() {
		return this.currentClass;
	}
	
	@Override
	public void accept(IVisitor v) {
		for (IDeclaration clazz : this.classList) {
			int methodcount = 0;
			int fieldcount = 0;
			v.preVisit(clazz);

			String type = "Field";
			boolean change = false;
			boolean toomuchmethod = false;
			boolean toomuchfield = false;
			if (clazz.getComponents().size() > 0) {
				for (IComponent p : clazz.getComponents()) {
					if (!p.getType().equals(type) && !change) {
						v.visit(clazz);
						change = true;
					}
					
					
					
					
					if(p.getType().equals("Field") && fieldcount <=10){
						fieldcount++;
						p.accept(v);
					}
					else if (p.getType().equals("Method") && methodcount <=10){
						methodcount++;
						p.accept(v);
					}
					else if(!p.getType().equals("Field") && !p.getType().equals("Method")){
						p.accept(v);
					}else if((p.getType().equals("Field") && fieldcount == 11 && !toomuchfield) ){
						
						toomuchfield = true;
						Field field = (Field) p;
						field.toomuchflag = true;
						field.accept(v);
					}else if((p.getType().equals("Method") && methodcount == 11 && !toomuchmethod) ){
						toomuchmethod = true;
						Method method = (Method) p;
						method.toomuchflag = true;
						method.accept(v);
					}
					
					

				}
			}

			v.postVisit(clazz);
			
			if (clazz.getRelations().size() > 0) {
				int relationcount = 0;
				for (IRelation p : clazz.getRelations()) {
//					if(relationcount >= 10){
//						break;
//					}
					String invoker = p.getInvoker();
					String accepter = p.getAccepter();
					if(relationCheck(invoker, accepter)){
						p.accept(v);
					}
					relationcount++;
					
				}
			}
			
			if (clazz.getPatterns().size() > 0) {
				int patterncount = 0;
				for (IPattern p : clazz.getPatterns()) {
//					if(patterncount >= 10){
//						break;
//					}
					p.accept(v);
					patterncount++;
				}
			}
		}


	}

	@Override
	public ArrayList<IDeclaration> getAllClasses() {
		return this.classList;
	}
	
	public boolean relationCheck(String invoker, String accepter){
		ArrayList<String> classnames = new ArrayList<String>();
		for(IDeclaration d: this.classList){
			String[] name = d.getName().split("/");
			classnames.add(name[name.length - 1]);
		}
		if(classnames.contains(invoker) && classnames.contains(accepter)){
			return true;
		}
		return false;
	}
	


}
