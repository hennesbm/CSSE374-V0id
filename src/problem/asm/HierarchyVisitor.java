package problem.asm;

import java.util.ArrayList;
import java.util.Collection;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Type;

import component.api.IDeclaration;
import component.api.IModel;

public class HierarchyVisitor extends ClassVisitor{
	
	private IModel _model;
	private IDeclaration current;
	
	public HierarchyVisitor(int api) {
		super(api);
		// TODO Auto-generated constructor stub
	}

	public HierarchyVisitor(int api, ClassVisitor decorated, IModel model) {
		super(api,decorated);
		this._model = model;
	}
	
	public HierarchyVisitor(int api, IModel model) {
		super(api);
		this._model = model;
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
	
		//Find current reading class
		for(IDeclaration d: this._model.getAllClasses()){
			if(d.getName().equals(name)){
				this.current = d;
			}
		}
		
		//Change hierarchy
		this.current.getHierarchy().add(this.current);
		findAllSuper(this.current.getHierarchy(), this.current.getName());
		super.visit(version, access, name, signature, superName, interfaces);
		
	}
	
	
	public void findAllSuper(Collection<IDeclaration> l, String classname){
		ArrayList<String> currentsupernames = new ArrayList<String>();
		ArrayList<IDeclaration> classlist = this._model.getAllClasses();
		
		for(IDeclaration c: classlist){
			if(c.getName().equals(classname)){
				if(!this.current.getName().equals(c.getName()) && !l.contains(c)){
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
