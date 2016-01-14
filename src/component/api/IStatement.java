package component.api;

public interface IStatement extends IComponent{
	public int getOpcode();
	public String getOwner();
	public String getName();
	public String getDescription();
	public boolean ifInterface();
}
