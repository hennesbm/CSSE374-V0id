package visitor.api;

import component.api.IComponent;
import component.api.IDeclaration;
import component.api.IPattern;
import component.api.IRelation;
import component.api.IStatement;

public abstract class VisitorAdapter implements IVisitor {
	@Override
	public void preVisit(IDeclaration d) {
	}

	@Override
	public void visit(IDeclaration d) {
	}

	@Override
	public void postVisit(IDeclaration d) {
	}

	@Override
	public void preVisit(IComponent c) {
	}

	@Override
	public void visit(IComponent c) {
	}

	@Override
	public void postVisit(IComponent c) {
	}

	@Override
	public void preVisit(IRelation r) {
	}

	@Override
	public void visit(IRelation r) {
	}

	@Override
	public void postVisit(IRelation r) {
	}

	@Override
	public void preVisit(IPattern p) {
	}

	@Override
	public void visit(IPattern p) {
	}

	@Override
	public void postVisit(IPattern p) {
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
}
