package component.api;

import java.util.ArrayList;

public interface IModel {
	public void addCurrentClass();
	public void setCurrentClass(IDeclaration clazz);
	public IDeclaration getCurrentClass();
<<<<<<< HEAD
	public ArrayList<IDeclaration> getAllClasses();
=======
	public ArrayList<IDeclaration> getClassList();
>>>>>>> origin/master
}
