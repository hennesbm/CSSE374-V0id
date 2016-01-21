package visitor.api;

import component.api.IDeclaration;
import component.api.IField;
import component.api.IMethod;
import component.api.ISingleton;
import component.api.IStatement;

public interface IVisitor {

	public void preVisit(IField f);
	public void visit(IField f);
	public void postVisit(IField f);
	
	public void preVisit(IMethod m);
	public void visit(IMethod m);
	public void postVisit(IMethod m);

	public void preVisit(IDeclaration c);
	public void visit(IDeclaration c);
	public void postVisit(IDeclaration c);
	
	public void preVisit(IStatement s);
	public void visit(IStatement s);
	public void postVisit(IStatement s);
	
	public void preVisit(ISingleton s);
	public void visit(ISingleton s);
	public void postVisit(ISingleton s);
}
