package component.api;

public interface IMethod extends IComponent {
	public int getAccess();
	public String getName();
	public String getDescription();
	public String getSignature();
	public String[] getExceptions();
}
