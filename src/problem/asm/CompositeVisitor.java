package problem.asm;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import component.api.IDeclaration;
import component.api.IModel;
import component.api.IPattern;
import component.impl.Composite;
import component.impl.Method;

public class CompositeVisitor extends ClassVisitor {

	private IModel _model;
	private IDeclaration current;
	private IDeclaration tempcomponent;

	public CompositeVisitor(int api) {
		super(api);
	}
	
	public CompositeVisitor(int api, IModel model) {
		super(api);
		this._model = model;
	}
	
	public CompositeVisitor(int api, ClassVisitor decorated, IModel model) {
		super(api,decorated);
		this._model = model;
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		for(IDeclaration d: this._model.getAllClasses()){
			if(d.getName().equals(name)){
				this.current = d;
			}
		}
		super.visit(version, access, name, signature, superName, interfaces);
		
	}
	
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		
		
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		
		return toDecorate;
	}
	

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		Collection<IDeclaration> currenthierarchy = this.current.getHierarchy();
		if(isPotentialComposite(name, desc, currenthierarchy)){
			Composite composite = new Composite(this.current.getName(), this.tempcomponent.getName(), "Composite");
			this.current.addPattern(composite);
		}
		return toDecorate;
	}
	
	
	
	
	//Detect whether current class has methods that takes arguments that are its superclass or interfaces.
	//Also, there needs to be an add method.
	private Boolean isPotentialComposite(String methodname, String desc, Collection<IDeclaration> currenthierarchy) {
		if (desc != null) {
			
			Type[] args = Type.getArgumentTypes(desc);
			for (int i = 0; i < args.length; i++) {
				//name: classnames of arguments
				String name = args[i].getClassName().replaceAll("\\.", "/");
				for(IDeclaration d: currenthierarchy){
					if(d.getName().equals(name) && methodname.equals("add")){
						Composite component = new Composite(d.getName(),"Component");
						this.tempcomponent = d;
						boolean flag = true;
						for(IPattern p: d.getPatterns()){
							if(p.getType().equals("Composite")){
								flag = false;
							}
						}
						if(flag){
							d.addPattern(component);
						}
						return true;
					}
				}
			}
		}
		return false;

	}

	
	
	
}
