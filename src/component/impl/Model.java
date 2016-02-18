package component.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
	
	public void setActive(String classname, boolean activity){
		for(IDeclaration d: this.classList){
			if(d.getName().replace("/", ".").equals(classname)){
				((Declaration)d).setActivity(activity);
			}
		}
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
			if(clazz.getActivity()){
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
			}


			v.postVisit(clazz);
			
			if (clazz.getRelations().size() > 0) {
				int relationcount = 0;
				for (IRelation p : clazz.getRelations()) {
					//traverse all relations
					String invoker = p.getInvoker();
					//find the relation that starts from
					String accepter = p.getAccepter();
					//find the relation that accepts
					if(relationCheck(invoker, accepter)){
						
						p.accept(v);
					}
					relationcount++;
					
				}
			}
			
			if (clazz.getPatterns().size() > 0) {
				String[] name = clazz.getName().split("/");
				if(name[name.length-1].equals("String")){
					int a= 1;
				}
				
				int patterncount = 0;
				for (IPattern p : clazz.getPatterns()) {
					ArrayList<String> allInfluencedClasses = p.getAllInfluencedClasses();
					if(patternCheck(allInfluencedClasses)){
						p.accept(v);
					}
//					p.accept(v);
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
			//traverse all classes
			String[] name = d.getName().split("/");
			//name[name.length-1] will be the current class name
			if(name[name.length - 1].equals(invoker) || name[name.length - 1].equals(accepter)){
				//if there is any string that match invoker or accepter
				//which means current class is part of the current relation
				if(!d.getActivity()){
					//if current class is not active, the relation should not be invoked
					return false;
				}
			}
			
			classnames.add(name[name.length - 1]);
		}
		if(classnames.contains(invoker) && classnames.contains(accepter)){
			return true;
		}
		return false;
	}
	
	public boolean patternCheck(ArrayList<String> allInfluencedClasses){
		ArrayList<String> classnames = new ArrayList<String>();
		for(IDeclaration d: this.classList){
			//traverse all classes
			String[] name = d.getName().split("/");
			//name[name.length-1] will be the current class name
			if(allInfluencedClasses.contains(name[name.length-1])){
				//if there is any string that match invoker or accepter
				//which means current class is part of the current relation
				if(!d.getActivity()){
					//if current class is not active, the relation should not be invoked
					return false;
				}
			}
			
			classnames.add(name[name.length - 1]);
		}
		if(classnames.containsAll(allInfluencedClasses)){
			return true;
		}
		return false;
	}

	@Override
	public HashMap<String, ArrayList<IPattern>> getAllPatterns() {
		HashMap<String, ArrayList<IPattern>> patternmap = new HashMap<String, ArrayList<IPattern>>();
		for(IDeclaration d :getAllClasses()){
			for(IPattern p: d.getPatterns()){
				String type = p.getType();
				if(patternmap.containsKey(type)){
					ArrayList<IPattern> typeOfPattern = patternmap.get(type);
					typeOfPattern.add(p);
				}else{
					ArrayList<IPattern> newTypeOfPattern = new ArrayList<IPattern>();
					newTypeOfPattern.add(p);
					patternmap.put(type, newTypeOfPattern);
				}
			}
			
		}
		return patternmap;
	}
	
	public static ArrayList<String> getPatternNameList(HashMap<String, ArrayList<IPattern>> sourceMap, String type){
		ArrayList<String> nameList = new ArrayList<String>();
		Set<String> keyset = sourceMap.keySet();
		if(keyset.contains(type)){
			ArrayList<IPattern> patternlist = sourceMap.get(type);
			for(IPattern p: patternlist){
				String unfixedname = p.getInvoker();
				String[] namet = unfixedname.split("/");
				String fixedname = namet[namet.length - 1];
				if(!nameList.contains(fixedname)){
					nameList.add(fixedname);
				}

			}
		}
		return nameList;
	}

}
