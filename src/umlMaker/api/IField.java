package umlMaker.api;

public interface IField extends IComponent {
	public int getAccess();
	public String getName();
	public String getDescription();
	public String getSignature();
	public Object getValue();
}
