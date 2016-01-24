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
import component.impl.Field;
import component.impl.Method;
import component.impl.Singleton;
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
	public void preVisit(IDeclaration c) {
		String[] namet = c.getName().split("/");
		write(namet[namet.length - 1] + "[");
		write("shape=\"record\",");
		Singleton single = (Singleton) c.getRelations().iterator().next();
		if (single.isSingleton()) {
			write("color=\"blue\",");
			write("label = \"{" + namet[namet.length - 1] + "\\n");
			write("\\<\\<Singleton\\>\\>|");
		} else {
			write("label = \"{" + namet[namet.length - 1] + "|");
		}
	}

	@Override
	public void visit(IDeclaration c) {
		write("|");
	}

	@Override
	public void postVisit(IDeclaration c) {
		Map<String, Integer> preventDuplicateUse = new HashMap<String, Integer>();
		Map<String, Integer> preventDuplicateAssociation = new HashMap<String, Integer>();
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
		if (DesignParser.CLASSES == null) {
			return;
		}
		for (String clazz : DesignParser.CLASSES) {
			for (IComponent j : c.getComponents()) {
				String[] ca = c.getName().split("/");

				if (j.getType().equals("Field")) {
					String name = j.getDescription();
					String[] s = clazz.split("\\.");
					String[] s2 = name.split("/");
					String field = s[s.length - 1];
					String name2 = s2[s2.length - 1].replace(";", "");
					if (name2.equals(field)) {
						if (!preventDuplicateUse.containsKey(ca[ca.length - 1] + field)) {
							write(ca[ca.length - 1] + " -> " + field + "[arrowhead=\"vee\", style=\"dashed\"];");
							preventDuplicateUse.put(ca[ca.length - 1] + field, 1);
						}
					}
					if (j.getSignature() != null) {
						String[] sig = j.getSignature().split("/");
						String signature = sig[sig.length - 1].replace(";>;", "");
						if (signature.equals(field)) {
							if (!preventDuplicateAssociation.containsKey(ca[ca.length - 1] + signature)) {
								write(ca[ca.length - 1] + " -> " + signature + "[arrowhead=\"vee\"];");
								preventDuplicateAssociation.put(ca[ca.length - 1] + signature, 1);
							}
						}

					}
				}

				else if (j.getType().equals("Method")) {
					String name = j.getDescription().split("\\)")[0];
					String[] s = clazz.split("\\.");
					String[] s2 = name.split("/");
					String method = s[s.length - 1];
					String name2 = s2[s2.length - 1].replace(";", "");
					if (name2.equals(method)) {

						if (!preventDuplicateAssociation.containsKey(ca[ca.length - 1] + method)) {
							write(ca[ca.length - 1] + " -> " + method + "[arrowhead=\"vee\"];");
							preventDuplicateAssociation.put(ca[ca.length - 1] + method, 1);

						}
					}
					if (j.getSignature() != null) {
						String[] sig = j.getSignature().split("/");
						String signature = sig[sig.length - 1].replace(";>;", "");
						if (signature.equals(method)) {
							if (!preventDuplicateAssociation.containsKey(ca[ca.length - 1] + signature)) {

								write(ca[ca.length - 1] + " -> " + signature + "[arrowhead=\"vee\"];");
								preventDuplicateAssociation.put(ca[ca.length - 1] + signature, 1);
							}
						}
					}
				}
			}
		}
		for (IRelation r : c.getRelations()) {
			if (r.getType().equals("Singleton")) {
				Singleton single = (Singleton) r;
				if (single.isSingleton()) {
					String[] classNameExtended = c.getName().split("/");
					String className = classNameExtended[classNameExtended.length - 1];
					write(className + " -> " + className + "[arrowhead=\"vee\"];");
				}
			}
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
