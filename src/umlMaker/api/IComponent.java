package umlMaker.api;

import umlMaker.visitor.api.ITraverser;

public interface IComponent extends ITraverser {
	public String getType();
	public String getName();
	public String getDescription();
	public String getSignature();
}
