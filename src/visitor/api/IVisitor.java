package visitor.api;

import component.api.IComponent;
import component.api.IDeclaration;
import component.api.IRelation;
import component.api.IStatement;

public interface IVisitor {
	public void preVisit(IDeclaration d);
	public void visit(IDeclaration d);
	public void postVisit(IDeclaration d);

	public void preVisit(IComponent c);
	public void visit(IComponent c);
	public void postVisit(IComponent c);
	
	public void preVisit(IRelation r);
	public void visit(IRelation r);
	public void postVisit(IRelation r);
	
	public void preVisit(IStatement s);
	public void visit(IStatement s);
	public void postVisit(IStatement s);
}
