package component.api;

import java.util.Collection;

public interface IDeclaration {
	public int getVersion();
	public int getAccess();
	public String getName();
	public String getSignature();
	public String getSuperClass();
	public String[] getInterfaces();
	public void addComponent(IComponent c);
	public Collection<IComponent> getComponents();
	public Collection<IRelation> getRelations();
	public void addRelation(IRelation r);
	public Collection<IPattern> getPatterns();
	public void addPattern(IPattern p);
}
