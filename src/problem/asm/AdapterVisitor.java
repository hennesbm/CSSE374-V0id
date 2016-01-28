package problem.asm;

import java.util.ArrayList;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

import com.sun.org.apache.bcel.internal.generic.Type;

import component.api.IDeclaration;
import component.api.IModel;
import component.impl.Adapter;

public class AdapterVisitor extends ClassVisitor {
	private IModel _model;
	private String currentClass;
	private ArrayList<String> interfaceClasses = new ArrayList<String>();
	private ArrayList<String> fields = new ArrayList<String>();

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

	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		String typeName = Type.getType(desc).toString();
		fields.add(typeName);
		return toDecorate;
	}

	public void visitEnd() {
		if (currentClass != null) {
			if (!interfaceClasses.isEmpty()) {
				if (!fields.isEmpty()) {
					setPattern();
				}
			}
		}
	}

	private void setPattern() {
		for (IDeclaration d : this._model.getAllClasses()) {
			if (d.getName().equals(this.currentClass)) {
				for (String s : this.fields) {
					String[] className = this.currentClass.split("/");
					String[] fieldName = s.split("\\.");
					d.addPattern(
							new Adapter(className[className.length - 1], "Adapter", fieldName[fieldName.length - 1]));
				}
			}
			for (String s : this.interfaceClasses) {
				for (IDeclaration dd : this._model.getAllClasses()) {
					if (dd.getName().equals(s)) {
						String[] fieldName = s.split("/");
						dd.addPattern(new Adapter(fieldName[fieldName.length - 1], "Target", null));
					}
				}
			}
			for (String s : this.fields) {
				for (IDeclaration dd : this._model.getAllClasses()) {
					if (dd.getName().equals(s.replace(".", "/"))) {
						String[] fieldName = s.split("\\.");
						dd.addPattern(new Adapter(fieldName[fieldName.length - 1], "Adaptee", null));
					}
				}
			}
		}
	}
}