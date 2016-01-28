package decoratorPattern;

public class ConcreteDecorator2 extends AbstractDecorator {
	
	IComponent c;
	
	@Override
	public void method1() {
		System.out.println("This is ConcreteDecorator2 Method 1.");
	}

	@Override
	public void method2() {
		System.out.println("This is ConcreteDecorator2 Method 2.");
	}

}
