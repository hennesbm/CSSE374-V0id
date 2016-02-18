package problem.asm;

import java.util.ArrayList;

import org.objectweb.asm.ClassVisitor;
//import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import com.sun.org.apache.bcel.internal.generic.Type;

import component.api.IDeclaration;
import component.api.IModel;
import component.impl.Adapter;

public class AdapterVisitor extends ClassVisitor {
	private IModel _model;
	private String currentClass;
	private ArrayList<String> interfaceClasses = new ArrayList<String>();
	private ArrayList<String> fields = new ArrayList<String>();
	private ArrayList<String> removedClasses = new ArrayList<String>();

	{

		this.removedClasses.add("int");
		this.removedClasses.add("boolean");
//		this.removedClasses.add("java");
//		this.removedClasses.add("org");

	}

	public AdapterVisitor(int api, IModel model) {
		super(api);
		this._model = model;
	}

	public AdapterVisitor(int api, ClassVisitor decorated, IModel model) {
		super(api, decorated);
		this._model = model;
	}

	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		super.visit(version, access, name, signature, superName, interfaces);
		currentClass = name;
		for (String s : interfaces) {
			interfaceClasses.add(s);
		}
	}

//	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
//		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
//		String typeName = Type.getType(desc).toString();
//		fields.add(typeName);
//		return toDecorate;
//	}

	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		if (name.equals("<init>")) {
			if (desc != null) {
				Type[] types = Type.getArgumentTypes(desc);
				for (Type t : types) {
					String typeName = t.toString();
					String[] typeNameParts = typeName.split("\\.");
					String[] finalParts = typeNameParts[typeNameParts.length-1].split("\\[");
					if(!this.removedClasses.contains(typeNameParts[0]) && !finalParts[finalParts.length-1].equals("]")) {
						fields.add(typeName);
					}
				}
			}
		}
		return toDecorate;
	}

	public void visitEnd() {
		if (currentClass != null) {
			if (!interfaceClasses.isEmpty()) {
				if (!fields.isEmpty()) {
					for (String interfaces : interfaceClasses) {
						for (String fields : fields) {
							setPattern(this.currentClass, interfaces, fields);
						}
					}
				}
			}
		}
	}

	private void setPattern(String adapter, String target, String adaptee) {
		
		
		for (IDeclaration d : this._model.getAllClasses()) {
			ArrayList<String> allInfluencedClasses = new ArrayList<String>();
			String[] interfaceName = target.split("/");
			String[] fieldName = adaptee.split("\\.");
			String[] className = this.currentClass.split("/");
			allInfluencedClasses.add(interfaceName[interfaceName.length - 1]);
			allInfluencedClasses.add(className[className.length - 1]);
			allInfluencedClasses.add(fieldName[fieldName.length - 1]);
			
			
			if (d.getName().equals(adapter)) {
				d.addPattern(new Adapter(className[className.length - 1], "Adapter", fieldName[fieldName.length - 1], allInfluencedClasses));
			} else if (d.getName().equals(target)) {
				d.addPattern(new Adapter(interfaceName[interfaceName.length - 1], "Target", null,allInfluencedClasses));
			} else if (d.getName().equals(adaptee.replace(".", "/"))) {
				d.addPattern(new Adapter(fieldName[fieldName.length - 1], "Adaptee", null,allInfluencedClasses));
			}
		}
	}
}