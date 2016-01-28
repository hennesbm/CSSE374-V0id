package problem.asm;

import java.util.ArrayList;
import java.util.Arrays;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Type;

import component.api.IDeclaration;
import component.api.IModel;
import component.api.IRelation;
import component.impl.Component;
import component.impl.Decorator;

public class ClassDecoratorVisitor extends ClassVisitor {
	private IModel _model;
	private ArrayList<IDeclaration> superclass;
	
	public ClassDecoratorVisitor(int api, IModel model) {
		super(api);
		this._model = model;
		this.superclass = new ArrayList<IDeclaration>();
	}
	
	public ClassDecoratorVisitor(int api, ClassVisitor decorated, IModel model) {
		super(api,decorated);
		this._model = model;
		this.superclass = new ArrayList<IDeclaration>();
	}
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		super.visit(version, access, name, signature, superName, interfaces);
		System.out.println("name: " + name);
		System.out.println("signature: " + signature);
		System.out.println("superName: " + superName);
		System.out.println("interfaces: " + Arrays.toString(interfaces));
		System.out.println();
	}

		

	
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		System.out.println("name-field: " + name);
		System.out.println("desc-field: " + desc);
		System.out.println("signature-field: " + signature);
		System.out.println("classtype-field: " + Type.getType(desc).getClassName());
		
		
	
		findAllSuper(this.superclass,this._model.getCurrentClass().getName());
		
		for(IDeclaration s: superclass){
			String description = Type.getType(desc).getClassName().replaceAll("\\.", "/");
			
			if(s.getName().equals(description)){
				boolean flag = false;
				for(IRelation r: s.getRelations()){
					if(r.getType().equals("Component")){
						flag = true;
					}
				}
				if(!flag){
					s.addRelation(new Component());
				}
				Decorator dec = new Decorator(s.getName());
				//System.out.println(this._model.getCurrentClass().getName() + "->" + s.getName());
				this._model.getCurrentClass().addRelation(dec);
				


			}
			
		}
		
		
		System.out.println();
		
		
		return toDecorate;
	}
	
	public void findAllSuper(ArrayList<IDeclaration> l, String classname){
		ArrayList<String> currentsupernames = new ArrayList<String>();
		ArrayList<IDeclaration> classlist = this._model.getAllClasses();
		
		for(IDeclaration c: classlist){
			if(c.getName().equals(classname)){
				if(!this._model.getCurrentClass().getName().equals(c.getName()) && !l.contains(c)){
					l.add(c);
				}
				
				for(String s: c.getInterfaces()){
					currentsupernames.add(s);
				}
				if(!c.getSuperClass().equals("java/lang/Object")){
					currentsupernames.add(c.getSuperClass());
				}
		
			}
		}
		for(String s: currentsupernames){
			for(IDeclaration d: classlist){
				if(s.equals(d.getName())){
					findAllSuper(l, s);
				}
			}
		}
		
	}
	


}
