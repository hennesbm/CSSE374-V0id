package component.impl;

import java.io.IOException;
import java.util.ArrayList;

import component.api.IComponent;
import component.api.IDeclaration;
import component.api.IModel;
import sdedit.impl.SDEditOutputStream;
import component.api.IRelation;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

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
			boolean change = false;
			if (clazz.getComponents().size() > 0) {
				for (IComponent p : clazz.getComponents()) {
					if (!p.getType().equals(type) && !change) {
						v.visit(clazz);
						change = true;
					}
					p.accept(v);
					type = p.getType();
				}
			}
			
			if (clazz.getRelations().size() > 0) {
				for (IRelation p : clazz.getRelations()) {
					p.accept(v);
				}
			}

			v.postVisit(clazz);
		}


	}

}
