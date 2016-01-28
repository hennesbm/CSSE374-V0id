package decoratorPattern;

public abstract class AbstractDecorator implements IComponent {
	public IComponent c;

	public abstract void method1();

	public abstract void method2();
}
