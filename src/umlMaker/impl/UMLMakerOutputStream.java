package umlMaker.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import component.api.IComponent;
import component.api.IDeclaration;
import component.api.IPattern;
import component.api.IRelation;
import component.impl.Adapter;
import component.impl.Composition;
import component.impl.Decorator;
import component.impl.Extends;
import component.impl.Field;
import component.impl.Implements;
import component.impl.Method;
import component.impl.Singleton;
import component.impl.Uses;
import visitor.api.VisitorAdapter;

public class UMLMakerOutputStream extends VisitorAdapter {
	private final OutputStream out;
	private final ArrayList<String> removedClasses = new ArrayList<String>();

	public UMLMakerOutputStream(OutputStream out) throws IOException {
		this.out = out;
//		this.removedClasses.add("String");
//		this.removedClasses.add("int");
//		this.removedClasses.add("java");
//		this.removedClasses.add("boolean");
//		this.removedClasses.add("OutputStream");
//		this.removedClasses.add("ArrayList");
//		this.removedClasses.add("Object");
//		this.removedClasses.add("Collection");
//		this.removedClasses.add("Map");
//		this.removedClasses.add("Attribute");
//		this.removedClasses.add("TypePath");
//		this.removedClasses.add("Handle");
//		this.removedClasses.add("Label");
	}

	private void write(String m) {
		try {
			this.out.write(m.replace("$", "_").getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void visit(IComponent c) {
		if (c.getType().equals("Field")) {
			
			Field f = (Field) c;
			if(!f.toomuchflag){
				String type = Type.getType(f.getDescription()).getClassName();
				addAccessLevel(f.getAccess());
				addColon(f.getName());
				addEnter(type);
			}
			else{
				write("...");
			}
		} else if (c.getType().equals("Method")) {
			Method m = (Method) c;
			if(!m.toomuchflag){
				addAccessLevel(m.getAccess());
				if (m.getName().equals("<init>")) {
					write(m.getClassName() + "(");
				} else {
					write((m.getName() + "("));
				}
				addArguments(m.getDescription());
				write(") : ");
				addReturnType(m.getDescription());
			}
			else{
				write("...");
			}
		}
		write("\\l\n\t\t");
	}

	@Override
	public void visit(IRelation r) {
		write("\t");
		if (r.getType().equals("Extends")) {
			Extends e = (Extends) r;
			if (!this.removedClasses.contains(e.getReferenceName().split("\\[")[0]))
				write(e.getClassName() + " -> " + e.getReferenceName() + " [arrowhead=\"onormal\"];");
		} else if (r.getType().equals("Implements")) {
			Implements i = (Implements) r;
			if (!this.removedClasses.contains(i.getReferenceName().split("\\[")[0]))
				write(i.getClassName() + " -> " + i.getReferenceName() + "[arrowhead=\"onormal\", style=\"dashed\"];");
		} else if (r.getType().equals("Uses")) {
			Uses u = (Uses) r;
			if (!this.removedClasses.contains(u.getReferenceName().split("\\[")[0]))
				write(u.getClassName() + " -> " + u.getReferenceName() + "[arrowhead=\"vee\", style=\"dashed\"];");
		} else if (r.getType().equals("Composition")) {
			Composition c = (Composition) r;
			if (!this.removedClasses.contains(c.getReferenceName().split("\\[")[0]))
				write(c.getClassName() + " -> " + c.getReferenceName() + "[arrowhead=\"vee\"];");
		}
		write("\n");
	}

	@Override
	public void visit(IPattern p) {
		write("\t");
		if (p.getType().equals("Singleton")) {
			Singleton s = (Singleton) p;
			if (!this.removedClasses.contains(s.getClassName().split("\\[")[0]))
			write(s.getClassName() + " -> " + s.getClassName() + "[arrowhead=\"vee\"];");
		} else if (p.getType().equals("Adapter")) {
			Adapter a = (Adapter) p;
			if (a.getComponent().equals("Adapter")) {
				if (!this.removedClasses.contains(a.getAdaptee().split("\\[")[0]))
				write(a.getClassName() + " -> " + a.getAdaptee()
						+ "[arrowhead=\"vee\", label=\"\\<\\<adapts\\>\\>\"];");
			}
		} else if (p.getType().equals("Decorator")) {
			Decorator d = (Decorator) p;
			if (d.getComponent().equals("Decorator")) {
				String[] name = d.getClassName().split("/");
				String[] decorates = d.getDecorates().split("/");
				// System.out.println(name[name.length - 1] + " "
				// +component[component.length - 1]);
				if (!this.removedClasses.contains(d.getDecorates().split("\\[")[0]))
				write(name[name.length - 1] + " -> " + decorates[decorates.length - 1]
						+ "[arrowhead=\"onormal\", label=\"\\<\\<decorates\\>\\>\"];");
			}
		}
		write("\n");
	}

	@Override
	public void preVisit(IDeclaration c) {
		if(c.getActivity()){
			String[] namet = c.getName().split("/");
			write("\n\t" + namet[namet.length - 1] + "[\n\t\t");
			write("shape=\"record\",\n\t\t");
			if (!c.getPatterns().isEmpty()) {
				write("style = \"filled\", fillcolor = \"" + c.getPatterns().iterator().next().getColor() + "\";\n\t\t");
			}
			write("label = \"{" + namet[namet.length - 1] + "\\n");
			if (!c.getPatterns().isEmpty()) {
				
				write("\\<\\<" + c.getPatterns().iterator().next().getComponent() + "\\>\\>\n\t\t");
			}
			write("\n\t\t|\n\t\t");
		}

	}

	@Override
	public void visit(IDeclaration c) {
		if(c.getActivity()){
			write("|\n\t\t");
		}

	}

	@Override
	public void postVisit(IDeclaration c) {
		if(c.getActivity()){
			write("}\"\n\t");
			write("];\n\n");
		}

	}

	private void addAccessLevel(int access) {
		String level = "";
		if ((access & Opcodes.ACC_PUBLIC) != 0) {
			level = "+";
		} else if ((access & Opcodes.ACC_PROTECTED) != 0) {
			level = "#";
		} else if ((access & Opcodes.ACC_PRIVATE) != 0) {
			level = "-";
		} else {
			level = "";
		}
		write(level + " ");
	}

	private void addReturnType(String desc) {
		if (desc != null) {
			String returnType = Type.getReturnType(desc).getClassName();
			String[] returnName = returnType.split("\\.");
			write(returnName[returnName.length - 1]);
		}
	}

	private void addArguments(String desc) {
		if (desc != null) {
			Type[] args = Type.getArgumentTypes(desc);
			for (int i = 0; i < args.length; i++) {
				String[] namet = args[i].getClassName().split("\\.");
				write(namet[namet.length - 1]);
				if (i != args.length - 1)
					write(", ");
			}
		}

	}

	private void addEnter(String signature) {
		if (signature == null) {
			return;
		}
		String[] namet = signature.split("\\.");
		write(namet[namet.length - 1]);
	}

	private void addColon(String name) {
		write(name + " : ");
	}
}
