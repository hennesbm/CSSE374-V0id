package sdedit.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import component.api.IComponent;
import component.api.IDeclaration;
import component.api.IStatement;
import component.impl.Field;
import component.impl.Method;
import directory.reader.DirectoryReader;
import problem.asm.DesignParser;
import visitor.api.VisitorAdapter;

public class SDEditOutputStream extends VisitorAdapter {
	private final OutputStream out;
	private int classFlag = 0;
	private int methodFlag = 0;
	private Map<String, String> fieldMap;
	private int counter = 0;
	public static ArrayList<String> declare;
	
	public static ArrayList<String> methods;

	public SDEditOutputStream(OutputStream out) throws IOException {
		this.fieldMap = new HashMap<String, String>();
		this.out = out;
		declare = new ArrayList<String>();
		methods = new ArrayList<String>();
	}

	private void write(String m) {
		try {
			this.out.write(m.getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void preVisit(IComponent c) {
		if (c.getType().equals("Field")) {
			Field f = (Field) c;
			if (this.classFlag == 1 && this.methodFlag == 1) {
				String name = DirectoryReader.className.replaceAll("\\.", "/");
				if (!this.fieldMap.containsKey(name.substring(0, name.length() - 5))) {
					this.declare.add("arg" + counter + ":"
							+ name.substring(0, name.length() - 5).replaceAll("Ljava", "java") + "\n");
					// write("arg"+counter+":"+name.substring(0,
					// name.length()-5).replaceAll("Ljava", "java")+"\n");
					this.fieldMap.put(name.substring(0, name.length() - 5), "arg" + counter);
					counter++;
				}

				if (!this.fieldMap.containsKey(f.getDescription())) {
					this.declare.add("arg" + counter + ":"
							+ f.getDescription().replaceAll(";", "").replaceAll("Ljava", "java") + "\n");
					// write("arg"+counter+":"+f.getDescription().replaceAll(";",
					// "").replaceAll("Ljava", "java")+"\n");
					this.fieldMap.put(f.getDescription(), "arg" + counter);
					counter++;
				}
			}
		} else if (c.getType().equals("Method")) {
			Method m = (Method) c;
			if (m.getName().equals(DirectoryReader.method)) {
				this.methodFlag = 1;
			} else {
				this.methodFlag = 0;
			}
		}
	}

	@Override
	public void preVisit(IDeclaration c) {
		if (DirectoryReader.className != null) {
			String[] sc = c.getName().split("/");
			String[] sn = DirectoryReader.className.split("\\.");
			// System.out.println(sc[sc.length-1]);
			// System.out.println(sn[sn.length-2]);
			if (sc[sc.length - 1].equals(sn[sn.length - 2])) {
				this.classFlag = 1;

				// System.out.println(sc[sc.length-1]);
			}
		} else {
			this.classFlag = 0;
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
		String returnType = Type.getReturnType(desc).getClassName();
		String[] returnName = returnType.split("\\.");
		write(returnName[returnName.length - 1]);
	}

	void addArguments(String desc) {
		Type[] args = Type.getArgumentTypes(desc);
		for (int i = 0; i < args.length; i++) {
			String[] namet = args[i].getClassName().split("\\.");
			write(namet[namet.length - 1]);
			if (i != args.length - 1)
				write(", ");
		}
	}

	void addReturnTypeType(String signature) {
		String[] type = signature.split("/");
		String[] type2 = type[type.length - 1].split(";");
		write("\\<" + type2[0] + "\\>");
	}

	@Override
	public void preVisit(IStatement s) {
		// write("----PreVisitStart----\n");
		if (this.classFlag == 1) {
			if (this.fieldMap.get(s.getClassName()) == null) {
				this.fieldMap.put(s.getClassName(), "arg" + this.counter);
				this.declare.add("arg" + counter + ":" + s.getClassName() + "\n");
				// write("arg"+counter+":"+s.getClassName()+"\n");
				counter++;

			}
			String commandL = this.fieldMap.get(s.getClassName()) + ":";
			if (this.fieldMap.get(s.getOwner()) == null) {
				this.fieldMap.put(s.getOwner(), "arg" + this.counter);
				this.declare.add("arg" + counter + ":" + s.getOwner() + "\n");
				// write("arg"+counter+":"+s.getOwner()+"\n");
				counter++;
			}

			String commandR = this.fieldMap.get(s.getOwner()) + "." + s.getName() + "()\n";
			String command = commandL + commandR;
			if (command.contains("<init>")) {
				command = command.replaceAll("[()]", "").replaceAll("<init>", "new");
			}
			this.methods.add(command);
			// write(command);

		}

		// write("----PreVisitEnds----\n");
	}
}
