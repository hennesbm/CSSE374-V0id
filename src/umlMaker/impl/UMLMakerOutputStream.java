package umlMaker.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import umlMaker.api.IDeclaration;
import umlMaker.api.IField;
import umlMaker.api.IMethod;
import umlMaker.visitor.api.VisitorAdapter;

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
	public void visit(IField f) {
		String type = Type.getType(f.getDescription()).getClassName();
		addAccessLevel(f.getAccess());
		addColon(f.getName());
		addEnter(type);
	}

	@Override
	public void visit(IMethod m) {
		addAccessLevel(m.getAccess());
		System.out.print(m.getName() + "(");
		addArguments(m.getDescription());
		System.out.print(") : ");
		addReturnType(m.getDescription());
		System.out.print("\\l");
	}

	@Override
	public void preVisit(IDeclaration c) {
		String[] namet = c.getName().split("/");
		System.out.println(namet[namet.length - 1] + "[");
		System.out.println("shape=\"record\",");
		System.out.print("label = \"{" + namet[namet.length - 1] + "|");
	}

	@Override
	public void postVisit(IDeclaration c) {
		String[] namet = c.getName().split("/");
		String[] superNamet = c.getSuperClass().split("/");
		ArrayList<String[]> interfacest = new ArrayList<String[]>();
		for (String i : c.getInterfaces()) {
			interfacest.add(i.split("/"));
		}

		System.out.println("}\"");
		System.out.println("];");
		System.out.println(
				namet[namet.length - 1] + " -> " + superNamet[superNamet.length - 1] + " [arrowhead=\"onormal\"];");
		for (String[] i : interfacest) {
			String inter = i[i.length - 1];
			System.out
					.println(namet[namet.length - 1] + " -> " + inter + " [arrowhead=\"onormal\", style=\"dashed\"];");
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
		System.out.print(" " + level + " ");
	}

	void addReturnType(String desc) {
		String returnType = Type.getReturnType(desc).getClassName();
		System.out.print(returnType);
	}

	void addArguments(String desc) {
		Type[] args = Type.getArgumentTypes(desc);
		for (int i = 0; i < args.length; i++) {
			String arg = args[i].getClassName();
			System.out.print(arg + " " );
		}
	}
	
	private void addEnter(String signature) {
		System.out.print(signature + "\\" + "l");

	}

	private void addColon(String name) {
		System.out.print(name + " : ");
	}
}
