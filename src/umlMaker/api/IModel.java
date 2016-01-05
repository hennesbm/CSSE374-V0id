package umlMaker.api;

public interface IModel {
	public void addCurrentClass();
	public void setCurrentClass(IDeclaration clazz);
	public IDeclaration getCurrentClass();
}
