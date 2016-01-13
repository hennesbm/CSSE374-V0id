package visitor.api;

import component.api.IDeclaration;
import component.api.IField;
import component.api.IMethod;

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
}
