package component.api;

import java.util.ArrayList;

import visitor.api.ITraverser;

public interface IPattern extends ITraverser{
	public String getType();
	public String getComponent();
	public String getColor();
	public String getInvoker();
	public String getAccepter();
	public ArrayList<String> getAllInfluencedClasses();
}