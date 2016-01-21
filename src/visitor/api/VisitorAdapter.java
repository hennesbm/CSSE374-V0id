package visitor.api;

import component.api.IDeclaration;
import component.api.IField;
import component.api.IMethod;
import component.api.ISingleton;
import component.api.IStatement;

public abstract class VisitorAdapter implements IVisitor {

	@Override
	public void preVisit(IField f) {
	}

	@Override
	public void visit(IField f) {
	}

	@Override
	public void postVisit(IField f) {
	}

	@Override
	public void preVisit(IMethod m) {
	}

	@Override
	public void visit(IMethod m) {
	}

	@Override
	public void postVisit(IMethod m) {
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
	
	@Override
	public void preVisit(IStatement s) {
	}
	
	@Override
	public void visit(IStatement s) {
	}
	
	@Override
	public void postVisit(IStatement s) {
	}
	
	@Override
	public void preVisit(ISingleton s) {
	}
	
	@Override
	public void visit(ISingleton s) {
	}
	
	@Override
	public void postVisit(ISingleton s) {
	}

}
