package umlMaker.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import component.api.IComponent;
import component.api.IDeclaration;
import component.api.IRelation;
import component.impl.Decorator;
import component.impl.Field;
import component.impl.Method;
import component.impl.Singleton;
import component.impl.Uses;
import problem.asm.DesignParser;
import visitor.api.VisitorAdapter;

public class UMLMakerOutputStream extends VisitorAdapter {
	private final OutputStream out;

	public UMLMakerOutputStream(OutputStream out) throws IOException {
		this.out = out;
	}

	private void write(String m) {
		try {
			this.out.write(m.getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void visit(IComponent c) {
		if (c.getType().equals("Field")) {
			Field f = (Field) c;
			String type = Type.getType(f.getDescription()).getClassName();
			addAccessLevel(f.getAccess());
			addColon(f.getName());
			addEnter(type);
			if (f.getSignature() != null) {
				addReturnTypeType(f.getSignature());
			}
			write("\\l");
		} else if (c.getType().equals("Method")) {
			Method m = (Method) c;
			if (m.getName().equals("<init>"))
				return;
			addAccessLevel(m.getAccess());
			write(m.getName() + "(");
			addArguments(m.getDescription());
			write(") : ");
			addReturnType(m.getDescription());
			if (m.getSignature() != null) {
				addReturnTypeType(m.getSignature());

			}
			write("\\l");
		}
	}

	@Override
	public void visit(IRelation r) {
		if (r.getType().equals("Singleton")) {
			Singleton s = (Singleton) r;
			write(s.getClassName() + " -> " + s.getClassName() + "[arrowhead=\"vee\"];");
		}
		else if(r.getType().equals("Uses")) {
			Uses u = (Uses) r;
			write(u.getClassName() + " -> " + u.getReferenceName() + "[arrowhead=\"vee\", style=\"dashed\"];");
		}
	}

	@Override
	public void preVisit(IDeclaration c) {
		String[] namet = c.getName().split("/");
		write(namet[namet.length - 1] + "[");
		write("shape=\"record\",");
		for (IRelation r : c.getRelations()) {
			if (r.getType().equals("Singleton")) {
				write("fillcolor = \"blue\";style=\"filled\";");
				break;
			}
			if (r.getType().equals("Decorator")) {
				write("fillcolor = \"green\";style=\"filled\";");
				break;
			}
			if (r.getType().equals("Component")) {
				write("fillcolor = \"green\";style=\"filled\";");
				break;
			}
		}
		write("label = \"{" + namet[namet.length - 1] + "\\n");
		for (IRelation r : c.getRelations()) {
			if (r.getType().equals("Singleton")) {
				write("\\<\\<Singleton\\>\\>");
				break;
			}
			if (r.getType().equals("Decorator")) {
				write("\\<\\<Decorator\\>\\>");
				break;
			}
			if (r.getType().equals("Component")) {
				write("\\<\\<Component\\>\\>");
				break;
			}
		}
		write("|");
	}

	@Override
	public void visit(IDeclaration c) {
		write("|");
	}

	@Override
	public void postVisit(IDeclaration c) {
//		Map<String, Integer> preventDuplicateUse = new HashMap<String, Integer>();
//		Map<String, Integer> preventDuplicateAssociation = new HashMap<String, Integer>();
		String[] namet = c.getName().split("/");
		String[] superNamet = c.getSuperClass().split("/");
		ArrayList<String[]> interfacest = new ArrayList<String[]>();
		for (String i : c.getInterfaces()) {
			interfacest.add(i.split("/"));
		}

		write("}\"");
		write("];");

		if (!superNamet[superNamet.length - 1].equals("Object"))
			write(namet[namet.length - 1] + " -> " + superNamet[superNamet.length - 1] + " [arrowhead=\"onormal\"];");
		for (String[] i : interfacest) {

			String inter = i[i.length - 1];
			write(namet[namet.length - 1] + " -> " + inter + " [arrowhead=\"onormal\", style=\"dashed\"];");
		}
		
		for (IRelation r : c.getRelations()) {
			if (r.getType().equals("Decorator")) {
				Decorator rdec = (Decorator)r;
				String[] rreference = rdec.getDecorated().split("/");
				write(namet[namet.length - 1] + " -> " + rreference[rreference.length - 1] + " [arrowhead=\"onormal\", label = \"<<decorates>>\"];");
				break;
				//"label = "" "
			}
		}
		
		if (DesignParser.CLASSES == null) {
			return;
		}

	}

	void addAccessLevel(int access) {
		String level = "";
		if ((access & Opcodes.ACC_PUBLIC) != 0) {
			level = "+";
		} else if ((access & Opcodes.ACC_PROTECTED) != 0) {
			level = "-";
		} else if ((access & Opcodes.ACC_PRIVATE) != 0) {
			level = "#";
		} else {
			level = "";
		}
		write(level + " ");
	}

	void addReturnType(String desc) {
		if (desc != null) {
			String returnType = Type.getReturnType(desc).getClassName();
			String[] returnName = returnType.split("\\.");
			write(returnName[returnName.length - 1]);
		}
	}

	void addArguments(String desc) {
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

	void addReturnTypeType(String signature) {
		String[] type = signature.split("/");
		String[] type2 = type[type.length - 1].split(";");
		write("\\<" + type2[0] + "\\>");
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
