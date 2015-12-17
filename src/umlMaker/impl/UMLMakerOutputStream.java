package umlMaker.impl;

import java.io.IOException;
import java.io.OutputStream;

import umlMaker.api.IDeclaration;
import umlMaker.api.IField;
import umlMaker.api.IMethod;
import umlMaker.visitor.api.VisitorAdapter;

public class UMLMakerOutputStream extends VisitorAdapter{
	private final OutputStream out;
	
	public UMLMakerOutputStream(OutputStream out) throws IOException {
		this.out = out;
	}
	
	private void write(String m) {
		try {
			this.out.write(m.getBytes());
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void preVisit(IField f) {
		// TODO Auto-generated method stub
		super.preVisit(f);
	}

	@Override
	public void visit(IField f) {
		// TODO Auto-generated method stub
		super.visit(f);
	}

	@Override
	public void postVisit(IField f) {
		// TODO Auto-generated method stub
		super.postVisit(f);
	}

	@Override
	public void preVisit(IMethod m) {
		// TODO Auto-generated method stub
		super.preVisit(m);
	}

	@Override
	public void visit(IMethod m) {
		// TODO Auto-generated method stub
		super.visit(m);
	}

	@Override
	public void postVisit(IMethod m) {
		// TODO Auto-generated method stub
		super.postVisit(m);
	}

	@Override
	public void preVisit(IDeclaration d) {
		// TODO Auto-generated method stub
		super.preVisit(d);
	}

	@Override
	public void visit(IDeclaration d) {
		// TODO Auto-generated method stub
		super.visit(d);
	}

	@Override
	public void postVisit(IDeclaration d) {
		// TODO Auto-generated method stub
		super.postVisit(d);
	}
	
	
}
