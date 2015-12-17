package umlMaker.visitor.api;

import umlMaker.api.IDeclaration;
import umlMaker.api.IField;
import umlMaker.api.IMethod;

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

}
