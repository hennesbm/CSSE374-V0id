package component.api;

import java.util.ArrayList;
import java.util.HashMap;

public interface IModel {
	public void addCurrentClass();
	public void setCurrentClass(IDeclaration clazz);
	public IDeclaration getCurrentClass();
	public ArrayList<IDeclaration> getAllClasses();
	public void setActive(String classname, boolean activity);
	public HashMap<String, ArrayList<IPattern>> getAllPatterns();
}
