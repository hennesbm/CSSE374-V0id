package umlMaker.impl;

import java.util.ArrayList;

import umlMaker.api.IComponent;
import umlMaker.api.IDeclaration;
import umlMaker.api.IModel;
import umlMaker.visitor.api.ITraverser;
import umlMaker.visitor.api.IVisitor;

public class Model implements IModel, ITraverser {
	private IDeclaration currentClass;
	private ArrayList<IDeclaration> classList = new ArrayList<IDeclaration>();

	@Override
	public void setCurrentClass(IDeclaration clazz) {
		this.currentClass = clazz;
	}

	@Override
	public void addCurrentClass() {
		this.classList.add(this.currentClass);
	}
	
	@Override
	public IDeclaration getCurrentClass() {
		return this.currentClass;
	}

	@Override
	public void accept(IVisitor v) {
		for (IDeclaration clazz : this.classList) {
			v.preVisit(clazz);

			String type = "Field";
			if (clazz.getComponents().size() > 0) {
				for (IComponent p : clazz.getComponents()) {
					if(!p.getType().equals(type))
						v.visit(clazz);
					p.accept(v);
					type = p.getType();
				}
			}

			v.postVisit(clazz);
		}
	}

}
