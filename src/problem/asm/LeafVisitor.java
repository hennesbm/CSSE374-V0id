package problem.asm;

import java.util.ArrayList;
import java.util.Arrays;

import org.objectweb.asm.ClassVisitor;

import component.api.IDeclaration;
import component.api.IModel;
import component.api.IPattern;
import component.impl.Composite;

public class LeafVisitor extends ClassVisitor {


	private IModel _model;
	private IDeclaration current;
	private IDeclaration currentsuperclass;
	public LeafVisitor(int api) {
		super(api);
	}
	
	public LeafVisitor(int api, IModel model) {
		super(api);
		this._model = model;
	}
	
	public LeafVisitor(int api, ClassVisitor decorated, IModel model) {
		super(api,decorated);
		this._model = model;
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		for(IDeclaration d: this._model.getAllClasses()){
			if(d.getName().equals(name)){
				this.current = d;
			}
			if(d.getName().equals(superName)){
				this.currentsuperclass = d;
			}
		}
		
		getAllComposite(this.current, this.currentsuperclass);
	
		super.visit(version, access, name, signature, superName, interfaces);
		
	}
	
	public void getAllComposite(IDeclaration current, IDeclaration currentsuperclass){
		boolean flag = true;
		for(IPattern cp: current.getPatterns()){
			if(cp.getType().equals("Composite")){
				flag = false;
			}
		}
		if(flag){
			if(currentsuperclass!=null){
				for(IPattern p : currentsuperclass.getPatterns()){
					if(p.getType().equals("Composite")){
						if(p.getComponent().equals("Component")){
							Composite leaf = new Composite(current.getName(),currentsuperclass.getName(),"Leaf");
							current.addPattern(leaf);
						}else if(p.getComponent().equals("Composite")){
							Composite composite = new Composite(current.getName(),currentsuperclass.getName(),"Composite");
							current.addPattern(composite);
							for(IDeclaration d: this._model.getAllClasses()){
								if(d.getSuperClass().equals(current.getName())){
									getAllComposite(d,current);
								}
							}		
						}
					}
				}
			}
		}

	}
	
}
