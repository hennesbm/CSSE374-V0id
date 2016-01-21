package component.api;

public interface ISingleton extends IRelation {
	public boolean isField();
	public boolean isMethod();
	public boolean isSingleton();
	public void setField();
	public void setMethod();
}
