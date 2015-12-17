package umlMaker.visitor.api;

import umlMaker.api.IDeclaration;
import umlMaker.api.IField;
import umlMaker.api.IMethod;

public interface IVisitor {

	public void preVisit(IField f);
	public void visit(IField f);
	public void postVisit(IField f);
	
	public void preVisit(IMethod m);
	public void visit(IMethod m);
	public void postVisit(IMethod m);

	public void preVisit(IDeclaration d);
	public void visit(IDeclaration d);
	public void postVisit(IDeclaration d);
}
