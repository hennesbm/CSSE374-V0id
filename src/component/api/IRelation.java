package component.api;

import visitor.api.ITraverser;

public interface IRelation extends ITraverser {
	public String getType();
	public String getInvoker();
	public String getAccepter();
}
