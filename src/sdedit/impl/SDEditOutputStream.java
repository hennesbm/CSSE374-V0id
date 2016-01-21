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
import component.api.IField;
import component.api.IMethod;
import component.api.IStatement;
import problem.asm.DesignParser;
import visitor.api.VisitorAdapter;

public class SDEditOutputStream extends VisitorAdapter {
	private final OutputStream out;

	public SDEditOutputStream(OutputStream out) throws IOException {
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

	}

	@Override
	public void visit(IMethod m) {

	}

	@Override
	public void preVisit(IDeclaration c) {

	}

	@Override
	public void visit(IDeclaration c) {
		
	}

	@Override
	public void postVisit(IDeclaration c) {

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

	private void addEnter(String signature) {
		String[] namet = signature.split("\\.");
		write(namet[namet.length - 1]);
	}

	private void addColon(String name) {
		write(name + " : ");
	}

	@Override
	public void preVisit(IStatement s) {
		//write("----PreVisitStart----\n");
		write(s.getOwner()+ ":arg."+s.getName()+"\n");
		//write("----PreVisitEnds----\n");
	}

	@Override
	public void visit(IStatement s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postVisit(IStatement s) {
		// TODO Auto-generated method stub
		
	}
}
