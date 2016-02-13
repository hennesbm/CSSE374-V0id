package component.api;

import java.util.ArrayList;

public interface IModel {
	public void addCurrentClass();
	public void setCurrentClass(IDeclaration clazz);
	public IDeclaration getCurrentClass();
	public ArrayList<IDeclaration> getAllClasses();
	public void setActive(String classname, boolean activity);
}
