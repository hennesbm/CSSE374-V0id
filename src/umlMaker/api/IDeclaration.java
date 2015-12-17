package umlMaker.api;

public interface IDeclaration {
	public int getVersion();
	public int getAccess();
	public String getName();
	public String getSignature();
	public String getSuperClass();
	public String[] getInterfaces();
}
