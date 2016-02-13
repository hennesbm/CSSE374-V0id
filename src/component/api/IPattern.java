package component.api;

import visitor.api.ITraverser;

public interface IPattern extends ITraverser{
	public String getType();
	public String getComponent();
	public String getColor();
	public String getInvoker();
	public String getAccepter();
}