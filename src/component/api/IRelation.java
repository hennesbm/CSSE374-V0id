package component.api;

import visitor.api.ITraverser;

public interface IRelation extends ITraverser {
	public String getType();
	public String getName();
	public String getReference();
}
