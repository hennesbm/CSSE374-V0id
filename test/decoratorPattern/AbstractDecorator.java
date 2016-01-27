package decoratorPattern;

public abstract class AbstractDecorator implements IComponent {
	protected IComponent c;

	public abstract void method1();

	public abstract void method2();
}
